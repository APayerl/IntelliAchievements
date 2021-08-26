package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.AchievementStorage;
import eu.andret.intelliachievements.IntelliAchievements;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Achievement implements Comparable<Achievement> {
	private final int[] states;
	private final List<OnStateUpdateListener> onStateUpdateListeners = new ArrayList<>();
	private final IntelliAchievements.AchievementsState state;

	public interface OnStateUpdateListener {
		void stateUpdated(Achievement a, int old, int current);
	}

	Achievement(final IntelliAchievements.AchievementsState state, final int... states) {
		this.state = state;
		this.states = states;
	}

	public abstract String getName();

	public abstract String getToolTipText();

	public abstract boolean isHidden();

	public final int getMatchingState() {
		return Arrays.stream(getStates())
				.filter(i -> i > getCurrentState())
				.findFirst()
				.orElse(states[states.length - 1]);
	}

	public final int[] getStates() {
		return states;
	}

	public int getCurrentState() {
		return getTargetField().map(field -> {
			try {
				return (int) field.get(state);
			} catch (final IllegalAccessException ex) {
				ex.printStackTrace();
			}
			return 0;
		}).orElse(0);
	}

	private Optional<Field> getTargetField() {
		return Arrays.stream(state.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(AchievementStorage.class))
				.filter(field -> field.getAnnotation(AchievementStorage.class).achievement().equals(getClass()))
				.findFirst();
	}

	public void setCurrentState(final int newState) {
		getTargetField().ifPresent(field -> {
			try {
				for (final OnStateUpdateListener listener : onStateUpdateListeners) {
					listener.stateUpdated(this, (int) field.get(state), newState);
				}
				field.set(state, newState);
			} catch (final IllegalAccessException ex) {
				ex.printStackTrace();
			}
		});
	}

	public final void addOnStateUpdateListener(final OnStateUpdateListener listener) {
		onStateUpdateListeners.add(listener);
	}

	@Override
	public String toString() {
		return getTargetField().map(field -> {
			String result = "Achievement{" + getClass().getSimpleName() + ", name=\"" + getName() + "\"";

			try {
				result += ", currentState=" + (int) field.get(state);
			} catch (final IllegalAccessException ex) {
				ex.printStackTrace();
			}

			return result + ", matchingState=" + getMatchingState() +
					", states=" + Arrays.toString(states) +
					'}';
		}).orElse("");
	}

	@Override
	public final boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Achievement that = (Achievement) o;
		return Arrays.equals(getStates(), that.getStates()) &&
				Objects.equals(getCurrentState(), that.getCurrentState()) &&
				Objects.equals(getName(), that.getName());
	}

	@Override
	public final int hashCode() {
		return Objects.hash(Arrays.hashCode(getStates()), getCurrentState(), getName());
	}

	@Override
	public int compareTo(@NotNull final Achievement achievement) {
		if (isHidden() == achievement.isHidden()) {
			return getName().compareTo(achievement.getName());
		}
		return (isHidden() ? 1 : 0) - (achievement.isHidden() ? 1 : 0);
	}
}
