package momentxrt;

import java.util.Set;

/**
 * Runtime configuration for MomentX RT.
 * Hackathon-safe, no external dependencies.
 */
public class PluginConfig {

    private final Set<String> enabledTriggers;

    public PluginConfig(Set<String> enabledTriggers) {
        this.enabledTriggers = enabledTriggers;
    }

    public boolean isTriggerEnabled(String triggerName) {
        return enabledTriggers.contains(triggerName);
    }

    // Demo factory
    public static PluginConfig demoConfig() {
        return new PluginConfig(Set.of(
                "ManualTrigger",
                "AudioSpikeTrigger"
        ));
    }
}