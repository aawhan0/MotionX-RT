package momentxrt;

import momentxrt.events.EventBus;
import momentxrt.events.MomentEvent;
import momentxrt.triggers.Trigger;
import momentxrt.triggers.manual.ManualTrigger;
import momentxrt.triggers.audio.AudioSpikeTrigger;
import momentxrt.events.MarkerEmitter;
import momentxrt.clipper.ClipWindow;
import momentxrt.clipper.ClipWindowCalculator;
import momentxrt.ui.ConsoleVisualizer;
import momentxrt.timeline.Timeline;
import momentxrt.timeline.TimelineEntry;
import java.util.List;

/**
 * Entry point for MomentX RT plugin.
 * Hackathon-safe minimal implementation.
 */
public class MomentXRTPlugin {

    // Core components (ORDER MATTERS)
    private final EventBus eventBus = new EventBus();
    private final ManualTrigger manualTrigger = new ManualTrigger();
    private final AudioSpikeTrigger audioTrigger = new AudioSpikeTrigger(0.7);
    private final PluginConfig config = PluginConfig.demoConfig();
    private final MarkerEmitter markerEmitter = new MarkerEmitter();
    private final ConsoleVisualizer visualizer = new ConsoleVisualizer(false);
    private final Timeline timeline = new Timeline();

    private final List<Trigger> triggers = List.of(
            manualTrigger,
            audioTrigger
    );

    private final ClipWindowCalculator clipCalculator =
        new ClipWindowCalculator(5000, 7000);

    public void start() {
        System.out.println("MomentX RT plugin started");

        eventBus.registerHandler(this::handleMoment);

        // Demo: run a simple trigger loop
        for (int i = 0; i < 5; i++) {

            // Simulate audio levels
            double simulatedLevel = (i == 3) ? 0.9 : 0.2;
            audioTrigger.updateLevel(simulatedLevel);

            pollTriggers();

            // Fire manual trigger once in the middle
            if (i == 2) {
                manualTrigger.fire();
            }

            sleep(500);
        }

        stop();
    }

    public void stop() {
        System.out.println("MomentX RT plugin stopped");
        timeline.printSummary();
        timeline.printJson();
    }

    private void pollTriggers() {
        for (Trigger trigger : triggers) {
            if (!config.isTriggerEnabled(trigger.getName())) {
                    continue;
            }
            
            MomentEvent event = trigger.check();
            if (event != null) {
                eventBus.publish(event);
            }
        }
    }

    private void handleMoment(MomentEvent event) {
        ClipWindow window = clipCalculator.calculate(event);
        
        visualizer.showMoment(event, window);
        markerEmitter.emit(event);
        timeline.add(new TimelineEntry(event, window));
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        new MomentXRTPlugin().start();
    }
}