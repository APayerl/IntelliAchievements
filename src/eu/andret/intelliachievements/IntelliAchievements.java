package eu.andret.intelliachievements;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievements.HelloWorld;
import eu.andret.intelliachievements.achievements.SymbolsTyped;
import java.util.Arrays;


public class IntelliAchievements implements PersistentStateComponent<IntelliAchievements.AchievementsState>, ApplicationComponent {
    private AchievementsState state = new AchievementsState();

    @State(name = "IntelliAchievements",
            storages = {
                    @Storage(id = "other", file = "$APP_CONFIG$/IntelliAchievements.xml")
            }
    )
    public static class AchievementsState {
        private AchievementsState() {
        }

        @AchievementStorage(achievement = HelloWorld.class)
        public int helloWorlds = 1;

        @AchievementStorage(achievement = SymbolsTyped.class)
        public int keysTyped = 50;
    }

    @Override
    public AchievementsState getState() {
        return state;
    }

    @Override
    public void loadState(AchievementsState loadedState) {
        state = loadedState;
    }

    @Override
    public void initComponent() {
        Achievement[] typical = {
                new HelloWorld(state, 1),//, 10, 100, 1000),
                new SymbolsTyped(state, 100, 1_000, 1_000_000, 1_000_000_000, Integer.MAX_VALUE)
        };

        Arrays.stream(typical).forEach(achievement -> {
            achievement.addOnStateUpdateListener((a, old, current) -> {
                if (a.getStates().contains(current)) {
                    Notifications.Bus.notify(new Notification("Achievement", a.getName(), a.getToolTip(), NotificationType.INFORMATION));
                }
            });
            AchievementManager.registerAchievement(achievement);
        });
    }

    @Override
    public void disposeComponent() {
        AchievementManager.disposeAll();
    }
}
