package momentxrt.timeline;

import momentxrt.clipper.ClipWindow;
import momentxrt.events.MomentEvent;

/**
 * Represents one timeline entry (moment + clip).
 */
public class TimelineEntry {

    private final MomentEvent event;
    private final ClipWindow clipWindow;

    public TimelineEntry(MomentEvent event, ClipWindow clipWindow) {
        this.event = event;
        this.clipWindow = clipWindow;
    }

    public MomentEvent getEvent() {
        return event;
    }

    public ClipWindow getClipWindow() {
        return clipWindow;
    }

    @Override
    public String toString() {
        return "TimelineEntry{" +
                "trigger=" + event.getTriggerType() +
                ", startMs=" + clipWindow.getStartMs() +
                ", endMs=" + clipWindow.getEndMs() +
                '}';
    }
}