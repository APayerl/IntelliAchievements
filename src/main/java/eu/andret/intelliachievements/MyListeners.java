package eu.andret.intelliachievements;

import com.intellij.openapi.vfs.VirtualFileManager;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;

public class MyListeners {
    private static final VirtualFileManager vfm = VirtualFileManager.getInstance();

    public static void setUpListeners() {
        AchievementManager.getByClass(FileSystemAchievement.class).forEach(a -> vfm.addVirtualFileListener((FileSystemAchievement) a));
    }
}
