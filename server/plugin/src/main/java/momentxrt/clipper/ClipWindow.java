package momentxrt.clipper;

/**
 * Represents a clip time window around a detected moment.
 */
public class ClipWindow {

    private final long startMs;
    private final long endMs;

    public ClipWindow(long startMs, long endMs) {
        this.startMs = startMs;
        this.endMs = endMs;
    }

    public long getStartMs() {
        return startMs;
    }

    public long getEndMs() {
        return endMs;
    }

    @Override
    public String toString() {
        return "ClipWindow{" +
                "startMs=" + startMs +
                ", endMs=" + endMs +
                '}';
    }
}