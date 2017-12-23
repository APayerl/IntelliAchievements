package eu.andret.intelliachievements;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.EditorAchievement;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MyTypedHandlerDelegate extends TypedHandlerDelegate {
    private List<Achievement> achievements = AchievementManager.getByClass(EditorAchievement.class);

    @Override
    public Result charTyped(char c, Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        for (Achievement achievement : achievements) {
            if (achievement instanceof EditorAchievement) {
                ((EditorAchievement) achievement).charTyped(c);
            }
        }
        return Result.CONTINUE;
    }
}
