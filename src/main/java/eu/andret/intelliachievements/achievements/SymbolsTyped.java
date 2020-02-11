package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.EditorAchievement;

public class SymbolsTyped extends EditorAchievement {
	public SymbolsTyped(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}

	@Override
	public void charTyped(char c) {
		if (c < 'z' && c > 'a' || c < '9' && c > '0' || c < 'Z' && c > 'A') {
			setCurrentState(getCurrentState() + 1);
		}
	}

	@Override
	public String getName() {
		return "Keyboard master";
	}

	@Override
	public String getToolTipText() {
		return "Type " + getMatchingState() + " symbols";
	}


	@Override
	public boolean isHidden() {
		return false;
	}
}
