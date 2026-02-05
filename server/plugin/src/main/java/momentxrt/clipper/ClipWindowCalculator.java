package momentxrt.clipper;

import momentxrt.events.MomentEvent;

/**
 * Calculates clip windows around detected moments.
 */
public class ClipWindowCalculator {

    private final long preRollMs;
    private final long postRollMs;

    public ClipWindowCalculator(long preRollMs, long postRollMs) {
        this.preRollMs = preRollMs;
        this.postRollMs = postRollMs;
    }

    public ClipWindow calculate(MomentEvent event) {
        long start = Math.max(0, event.getTimestampMs() - preRollMs);
        long end = event.getTimestampMs() + postRollMs;
        return new ClipWindow(start, end);
    }
}