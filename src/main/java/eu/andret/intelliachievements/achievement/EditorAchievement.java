package eu.andret.intelliachievements.achievement;

import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;

public abstract class EditorAchievement extends Achievement {
	public EditorAchievement(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}

	public abstract void charTyped(char c);
}
