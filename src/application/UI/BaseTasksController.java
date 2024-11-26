package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.BL.Model.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BaseTasksController {
	
	 // Create a UI component (HBox) for each task
    protected HBox createTaskItem(Task task) {
        HBox taskItem = new HBox();
        taskItem.setSpacing(10);
        taskItem.setPadding(new Insets(10, 0, 0, 0)); // Top, Right, Bottom, Left

        // Create task details labels
        Label titleLabel = new Label(task.getTitle());
        titleLabel.getStyleClass().add("task-title");  // Add CSS class for title

        Label supervisorLabel = new Label(task.getSupervisorName());
        supervisorLabel.getStyleClass().add("task-supervisor");  // Add CSS class for supervisor

        String deadlineStr = task.getDeadline();  // Assuming deadline is a string like "2024-11-25 12:00:00.0"
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
        String formattedDeadline = deadline.format(outputFormatter);

        Label deadlineLabel = new Label(formattedDeadline);	   
        deadlineLabel.getStyleClass().add("task-deadline");  // Add CSS class for deadline

        Label statusLabel = new Label();
        String status = task.getStatus().toLowerCase();

        if (status.equals("missing")) {
            statusLabel.setText("MISSING"); // Set label text to "MISSING"
            statusLabel.getStyleClass().add("task-status-missing");
        } else if (status.equals("pending")) {
            statusLabel.setText("PENDING"); // Set label text to "PENDING"
            statusLabel.getStyleClass().add("task-status-pending");
        } else if (status.equals("inprogress")) {
            statusLabel.setText("IN PROGRESS"); // Set label text to "IN PROGRESS" with a space
            statusLabel.getStyleClass().add("task-status-inprogress");
        } else if (status.equals("completed")) {
            statusLabel.setText("COMPLETED"); // Set label text to "COMPLETED"
            statusLabel.getStyleClass().add("task-status-completed");
        }

        statusLabel.getStyleClass().add("task-status"); // Add base class for shared styles
        
        Label spaceLabel = new Label("");
        spaceLabel.getStyleClass().add("space-label");
       
        // Add labels to HBox
        taskItem.getChildren().addAll(titleLabel, supervisorLabel, deadlineLabel, statusLabel, spaceLabel);

        return taskItem;
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
