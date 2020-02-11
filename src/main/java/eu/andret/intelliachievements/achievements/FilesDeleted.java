package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;

public class FilesDeleted extends FileSystemAchievement {
	public FilesDeleted(Project project, IntelliAchievements.AchievementsState state, int... states) {
		super(project, state, states);
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

//    @Override
//    public void fileDeleted(@NotNull VirtualFileEvent event) {
//        setCurrentState(getCurrentState() + 1);
//    }

}
