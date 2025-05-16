package main;

public class WorkoutLog {
    private String trackId;
    private String username;
    private String date;
    private String equipment;
    private String classification;

    public WorkoutLog(String trackId, String username, String date, String equipment, String classification) {
        this.trackId = trackId;
        this.username = username;
        this.date = date;
        this.equipment = equipment;
        this.classification = classification;
    }

    public String getTrackId() { return trackId; }
    public String getUsername() { return username; }
    public String getDate() { return date; }
    public String getEquipment() { return equipment; }
    public String getClassification() { return classification; }
} 