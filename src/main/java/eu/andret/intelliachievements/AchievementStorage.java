package eu.andret.intelliachievements;

import eu.andret.intelliachievements.achievement.Achievement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AchievementStorage {
	Class<? extends Achievement> achievement();
}
