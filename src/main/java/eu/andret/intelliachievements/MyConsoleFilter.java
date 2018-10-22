package eu.andret.intelliachievements;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import eu.andret.intelliachievements.achievement.AchievementManager;
import eu.andret.intelliachievements.achievement.ConsoleAchievement;
import org.jetbrains.annotations.NotNull;

public class MyConsoleFilter implements ConsoleFilterProvider {
    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        return AchievementManager.getByClass(ConsoleAchievement.class).toArray(new Filter[]{});
    }
}
