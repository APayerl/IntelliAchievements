package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;

public class FilesCreated extends FileSystemAchievement {
	public FilesCreated(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
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

//	@Override
//	public void fileCreated(@NotNull VirtualFileEvent event) {
//		setCurrentState(getCurrentState() + 1);
//	}

}
