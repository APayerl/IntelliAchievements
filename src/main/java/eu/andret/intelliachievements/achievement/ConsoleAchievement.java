package eu.andret.intelliachievements.achievement;

import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class ConsoleAchievement extends Achievement implements Filter {
	public ConsoleAchievement(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}
}
