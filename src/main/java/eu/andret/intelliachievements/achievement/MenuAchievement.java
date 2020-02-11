package eu.andret.intelliachievements.achievement;

import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class MenuAchievement extends Achievement {
	public MenuAchievement(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}
}
