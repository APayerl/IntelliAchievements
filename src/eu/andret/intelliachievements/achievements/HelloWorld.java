package eu.andret.intelliachievements.achievements;

import com.intellij.execution.filters.Filter;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.ConsoleAchievement;

public class HelloWorld extends ConsoleAchievement {
    public HelloWorld(IntelliAchievements.AchievementsState state, Integer firstState, Integer... states) {
        super(state, firstState, states);
    }

    @Override
    public String getName() {
        return "Greet the world!";
    }

    @Override
    public String getToolTip() {
        return "Type traditional \"Hello World\".";
    }

    @Override
    public Filter.Result applyFilter(String line, int entireLength) {
        if (line.toLowerCase().contains("Hello World".toLowerCase())) {
            setCurrentState(getCurrentState() + 1);
        }
        return null;
    }
}
