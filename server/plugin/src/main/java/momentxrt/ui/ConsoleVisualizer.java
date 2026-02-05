package momentxrt.ui;

import momentxrt.clipper.ClipWindow;
import momentxrt.events.MomentEvent;

/**
 * Console-based visual output for MomentX RT.
 * Supports ASCII-safe and Emoji modes.
 */
public class ConsoleVisualizer {

    private final boolean emojiMode;

    public ConsoleVisualizer(boolean emojiMode) {
        this.emojiMode = emojiMode;
    }

    public void showMoment(MomentEvent event, ClipWindow window) {
        if (emojiMode) {
            showEmoji(event, window);
        } else {
            showAscii(event, window);
        }
    }

    // ===================== EMOJI MODE =====================

    private void showEmoji(MomentEvent event, ClipWindow window) {
        System.out.println();
        System.out.println("══════════════════════════════════════════════");
        System.out.println("🎯  MOMENT DETECTED");
        System.out.println("──────────────────────────────────────────────");
        printBody(event, window);
        System.out.println("══════════════════════════════════════════════");
        System.out.println();
    }

    // ===================== ASCII MODE =====================

    private void showAscii(MomentEvent event, ClipWindow window) {
        System.out.println();
        System.out.println("================================================");
        System.out.println("[ MOMENT DETECTED ]");
        System.out.println("------------------------------------------------");
        printBody(event, window);
        System.out.println("================================================");
        System.out.println();
    }

    // ===================== SHARED BODY =====================

    private void printBody(MomentEvent event, ClipWindow window) {
        System.out.println(" Trigger     : " + event.getTriggerType());
        System.out.println(" Confidence  : " + event.getConfidence());
        System.out.println(" Timestamp   : " + event.getTimestampMs());
        System.out.println();
        System.out.println(" Clip Start  : " + window.getStartMs());
        System.out.println(" Clip End    : " + window.getEndMs());
        System.out.println(" Duration   : " +
                (window.getEndMs() - window.getStartMs()) + " ms");
    }
}