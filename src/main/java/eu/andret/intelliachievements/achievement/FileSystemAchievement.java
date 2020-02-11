package eu.andret.intelliachievements.achievement;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class FileSystemAchievement extends Achievement implements BulkFileListener {
	public FileSystemAchievement(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
	}

	@Override
	public void before(@NotNull List<? extends VFileEvent> events) {
		System.out.println("before" + events.get(0).getRequestor());
	}

	@Override
	public void after(@NotNull List<? extends VFileEvent> events) {
//		events.get(0).getRequestor()
		ProjectFileIndex fileIndex = ProjectRootManager.getInstance(getProject()).getFileIndex();
		System.out.println("after" + fileIndex.isInContent(events.get(0).getFile()));
	}
}
