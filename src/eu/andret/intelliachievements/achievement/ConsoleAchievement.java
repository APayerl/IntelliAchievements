package eu.andret.intelliachievements.achievement;

import com.intellij.execution.filters.Filter;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class ConsoleAchievement extends Achievement implements Filter {
    @SafeVarargs
    public ConsoleAchievement(IntelliAchievements.State state, int firstState, Integer... states) {
        super(state, firstState, states);
    }

    @Override
    public Filter.Result applyFilter(String line, int entireLength) {

        return null;
    }
}
