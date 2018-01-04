package eu.andret.intelliachievements.achievement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.jetbrains.annotations.NotNull;

public final class AchievementManager {
    private static final Set<Achievement> achievements = new TreeSet<>();

    private AchievementManager() {
    }


    public static void registerAchievement(@NotNull Achievement achievement) {
        achievements.add(achievement);
    }

    public static List<Achievement> getByClass(Class<? extends Achievement> clazz) {
        return achievements.stream()
                .filter(a -> a.getClass().isAssignableFrom(clazz) || clazz.isAssignableFrom(a.getClass()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public static void disposeAll() {
        achievements.clear();
    }

    public static List<Achievement> getAllAchievements() {
        return new ArrayList<>(achievements);
    }
}
