package application.BL.Model;

import java.sql.Timestamp;

public class Task {
    private final int taskId;
    private final String title;
    private String status;
    private final String description;
    private final byte [] data;
    private final String type;
    private final String name;
    private final String deadline;
    private final String supervisorName;
    private final String created_at;

    public Task(int taskId, String title, String status, String deadline, String desc, byte[] d, String t, String n, String supervisorName, String ctime) {
        this.taskId = taskId;
        this.title = title;
        this.status = status;
        this.description = desc;
        this.data = d;
        this.type = t;
        this.name = n;
        this.deadline = deadline;
        this.supervisorName = supervisorName;
        this.created_at = ctime;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
    
    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }
    
    public byte[] getData() {
    	return data;
    }
    
    public String getType() {
    	return type;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getCreatedAt() {
        return created_at;
    }

    public String getSupervisorName() {
        return supervisorName;
    }
    
    public void setStatus(String s) {
    	this.status = s;
    }
    
}
