package eu.andret.intelliachievements.achievements;

import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.EditorAchievement;

public class SymbolsTyped extends EditorAchievement<Long> {

    public SymbolsTyped(IntelliAchievements.State state, Long initialState, Long firstState, Long... states) {
        super(state, initialState, firstState, states);
    }

    @Override
    public void charTyped(char c) {
        setCurrentState(getCurrentState() + 1);
        state.keysTyped++;
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
