package main;

public class BodyStatsRecord {
    private String username;
    private String recordId;
    private String date;
    private String bmi;
    private String classification;

    public BodyStatsRecord(String username, String recordId, String date, String bmi, String classification) {
        this.username = username;
        this.recordId = recordId;
        this.date = date;
        this.bmi = bmi;
        this.classification = classification;
    }

    public String getUsername() { return username; }
    public String getRecordId() { return recordId; }
    public String getDate() { return date; }
    public String getBmi() { return bmi; }
    public String getClassification() { return classification; }
} 