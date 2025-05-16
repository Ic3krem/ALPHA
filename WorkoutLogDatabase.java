package main;

import java.util.ArrayList;
import java.util.List;

public class WorkoutLogDatabase {
    private static List<WorkoutLog> logs = new ArrayList<>();

    static {
        // Sample data
        logs.add(new WorkoutLog("W001", "john.doe", "2025-05-10", "Treadmill", "Cardio"));
        logs.add(new WorkoutLog("W002", "jane.smith", "2025-05-10", "Dumbbells", "Strength"));
        logs.add(new WorkoutLog("W003", "mike.johnson", "2025-05-10", "Stationary Bike", "Cardio"));
        logs.add(new WorkoutLog("W004", "john.doe", "2025-05-11", "Bench Press", "Strength"));
    }

    public static List<WorkoutLog> getAllLogs() {
        return logs;
    }
} 