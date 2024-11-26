package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.BL.Model.Meeting;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BaseMeetingsController {
	
	 // Create a UI component (HBox) for each task
    protected HBox createMeetingItem(Meeting m) {
        HBox meetingItem = new HBox();
        meetingItem.setSpacing(10);
        meetingItem.setPadding(new Insets(10, 0, 0, 0)); // Top, Right, Bottom, Left
        
     // Create task details labels
        Label organizerLabel = new Label(m.getAdminName());
        organizerLabel.getStyleClass().add("meeting-organizer");  // Add CSS class for title

        // Create task details labels
        Label titleLabel = new Label(m.getPurpose());
        titleLabel.getStyleClass().add("meeting-purpose");  // Add CSS class for title

        String deadlineStr = m.getScheduledAt();  // Assuming deadline is a string like "2024-11-25 12:00:00.0"
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
        String formattedDeadline = deadline.format(outputFormatter);

        Label deadlineLabel = new Label(formattedDeadline);	   
        deadlineLabel.getStyleClass().add("meeting-deadline");  // Add CSS class for deadline

        Label duration = new Label();
        int totalMinutes = m.getDuration();
        int hours = totalMinutes / 60; // Calculate hours
        int minutes = totalMinutes % 60; // Calculate remaining minutes

        // Format the duration
        String formattedDuration = (hours > 0 ? hours + " hr " : "") + (minutes > 0 ? minutes + " min" : "");
        duration.setText(formattedDuration);
        duration.getStyleClass().add("meeting-duration");
        Label statusLabel = new Label();
        String status = m.getStatus().toLowerCase();
        System.out.println(status);

        if (status.equals("missing")) {
            statusLabel.setText("MISSING"); // Set label text to "MISSING"
            statusLabel.getStyleClass().add("task-status-missing");
        } else if (status.equals("pending")) {
            statusLabel.setText("PENDING"); // Set label text to "PENDING"
            statusLabel.getStyleClass().add("task-status-pending");
        } else if (status.equals("scheduled")) {
            statusLabel.setText("SCHEDULED"); // Set label text to "IN PROGRESS" with a space
            statusLabel.getStyleClass().add("task-status-inprogress");
        } else if (status.equals("completed")) {
            statusLabel.setText("COMPLETED"); // Set label text to "COMPLETED"
            statusLabel.getStyleClass().add("task-status-completed");
        }
        else if (status.equals("cancelled")) {
            statusLabel.setText("CANCELLED"); // Set label text to "COMPLETED"
            statusLabel.getStyleClass().add("meeting-status-cancelled");
        }

        statusLabel.getStyleClass().add("task-status"); // Add base class for shared styles
        
        Label spaceLabel = new Label("");
        spaceLabel.getStyleClass().add("meeting-space-label");
       
        // Add labels to HBox
        meetingItem.getChildren().addAll(organizerLabel,titleLabel,deadlineLabel,duration,statusLabel,spaceLabel);

        return meetingItem;	
    }
    
    protected void toggleActive(Button activeButton, Button... otherButtons) {
        // Set active button style
    	activeButton.setStyle("-fx-background-color: #4f46E5; -fx-text-fill: white; -fx-cursor: HAND; -fx-background-radius:8;");
        // Remove active style from other buttons
        for (Button button : otherButtons) {
        	button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-cursor: HAND; -fx-background-radius:8;");       
        	}
    }
    

}
