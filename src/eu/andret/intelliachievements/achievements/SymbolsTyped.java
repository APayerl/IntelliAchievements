package eu.andret.intelliachievements.achievements;

import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.EditorAchievement;

public class SymbolsTyped extends EditorAchievement {

    public SymbolsTyped(IntelliAchievements.State state, Integer firstState, Integer... states) {
        super(state, firstState, states);
    }

    @Override
    public void charTyped(char c) {
        setCurrentState(getCurrentState() + 1);
    }

    @Override
    public String getName() {
        return "Keyboard master";
    }

    @Override
    public String getText() {
        return "Type " + getMatchingState()/*.get(getCurrentState())*/ + " symbols";
    }
}
