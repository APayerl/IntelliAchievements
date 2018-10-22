package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.vfs.VirtualFileEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;
import org.jetbrains.annotations.NotNull;

public class FilesDeleted extends FileSystemAchievement {
    public FilesDeleted(IntelliAchievements.AchievementsState state, int firstState, Integer... states) {
        super(state, firstState, states);
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
    public void fileDeleted(@NotNull VirtualFileEvent event) {
        setCurrentState(getCurrentState() + 1);
    }

}
