package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.MenuAchievement;

public class EasterEggFound extends MenuAchievement {
	public EasterEggFound(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
	}

	@Override
	public String getName() {
		return "Is it Easter time?";
	}

	@Override
	public String getToolTipText() {
		return "Find the Easter egg.";
	}

	@Override
	public boolean isHidden() {
		return true;
	}
}

class EggFound extends AnAction {
	@Override
	public void actionPerformed(final AnActionEvent anActionEvent) {
		Messages.showDialog("You have found the Easter Egg and got the Achievement!", "Easter Egg", new String[]{"OK"}, -1, null);
		anActionEvent.getPresentation().setEnabledAndVisible(true);
		AchievementManager.getByClass(EasterEggFound.class).get(0).setCurrentState(1);
	}
}
