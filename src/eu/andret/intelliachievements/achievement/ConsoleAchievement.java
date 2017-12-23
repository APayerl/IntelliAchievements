package eu.andret.intelliachievements.achievement;

import com.intellij.execution.filters.Filter;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class ConsoleAchievement<T extends Comparable<T>> extends Achievement<T> implements Filter {
    @SafeVarargs
    public ConsoleAchievement(IntelliAchievements.State state, T initialState, T firstState, T... states) {
        super(state, initialState, firstState, states);
    }

    @Override
    public Filter.Result applyFilter(String line, int entireLength) {

        return null;
    }
}
