package eu.andret.intelliachievements;

import com.intellij.openapi.options.Configurable;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

public class PluginConfigurable implements Configurable {
    private final PluginSettingsForm settingsForm = new PluginSettingsForm();

    @Nls
    @Override
    public String getDisplayName() {
        return "IntelliJ Achievements";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "Displays the Achievements availale to get";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingsForm.getRootComponent();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }
}
