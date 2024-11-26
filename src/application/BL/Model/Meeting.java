package application.BL.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Meeting {
    private int meetingId;
    private int clientId;
    private int adminId;
    private String purpose;
    private String description;
    private String status;
    private String scheduledAt;  // Changed to String for formatted date
    private int duration;
    private String request;
    private String createdAt;  // Changed to String for formatted date
    private String clientName;
    private String adminName;

    // Constructor
    public Meeting(int meetingId, int clientId, int adminId, String purpose, String description, String status, 
                   String scheduledAt, int duration, String request, String createdAt, String clientName, String adminName) {
        this.meetingId = meetingId;
        this.clientId = clientId;
        this.adminId = adminId;
        this.purpose = purpose;
        this.description = description;
        this.status = status;
        this.scheduledAt = scheduledAt;
        this.duration = duration;
        this.request = request;
        this.createdAt = createdAt;
        this.clientName = clientName;
        this.adminName = adminName;
        
        this.toString();
    }

    // Getters and Setters
    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(String scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    // Override toString method for better object representation
    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", clientId=" + clientId +
                ", adminId=" + adminId +
                ", purpose='" + purpose + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", scheduledAt='" + scheduledAt + '\'' +
                ", duration=" + duration +
                ", request='" + request + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", clientName='" + clientName + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }
    
    // Utility method to format Date to String (for scheduledAt and createdAt)
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }
}
