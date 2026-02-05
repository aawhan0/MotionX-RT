package momentxrt.events;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Emits timeline markers for detected moments.
 * Hackathon-safe: console JSON output.
 */
public class MarkerEmitter {

    public void emit(MomentEvent event) {
        Map<String, Object> marker = new HashMap<>();
        marker.put("type", "moment");
        marker.put("trigger", event.getTriggerType());
        marker.put("confidence", event.getConfidence());
        marker.put("timestampMs", event.getTimestampMs());
        marker.put("isoTime", Instant.ofEpochMilli(event.getTimestampMs()).toString());

        System.out.println("[MARKER] " + marker);
    }
}