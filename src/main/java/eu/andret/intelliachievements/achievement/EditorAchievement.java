package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;

public abstract class EditorAchievement extends Achievement {
	protected EditorAchievement(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
	}

	public abstract void charTyped(char c);
}
