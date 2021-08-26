package eu.andret.intelliachievements.achievement;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@UtilityClass
public final class AchievementManager {
	private static final Set<Achievement> achievements = new TreeSet<>();

	public void registerAchievement(@NotNull final Achievement achievement) {
		achievements.add(achievement);
	}

	public <E extends Achievement> List<E> getByClass(final Class<E> clazz) {
		//noinspection unchecked
		return achievements.stream()
				.filter(a -> a.getClass().isAssignableFrom(clazz) || clazz.isAssignableFrom(a.getClass()))
				.map(x -> (E) x)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
 
	public List<Achievement> getAllAchievements() {
		return new ArrayList<>(achievements);
	}
}
