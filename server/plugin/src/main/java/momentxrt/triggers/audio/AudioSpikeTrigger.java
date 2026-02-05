package momentxrt.triggers.audio;

import momentxrt.events.MomentEvent;
import momentxrt.triggers.Trigger;

/**
 * Mock audio spike trigger.
 * Simulates real-time audio level monitoring.
 */
public class AudioSpikeTrigger implements Trigger {

    private final double threshold;
    private double currentLevel = 0.0;
    private boolean triggered = false;

    public AudioSpikeTrigger(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Simulate incoming audio level (0.0 – 1.0).
     */
    public void updateLevel(double level) {
        this.currentLevel = level;
    }

    @Override
    public MomentEvent check() {
        if (!triggered && currentLevel >= threshold) {
            triggered = true;
            return new MomentEvent(
                    System.currentTimeMillis(),
                    "audio_spike",
                    currentLevel
            );
        }

        // reset once signal drops
        if (currentLevel < threshold * 0.5) {
            triggered = false;
        }

        return null;
    }

    @Override
    public String getName() {
        return "AudioSpikeTrigger";
    }
}