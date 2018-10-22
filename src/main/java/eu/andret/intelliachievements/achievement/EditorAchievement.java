package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;

public abstract class EditorAchievement extends Achievement {
    public EditorAchievement(IntelliAchievements.AchievementsState state, int firstState, Integer... states) {
        super(state, firstState, states);
    }

    public abstract void charTyped(char c);
}
