package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.AchievementStorage;
import eu.andret.intelliachievements.IntelliAchievements;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public abstract class Achievement implements Comparable<Achievement> {
    private final List<Integer> states = new ArrayList<>();
    private final List<OnStateUpdateListener> onStateUpdateListeners = new ArrayList<>();
    private final IntelliAchievements.State state;

    public interface OnStateUpdateListener {
        void stateUpdated(Achievement a, int old, int current);
    }

    Achievement(IntelliAchievements.State state, int firstState, Integer... states) {
        this.state = state;
        this.states.add(firstState);
        this.states.addAll(Arrays.asList(states));
    }

    public abstract String getName();

    public abstract String getText();

    public int getMatchingState() {
        List<Integer> states = getStates();
        for (int state : states) {
            if (state > getCurrentState()) {
                return state;
            }
        }
        return states.get(states.size() - 1);
    }

    public final List<Integer> getStates() {
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
        for (Field field : state.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(AchievementStorage.class)) {
                continue;
            }
            if (field.getAnnotation(AchievementStorage.class).achievement().equals(getClass())) {
                return field;
            }
        }
        return null;
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

    public void addOnStateUpdateListener(OnStateUpdateListener listener) {
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
                ", states=" + states +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Achievement that = (Achievement) o;
        return Objects.equals(getStates(), that.getStates()) &&
                Objects.equals(getCurrentState(), that.getCurrentState()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStates(), getCurrentState(), getName());
    }

    @Override
    public int compareTo(@NotNull Achievement achievement) {
        return getClass().getSimpleName().compareTo(achievement.getClass().getSimpleName());
    }
}
