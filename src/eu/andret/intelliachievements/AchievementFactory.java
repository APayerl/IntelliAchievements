package eu.andret.intelliachievements;

import com.intellij.ui.JBColor;
import eu.andret.intelliachievements.achievement.Achievement;
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
        achievementPanel.setMaximumSize(new Dimension(712, 50));
        achievementPanel.setMinimumSize(new Dimension(600, 30));
        boolean shouldBeHidden = achievement.isHidden() && (achievement.getCurrentState() < achievement.getMatchingState());
        achievementPanel.setToolTipText(shouldBeHidden ? "<html><i>Hidden achievement!</i></html>" : achievement.getToolTipText());


        JLabel label = new JLabel(shouldBeHidden ? "???" : achievement.getName());
        label.setPreferredSize(new Dimension(0, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JProgressBar progress = new JProgressBar(0, achievement.getMatchingState());
        progress.setBackground(JBColor.LIGHT_GRAY);
        progress.setStringPainted(true);
        progress.setValue(shouldBeHidden ? 0 : achievement.getCurrentState());
        progress.setString(shouldBeHidden ? "??? / ???" : String.format("%,d / %,d", achievement.getCurrentState(), achievement.getMatchingState()));
        progress.setPreferredSize(new Dimension(700, 20));

        if (achievement.getCurrentState() >= achievement.getMatchingState()) {
            progress.setForeground(JBColor.GREEN);
        }

        achievementPanel.add(label, "wrap");
        achievementPanel.add(progress);
        return achievementPanel;
    }
}
