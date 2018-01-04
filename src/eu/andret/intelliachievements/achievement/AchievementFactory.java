package eu.andret.intelliachievements.achievement;

import com.intellij.ui.JBColor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;

public final class AchievementFactory {
    private AchievementFactory() {
    }

    public static JPanel getAchievementPanel(Achievement achievement) {
        final JPanel achievementPanel = new JPanel(new MigLayout("", "[center][grow]", "[center]"));
        achievementPanel.setBackground(JBColor.LIGHT_GRAY);
        achievementPanel.setPreferredSize(new Dimension(700, 50));
        achievementPanel.setToolTipText(achievement.getToolTip());

        JLabel label = new JLabel(achievement.getName());
        label.setPreferredSize(new Dimension(0, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JProgressBar progress = new JProgressBar(0, achievement.getMatchingState());
        progress.setValue(achievement.getCurrentState());
        progress.setBackground(JBColor.LIGHT_GRAY);
        progress.setStringPainted(true);
        progress.setString(achievement.getCurrentState() + "/" + achievement.getMatchingState());
        progress.setPreferredSize(new Dimension(700, 20));
        progress.setMinimumSize(new Dimension(700, 20));

        if (achievement.getCurrentState() >= achievement.getMatchingState()) {
            progress.setForeground(JBColor.GREEN);
        }

        achievementPanel.add(label, "wrap");
        achievementPanel.add(progress);
        return achievementPanel;
    }
}
