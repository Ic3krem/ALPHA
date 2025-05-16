package main;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDatabase {
    private static List<AttendanceRecord> records = new ArrayList<>();

    static {
        // Sample data
        records.add(new AttendanceRecord("001", "A001", "2025-05-10", "08:30", "17:00"));
        records.add(new AttendanceRecord("002", "A002", "2025-05-10", "09:00", "17:30"));
        records.add(new AttendanceRecord("003", "A003", "2025-05-10", "08:45", "16:45"));
        records.add(new AttendanceRecord("001", "A004", "2025-05-11", "08:35", "17:10"));
    }

    public static List<AttendanceRecord> getAllRecords() {
        return records;
    }
} 