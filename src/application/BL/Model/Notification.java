package application.BL.Model;


public class Notification {
    private int id;
    private int generatedBy; 
    private int receivedBy;  
    private String message;  
    private String status;  
    private String createdAt; 

    // Default constructor
    public Notification() {
    }

    // Parameterized constructor
    public Notification(int id, int generatedBy, int receivedBy, String message, String status, String createdAt) {
        this.id = id;
        this.generatedBy = generatedBy;
        this.receivedBy = receivedBy;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(int generatedBy) {
        this.generatedBy = generatedBy;
    }

    public int getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(int receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", generatedBy=" + generatedBy +
                ", receivedBy=" + receivedBy +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
