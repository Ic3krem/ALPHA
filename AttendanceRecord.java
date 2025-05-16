package main;

public class AttendanceRecord {
    private String userId;
    private String logId;
    private String date;
    private String checkIn;
    private String checkOut;

    public AttendanceRecord(String userId, String logId, String date, String checkIn, String checkOut) {
        this.userId = userId;
        this.logId = logId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getUserId() { return userId; }
    public String getLogId() { return logId; }
    public String getDate() { return date; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }
} 