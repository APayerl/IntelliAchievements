package eu.andret.intelliachievements.achievements;

import com.intellij.execution.filters.Filter;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.ConsoleAchievement;

public class HelloWorld extends ConsoleAchievement {
	public HelloWorld(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
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
	public Filter.Result applyFilter(final String line, final int entireLength) {
		if (line.toLowerCase().contains("Hello World".toLowerCase())) {
			setCurrentState(getCurrentState() + 1);
		}
		return null;
	}
}
