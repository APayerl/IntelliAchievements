package eu.andret.intelliachievements.achievement;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.wm.WindowManager;
import eu.andret.intelliachievements.IntelliAchievements;
import org.jetbrains.annotations.NotNull;

import java.awt.Window;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class FileSystemAchievement extends Achievement implements BulkFileListener {
	public FileSystemAchievement(IntelliAchievements.AchievementsState state, int... states) {
		super(state, states);
	}

	private Optional<Project> getCurrentProject() {
		return Arrays.stream(ProjectManager.getInstance().getOpenProjects())
				.filter(p -> {
					Window window = WindowManager.getInstance().suggestParentWindow(p);
					return window != null && window.isActive();
				})
				.findFirst();
	}

	@Override
	public void after(@NotNull List<? extends VFileEvent> events) {
		getCurrentProject()
				.ifPresent(project -> events.forEach(event -> fileChangedAfter(event, project)));
	}

	@Override
	public void before(@NotNull List<? extends VFileEvent> events) {
		getCurrentProject()
				.ifPresent(project -> events.forEach(event -> fileChangedBefore(event, project)));
	}

	public void fileChangedAfter(@NotNull VFileEvent event, @NotNull Project project) {
	}

	public void fileChangedBefore(@NotNull VFileEvent event, @NotNull Project project) {
	}

}
