package eu.andret.intelliachievements;

import eu.andret.intelliachievements.achievement.AchievementFactory;
import eu.andret.intelliachievements.achievement.AchievementManager;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;

public class PluginSettingsForm {
    private JPanel root;

    public JComponent getRootComponent() {
        if (root == null) {
            root = new JPanel(new MigLayout("wrap 1", "[grow]"));
            JPanel achievementsPanel = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
            achievementsPanel.setBorder(BorderFactory.createTitledBorder("Achievements"));
            achievementsPanel.setLayout(new BoxLayout(achievementsPanel, BoxLayout.PAGE_AXIS));
            achievementsPanel.setMaximumSize(new Dimension(720, 500));

            AchievementManager.getAllAchievements().forEach(achievement -> {
                achievementsPanel.add(AchievementFactory.getAchievementPanel(achievement));
                achievementsPanel.add(Box.createVerticalStrut(5));
            });

            root.add(achievementsPanel, "grow");
        }
        return root;
    }

    public static void main(String[] args) {
        new IntelliAchievements().initComponent();
        PluginSettingsForm form = new PluginSettingsForm();

        JFrame frame = new JFrame("Test: IntelliAchievements SettingsForm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(form.getRootComponent());
        frame.setSize(730, 300);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}
