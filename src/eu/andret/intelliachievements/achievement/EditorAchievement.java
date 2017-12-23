package eu.andret.intelliachievements.achievement;

import eu.andret.intelliachievements.IntelliAchievements;

public abstract class EditorAchievement<T extends Comparable<T>> extends Achievement<T> {
    public EditorAchievement(IntelliAchievements.State state, T initialState, T firstState, T... states) {
        super(state, initialState, firstState, states);
    }

    public abstract void charTyped(char c);
}
