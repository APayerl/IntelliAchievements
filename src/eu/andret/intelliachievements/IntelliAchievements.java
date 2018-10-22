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
import eu.andret.intelliachievements.achievements.EasterEggFound;
import eu.andret.intelliachievements.achievements.FilesCreated;
import eu.andret.intelliachievements.achievements.FilesDeleted;
import eu.andret.intelliachievements.achievements.HelloWorld;
import eu.andret.intelliachievements.achievements.SymbolsTyped;
import java.util.Arrays;

public class IntelliAchievements implements PersistentStateComponent<IntelliAchievements.AchievementsState>, ApplicationComponent {
    private static IntelliAchievements instance;
    private static boolean release = true;

    private AchievementsState state = new AchievementsState();

    public IntelliAchievements() {
        instance = this;
    }

    @State(name = "IntelliAchievements",
            storages = {
                    @Storage(id = "other", file = "$APP_CONFIG$/IntelliAchievements.xml")
            }
    )
    public static class AchievementsState {
        private AchievementsState() {
        }

        @AchievementStorage(achievement = HelloWorld.class)
        public int helloWorlds;

        @AchievementStorage(achievement = SymbolsTyped.class)
        public int keysTyped;

        @AchievementStorage(achievement = FilesCreated.class)
        public int filesCreated;

        @AchievementStorage(achievement = FilesDeleted.class)
        public int filesDeleted;

        @AchievementStorage(achievement = EasterEggFound.class)
        public int easterEggFound;
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
                new HelloWorld(state, 1, 10, 100),
                new SymbolsTyped(state, 100, 1_000, 1_000_000, 1_000_000_000, Integer.MAX_VALUE),
                new FilesCreated(state, 1, 10, 100, 1_000, 10_000),
                new FilesDeleted(state, 1, 10, 100, 1_000, 10_000),
                new EasterEggFound(state, 1)
        };

        Arrays.stream(typical).forEach(achievement -> {
            achievement.addOnStateUpdateListener((a, old, current) -> {
                if (a.getStates().contains(current)) {
                    Notifications.Bus.notify(new Notification("Achievement", a.getName(), a.getToolTipText(), NotificationType.INFORMATION));
                }
            });
            AchievementManager.registerAchievement(achievement);
        });
        if (release) {
            MyListeners.setUpListeners();
        }
    }

    @Override
    public void disposeComponent() {
        AchievementManager.disposeAll();
    }

    public static IntelliAchievements getInstance() {
        return instance;
    }

    public static void beta() {
        release = false;
    }
}
