package main;

import java.util.ArrayList;
import java.util.List;

public class BodyStatsDatabase {
    private static List<BodyStatsRecord> records = new ArrayList<>();

    static {
        // Sample data
        records.add(new BodyStatsRecord("john.doe", "B001", "2025-05-10", "23.5", "Normal"));
        records.add(new BodyStatsRecord("jane.smith", "B002", "2025-05-10", "27.2", "Overweight"));
        records.add(new BodyStatsRecord("mike.johnson", "B003", "2025-05-10", "21.8", "Normal"));
        records.add(new BodyStatsRecord("john.doe", "B004", "2025-05-11", "23.7", "Normal"));
    }

    public static List<BodyStatsRecord> getAllRecords() {
        return records;
    }
} 