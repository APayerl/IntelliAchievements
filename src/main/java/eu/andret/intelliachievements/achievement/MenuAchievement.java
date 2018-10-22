package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;

public abstract class MenuAchievement extends Achievement {
    public MenuAchievement(IntelliAchievements.AchievementsState state, int firstState, Integer... states) {
        super(state, firstState, states);
    }
}
