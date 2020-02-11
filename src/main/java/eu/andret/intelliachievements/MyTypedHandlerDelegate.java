package eu.andret.intelliachievements;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.EditorAchievement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyTypedHandlerDelegate extends TypedHandlerDelegate {
	private List<Achievement> achievements = AchievementManager.getByClass(EditorAchievement.class);

	@NotNull
	@Override
	public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
		for (Achievement achievement : achievements) {
			((EditorAchievement) achievement).charTyped(c);
		}
		return Result.CONTINUE;
	}
}
