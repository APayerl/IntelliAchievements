package eu.andret.intelliachievements.achievements;

import com.intellij.openapi.vfs.VirtualFileEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import eu.andret.intelliachievements.achievement.FileSystemAchievement;
import org.jetbrains.annotations.NotNull;

public class FilesCreated extends FileSystemAchievement {
    public FilesCreated(IntelliAchievements.AchievementsState state, int firstState, Integer... states) {
        super(state, firstState, states);
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
    public void fileCreated(@NotNull VirtualFileEvent event) {
        setCurrentState(getCurrentState() + 1);
    }

}
