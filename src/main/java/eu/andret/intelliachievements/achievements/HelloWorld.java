package eu.andret.intelliachievements.achievements;

import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.ConsoleAchievement;

public class HelloWorld extends ConsoleAchievement {
	public HelloWorld(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}

	@Override
	public String getName() {
		return "Greet the world!";
	}

	@Override
	public String getToolTipText() {
		return "Type traditional \"Hello World\".";
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public Filter.Result applyFilter(String line, int entireLength) {
		if (line.toLowerCase().contains("Hello World".toLowerCase())) {
			setCurrentState(getCurrentState() + 1);
		}
		return null;
	}
}
