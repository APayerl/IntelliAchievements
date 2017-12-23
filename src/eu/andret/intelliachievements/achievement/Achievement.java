package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public abstract class Achievement<T extends Comparable<T>> implements Comparable<Achievement<T>> {
    private final List<T> states = new ArrayList<>();
    private final List<OnStateUpdateListener> onStateUpdateListeners = new ArrayList<>();
    protected final IntelliAchievements.State state;

    private T currentState;

    public interface OnStateUpdateListener {
        <T> void stateUpdated(Achievement a, T old, T current);
    }

    @SafeVarargs
    public Achievement(IntelliAchievements.State state, T initialState, T firstState, T... states) {
        this.state = state;
        currentState = initialState;
        this.states.add(firstState);
        this.states.addAll(Arrays.asList(states));
    }

    public abstract String getName();

    public abstract String getText();


    public T getMatchingState() {
        List<T> states = getStates();
        for (T state : states) {
            if (state.compareTo(getCurrentState()) > 0) {
                return state;
            }
        }
        return states.get(states.size() - 1);
    }

    public final List<T> getStates() {
        return states;
    }

    public T getCurrentState() {
        return currentState;
    }

    public void setCurrentState(T newState) {
        for (OnStateUpdateListener listener : onStateUpdateListeners) {
            listener.stateUpdated(this, currentState, newState);
        }
        currentState = newState;
    }

    public void addOnStateUpdateListener(OnStateUpdateListener listener) {
        onStateUpdateListeners.add(listener);
    }

    @Override
    public String toString() {
        return "Achievement{" + getClass().getSimpleName() +
                ", name=\"" + getName() + "\"" +
                ", currentState=" + currentState +
                ", matchingState=" + getMatchingState() +
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
        Achievement<?> that = (Achievement<?>) o;
        return Objects.equals(getStates(), that.getStates()) &&
                Objects.equals(getCurrentState(), that.getCurrentState()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStates(), getCurrentState(), getName());
    }

    @Override
    public int compareTo(@NotNull Achievement<T> achievement) {
        return getClass().getSimpleName().compareTo(achievement.getClass().getSimpleName());
    }
}
