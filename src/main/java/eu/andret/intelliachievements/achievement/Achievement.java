package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.AchievementStorage;
import eu.andret.intelliachievements.IntelliAchievements;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Achievement implements Comparable<Achievement> {
	private final int[] states;
	private final List<OnStateUpdateListener> onStateUpdateListeners = new ArrayList<>();
	private final IntelliAchievements.AchievementsState state;

	public interface OnStateUpdateListener {
		void stateUpdated(Achievement a, int old, int current);
	}

	Achievement(IntelliAchievements.AchievementsState state, int... states) {
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
		try {
			return (int) Objects.requireNonNull(getTargetField()).get(state);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	private Field getTargetField() {
		return Arrays.stream(state.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(AchievementStorage.class))
				.filter(field -> field.getAnnotation(AchievementStorage.class).achievement().equals(getClass()))
				.findFirst()
				.orElse(null);
	}

	public void setCurrentState(int newState) {
		try {
			for (OnStateUpdateListener listener : onStateUpdateListeners) {
				listener.stateUpdated(this, (int) Objects.requireNonNull(getTargetField()).get(state), newState);
			}
			getTargetField().set(state, newState);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}

	public final void addOnStateUpdateListener(OnStateUpdateListener listener) {
		onStateUpdateListeners.add(listener);
	}

	@Override
	public String toString() {
		String result = "Achievement{" + getClass().getSimpleName() +
				", name=\"" + getName() + "\"";

		try {
			result += ", currentState=" + (int) Objects.requireNonNull(getTargetField()).get(state);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

		return result + ", matchingState=" + getMatchingState() +
				", states=" + Arrays.toString(states) +
				'}';
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Achievement that = (Achievement) o;
		return Arrays.equals(getStates(), that.getStates()) &&
				Objects.equals(getCurrentState(), that.getCurrentState()) &&
				Objects.equals(getName(), that.getName());
	}

	@Override
	public final int hashCode() {
		return Objects.hash(getStates(), getCurrentState(), getName());
	}

	@Override
	public int compareTo(@NotNull Achievement achievement) {
		if (isHidden() == achievement.isHidden()) {
			return getName().compareTo(achievement.getName());
		}
		return (isHidden() ? 1 : 0) - (achievement.isHidden() ? 1 : 0);
	}
}
