package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.newvfs.events.VFileCreateEvent;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;
import org.jetbrains.annotations.NotNull;

public class FilesCreated extends FileSystemAchievement {
	public FilesCreated(IntelliAchievements.AchievementsState state, int... states) {
		super(state, states);
	}

	@Override
	public String getName() {
		return "Hello new world!";
	}

	@Override
	public String getToolTipText() {
		return "Create " + getMatchingState() + " files.";
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public void fileChangedAfter(@NotNull VFileEvent event, @NotNull Project project) {
		ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
		if (event instanceof VFileCreateEvent && event.getFile() != null && fileIndex.isInContent(event.getFile())) {
			setCurrentState(getCurrentState() + 1);
		}
	}
}
