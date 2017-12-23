package eu.andret.intelliachievements;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import eu.andret.intelliachievements.achievement.Achievement;
import eu.andret.intelliachievements.achievement.AchievementManager;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

public class MyToolWindowFactory implements ToolWindowFactory {
/*
private JPanel panel1;
private JTabbedPane tabbedPane1;
private JTextArea MainText;
private JList FileList;
private JPanel FilesTab;
private JPanel TextTab;
private JScrollPane TextPane;
*/

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        System.out.println("MyToolWindowFactory.createToolWindowContent");
        JPanel mainPanel = new JPanel(new MigLayout("", "[grow]", "[nogrid]"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        for (Achievement achievement : AchievementManager.getAllAchievements()) {
            mainPanel.add(getAchievementPanel(achievement));
            mainPanel.add(Box.createVerticalStrut(7));
        }

        //Создаем фабрику контента
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //Создаем контент(окно) с нашим GUI
        Content content = contentFactory.createContent(mainPanel, "", false);
        //Добавляем в IDE
        toolWindow.getContentManager().addContent(content);
    }

    @NotNull
    private static JPanel getAchievementPanel(Achievement achievement) {
        System.out.println("MyToolWindowFactory.getAchievementPanel");
        final JPanel panel = new JPanel();
        panel.setBackground(JBColor.GRAY);
        panel.setPreferredSize(new Dimension(0, 50));
        panel.setToolTipText(achievement.getText());

        if (achievement.getCurrentState() > achievement.getStates().get(achievement.getStates().size() - 1)) {
            JLabel label = new JLabel(achievement.getName() + " (Achieved)");
            label.setDisplayedMnemonic('V');
            panel.add(label, "wrap");
        } else {
            //UIManager.put("ProgressBar.background", Color.GREEN);
            //UIManager.put("ProgressBar.foreground", Color.GREEN);
            //UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
            //UIManager.put("ProgressBar.selectionForeground", Color.WHITE);
            JProgressBar jProgressBar = new JProgressBar(0, (int) achievement.getStates().get(achievement.getStates().size() - 1));

            jProgressBar.setValue((int) achievement.getCurrentState());
            jProgressBar.setStringPainted(true);
            jProgressBar.setString(achievement.getCurrentState() + "/" + achievement.getStates().get(achievement.getStates().size() - 1));
            jProgressBar.setPreferredSize(new Dimension(700, 20));

            JLabel label = new JLabel(achievement.getName());
            label.setDisplayedMnemonic('V');
            label.setLabelFor(jProgressBar);
            panel.add(label);
            panel.add(jProgressBar);
        }
        return panel;
    }
}
