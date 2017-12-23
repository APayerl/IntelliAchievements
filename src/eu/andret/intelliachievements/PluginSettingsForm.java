package eu.andret.intelliachievements;

import com.intellij.ui.JBColor;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

public class PluginSettingsForm {
    private final JPanel root = new JPanel(new MigLayout("wrap 1", "[grow]"));

    PluginSettingsForm() {
        JPanel projectSetings = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));

        JPanel ideSettings = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
        ideSettings.setBorder(BorderFactory.createTitledBorder("Achievements"));
        ideSettings.setLayout(new BoxLayout(ideSettings, BoxLayout.PAGE_AXIS));

        for (Achievement achievement : AchievementManager.getAllAchievements()) {
            ideSettings.add(getAchievementPanel(achievement));
            ideSettings.add(Box.createVerticalStrut(7));
        }

        root.add(projectSetings, "grow");
        root.add(ideSettings, "grow");
    }

    private JPanel getAchievementPanel(@NotNull Achievement achievement) {
        final JPanel panel = new JPanel();
        panel.setBackground(JBColor.GRAY);
        panel.setPreferredSize(new Dimension(0, 50));
        panel.setToolTipText(achievement.getText());

        if (achievement.getCurrentState().compareTo(achievement.getMatchingState()) > 0) {
            JLabel label = new JLabel(achievement.getName() + " (Achieved)");
            label.setDisplayedMnemonic('V');
            panel.add(label, "wrap");
        } else {
            //UIManager.put("ProgressBar.background", Color.GREEN);
            //UIManager.put("ProgressBar.foreground", Color.GREEN);
            //UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
            //UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
            JProgressBar jProgressBar = new JProgressBar(0, (int) achievement.getMatchingState());
            System.out.println(achievement);
            jProgressBar.setValue((int) achievement.getCurrentState());
            jProgressBar.setStringPainted(true);
            jProgressBar.setString(achievement.getCurrentState() + "/" + achievement.getMatchingState());
            jProgressBar.setPreferredSize(new Dimension(700, 20));

            JLabel label = new JLabel(achievement.getName());
            label.setDisplayedMnemonic('V');
            label.setLabelFor(jProgressBar);
            panel.add(label);
            panel.add(jProgressBar);
        }
        return panel;
    }

    public JComponent createComponent() {
        return root;
    }

    public static void main(String[] args) {
        PluginSettingsForm form = new PluginSettingsForm();

        JFrame frame = new JFrame("Test: AfcSettingsForm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(form.createComponent());
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setVisible(true);
    }
}
