package eu.andret.intelliachievements;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

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
		return "Displays the Achievements available to get";
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
		// does nothing
	}

	@Override
	public void reset() {
		// does nothing
	}

	@Override
	public void disposeUIResources() {
		// does nothing
	}
}
