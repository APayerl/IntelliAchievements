package eu.andret.intelliachievements.achievement;

import com.intellij.execution.filters.Filter;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class ConsoleAchievement extends Achievement implements Filter {
	protected ConsoleAchievement(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
	}
}
