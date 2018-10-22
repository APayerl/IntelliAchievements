package eu.andret.intelliachievements.achievement;

import com.intellij.openapi.vfs.VirtualFileCopyEvent;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import eu.andret.intelliachievements.IntelliAchievements;
import org.jetbrains.annotations.NotNull;

public abstract class FileSystemAchievement extends Achievement implements VirtualFileListener {
    public FileSystemAchievement(IntelliAchievements.AchievementsState state, int firstState, Integer... states) {
        super(state, firstState, states);
    }

    @Override
    public void propertyChanged(@NotNull VirtualFilePropertyEvent event) {
    }

    @Override
    public void contentsChanged(@NotNull VirtualFileEvent event) {
    }

    @Override
    public void fileCreated(@NotNull VirtualFileEvent event) {
    }

    @Override
    public void fileDeleted(@NotNull VirtualFileEvent event) {
    }

    @Override
    public void fileMoved(@NotNull VirtualFileMoveEvent event) {
    }

    @Override
    public void fileCopied(@NotNull VirtualFileCopyEvent event) {
    }

    @Override
    public void beforePropertyChange(@NotNull VirtualFilePropertyEvent event) {
    }

    @Override
    public void beforeContentsChange(@NotNull VirtualFileEvent event) {
    }

    @Override
    public void beforeFileDeletion(@NotNull VirtualFileEvent event) {
    }

    @Override
    public void beforeFileMovement(@NotNull VirtualFileMoveEvent event) {
    }
}
