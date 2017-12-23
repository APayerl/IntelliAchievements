package eu.andret.intelliachievements;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievements.HelloWorld;
import eu.andret.intelliachievements.achievements.SymbolsTyped;
import org.jetbrains.annotations.NotNull;

@State(name = "IntelliAchievements",
        storages = {
                @Storage(id = "other", file = "$APP_CONFIG$/IntelliAchievements.xml")
        }
)
public class IntelliAchievements implements PersistentStateComponent<IntelliAchievements.State>, ApplicationComponent {
    private final State state;

    public static class State {
        private State() {
        }

        @AchievementStorage(achievement = HelloWorld.class)
        public int helloWorlds;

        @AchievementStorage(achievement = SymbolsTyped.class)
        public int keysTyped;
    }

    private IntelliAchievements() {
        state = new State();
        Achievement[] typical = {
                new HelloWorld(state, 1, 10, 100, 1000),
                new SymbolsTyped(state, 100, 1_000, 1_000_000, 1_000_000_000, Integer.MAX_VALUE)
        };

        for (Achievement achievement : typical) {
            achievement.addOnStateUpdateListener((a, old, current) -> {
                if (a.getStates().contains(current)) {
                    Notifications.Bus.notify(new Notification("Achievement", a.getName(), a.getText(), NotificationType.INFORMATION));
                }
            });
            AchievementManager.registerAchievement(achievement);
        }
    }

    @Override
    public IntelliAchievements.State getState() {
        return state;
    }

    @Override
    public void loadState(IntelliAchievements.State state) {
        System.out.println(state + ", ");
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "IntelliAchievements";
    }
}
