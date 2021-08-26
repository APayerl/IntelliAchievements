package eu.andret.intelliachievements;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.EditorAchievement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyTypedHandlerDelegate extends TypedHandlerDelegate {
	private final List<EditorAchievement> achievements = AchievementManager.getByClass(EditorAchievement.class);

	@NotNull
	@Override
	public Result charTyped(final char c, @NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile file) {
		achievements.forEach(achievement -> achievement.charTyped(c));
		return Result.CONTINUE;
	}
}
