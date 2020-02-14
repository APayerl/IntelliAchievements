package eu.andret.intelliachievements;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievements.EasterEggFound;
import eu.andret.intelliachievements.achievements.FilesCreated;
import eu.andret.intelliachievements.achievements.FilesDeleted;
import eu.andret.intelliachievements.achievements.HelloWorld;
import eu.andret.intelliachievements.achievements.SymbolsTyped;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@State(name = "achievements", storages = {
		@Storage("achievements.xml")
})
public class IntelliAchievements implements PersistentStateComponent<IntelliAchievements.AchievementsState> {
	private static boolean release = true;

	private AchievementsState achievementsState = new AchievementsState();

	public IntelliAchievements() {
		Achievement[] typical = {
				new HelloWorld(achievementsState, 1, 10, 100),
				new SymbolsTyped(achievementsState, 100, 1_000, 1_000_000, 1_000_000_000, Integer.MAX_VALUE),
				new FilesCreated(achievementsState, 1, 10, 100, 1_000, 10_000),
				new FilesDeleted(achievementsState, 1, 10, 100, 1_000, 10_000),
				new EasterEggFound(achievementsState, 1)
		};

		Arrays.stream(typical).forEach(achievement -> {
			achievement.addOnStateUpdateListener((a, old, current) -> {
				if (Arrays.stream(a.getStates()).anyMatch(s -> s == current)) {
					Notifications.Bus.notify(new Notification("Achievement", a.getName(), a.getToolTipText(), NotificationType.INFORMATION));
				}
			});
			if (BulkFileListener.class.isAssignableFrom(achievement.getClass())) {
				ApplicationManager.getApplication().getMessageBus().connect().subscribe(VirtualFileManager.VFS_CHANGES, (BulkFileListener) achievement);
			}
			AchievementManager.registerAchievement(achievement);
		});
		if (release) {
			MyListeners.setUpListeners();
		}
	}

	public static class AchievementsState {
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
		return achievementsState;
	}

	@Override
	public void loadState(@NotNull AchievementsState state) {
		achievementsState = state;
	}

	public static void beta() {
		release = false;
	}
}
