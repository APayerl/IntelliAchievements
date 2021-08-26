package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.newvfs.events.VFileDeleteEvent;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;
import org.jetbrains.annotations.NotNull;

public class FilesDeleted extends FileSystemAchievement {
	public FilesDeleted(final IntelliAchievements.AchievementsState state, final int... states) {
		super(state, states);
	}

	@Override
	public String getName() {
		return "Goodbye cruel world...";
	}

	@Override
	public String getToolTipText() {
		return "Delete " + getMatchingState() + " files.";
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public void fileChangedBefore(@NotNull final VFileEvent event, @NotNull final Project project) {
		final ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
		if (event instanceof VFileDeleteEvent && event.getFile() != null && fileIndex.isInContent(event.getFile())) {
			setCurrentState(getCurrentState() + 1);
		}
	}
}
