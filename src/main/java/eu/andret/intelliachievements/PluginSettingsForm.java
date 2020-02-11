package eu.andret.intelliachievements;

import com.intellij.ui.components.JBScrollPane;
import eu.andret.intelliachievements.achievement.AchievementManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;

public class PluginSettingsForm {
	private JPanel root;

	public JComponent getRootComponent() {
		if (root == null) {
			root = new JPanel(new MigLayout("wrap 1", "[grow]"));
			JPanel achievementsPanel = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
			achievementsPanel.setBorder(BorderFactory.createTitledBorder("Achievements"));
			achievementsPanel.setLayout(new BoxLayout(achievementsPanel, BoxLayout.PAGE_AXIS));
			achievementsPanel.setMaximumSize(new Dimension(725, 800));
			achievementsPanel.setPreferredSize(new Dimension(725, AchievementManager.getAllAchievements().size() * 65));
			JPanel test = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
			test.setMaximumSize(new Dimension(715, 800));
			test.setPreferredSize(new Dimension(715, AchievementManager.getAllAchievements().size() * 60));
			test.setLayout(new BoxLayout(test, BoxLayout.PAGE_AXIS));
			JBScrollPane scrollPane = new JBScrollPane(test,
					JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			test.setAutoscrolls(true);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			AchievementManager.getAllAchievements().forEach(achievement -> {
				test.add(AchievementFactory.getAchievementPanel(achievement));
				test.add(Box.createVerticalStrut(5));
			});
			achievementsPanel.add(scrollPane);
			root.add(achievementsPanel, "grow");
		}
		return root;
	}

	public static void main(String[] args) {
		IntelliAchievements.beta();
		new IntelliAchievements().initializeComponent();
		PluginSettingsForm form = new PluginSettingsForm();

		JFrame frame = new JFrame("Test: IntelliAchievements SettingsForm");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(form.getRootComponent());
		frame.setSize(750, 500);
		frame.setLocation(200, 200);
		frame.setVisible(true);
	}
}
