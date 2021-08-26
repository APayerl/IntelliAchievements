package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;

public abstract class MenuAchievement extends Achievement {
	protected MenuAchievement(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
	}
}
