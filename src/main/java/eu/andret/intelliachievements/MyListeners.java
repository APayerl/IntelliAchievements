package eu.andret.intelliachievements;

import com.intellij.openapi.vfs.VirtualFileManager;

public class MyListeners {
	private static final VirtualFileManager vfm = VirtualFileManager.getInstance();

	public static void setUpListeners() {
//        AchievementManager.getByClass(FileSystemAchievement.class).forEach(a -> vfm.addVirtualFileListener((BulkFileListener) a));
	}
}
