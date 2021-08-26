package eu.andret.intelliachievements;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.ConsoleAchievement;
import org.jetbrains.annotations.NotNull;

public class MyConsoleFilter implements ConsoleFilterProvider {
	@Override
	public Filter @NotNull [] getDefaultFilters(@NotNull final Project project) {
		return AchievementManager.getByClass(ConsoleAchievement.class)
				.stream()
				.map(ConsoleAchievement.class::cast)
				.toArray(Filter[]::new);
	}
}
