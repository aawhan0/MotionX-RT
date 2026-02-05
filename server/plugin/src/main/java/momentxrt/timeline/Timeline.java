package momentxrt.timeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In-memory timeline for detected moments.
 */
public class Timeline {

    private final List<TimelineEntry> entries = new ArrayList<>();



    
    public void add(TimelineEntry entry) {
        entries.add(entry);
    }




    public List<TimelineEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }





    public void printSummary() {
        System.out.println();
        System.out.println("========= TIMELINE SUMMARY =========");
        if (entries.isEmpty()) {
            System.out.println("(no moments detected)");
        } else {
            int i = 1;
            for (TimelineEntry entry : entries) {
                System.out.println(i++ + ". " + entry);
            }
        }
        System.out.println("===================================");
        System.out.println();
    }









    public void printJson() {
    System.out.println();
    System.out.println("========= TIMELINE JSON =========");
    System.out.println("[");

    for (int i = 0; i < entries.size(); i++) {
        TimelineEntry entry = entries.get(i);

        System.out.println("  {");
        System.out.println("    \"trigger\": \"" + entry.getEvent().getTriggerType() + "\",");
        System.out.println("    \"timestampMs\": " + entry.getEvent().getTimestampMs() + ",");
        System.out.println("    \"clipStartMs\": " + entry.getClipWindow().getStartMs() + ",");
        System.out.println("    \"clipEndMs\": " + entry.getClipWindow().getEndMs());
        System.out.print("  }");

        if (i < entries.size() - 1) {
            System.out.println(",");
        } else {
            System.out.println();
        }
    }

    System.out.println("]");
    System.out.println("================================");
    System.out.println();
}
}