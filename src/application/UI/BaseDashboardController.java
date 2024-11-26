package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.BL.MeetingsBL;
import application.BL.TasksBL;
import application.BL.Model.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BaseDashboardController {
	 protected void getData(Label tasksAssigned, Label tasksCompleted, Label meetingsScheduled, Label meetingsAttended, int days, Button active, Button... others) {
	    	toggleActive(active, others);
	    	tasksAssigned.setText(String.valueOf(TasksBL.getTasksCreatedCountByDays(days, LoginController.getLoggedInUserId())));
	    	tasksCompleted.setText(String.valueOf(TasksBL.getTasksCompletedCountByDays(days, LoginController.getLoggedInUserId())));
	    	meetingsScheduled.setText(String.valueOf(MeetingsBL.getMeetingsCreatedCountByDays(days, LoginController.getLoggedInUserId())));
	    	meetingsAttended.setText(String.valueOf(MeetingsBL.getMeetingsCompletedCountByDays(days, LoginController.getLoggedInUserId())));

	    }

		 protected void getTasks(VBox todayTasks, String s) {
			    // Clear the existing tasks from the container
			    todayTasks.getChildren().clear();

			    try {
			    	List<Task> tasks = null;
			        // Fetch tasks for the logged-in user from the business logic layer (BL)
			    	if(s.equals("week")) {
			    		tasks = TasksBL.getTasksDueNextWeek(LoginController.getLoggedInUserId());
			    	}
			    	else if(s.equals("today")){
			    		tasks = TasksBL.getTasksDueToday(LoginController.getLoggedInUserId());
			    	}

			        // Log the number of tasks fetched
			        System.out.println("Number of tasks: " + (tasks != null ? tasks.size() : 0));

			        if (tasks != null && !tasks.isEmpty()) {
			            // Create and add each task to the task list container
			            for (Task task : tasks) {
			                HBox taskItem = createTaskItem(task);
			                todayTasks.getChildren().add(taskItem);
			            }
			        } else {
			            Label empty = new Label();
			            empty.setText("No Tasks Due Today");
			        }
			    } catch (Exception e) {
			        // Log the exception for debugging purposes
			        System.err.println("Error fetching tasks: " + e.getMessage());
			        e.printStackTrace();
			    }
			}

		 
		 protected HBox createTaskItem(Task task) {
		        HBox taskItem = new HBox();
		        taskItem.setSpacing(10);
		        taskItem.setPadding(new Insets(10, 0, 0, 0)); // Top, Right, Bottom, Left

		        // Create task details labels
		        Label titleLabel = new Label(task.getTitle());
		        titleLabel.setStyle(
		        	    "-fx-font-size: 14px; " +
		        	    "-fx-font-family: Arial; " +
		        	    "-fx-text-fill: black; " +
		        	    "-fx-pref-width: 140px; " +
		        	    "-fx-wrap-text: true;"
		        	);

		        Label supervisorLabel = new Label(task.getSupervisorName());
		        supervisorLabel.getStyleClass().add("task-supervisor");  // Add CSS class for supervisor

		        String deadlineStr = task.getDeadline();  // Assuming deadline is a string like "2024-11-25 12:00:00.0"
		        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, inputFormatter);

		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
		        String formattedDeadline = deadline.format(outputFormatter);

		        Label deadlineLabel = new Label(formattedDeadline);	   
		        //deadlineLabel.getStyleClass().add("task-deadline");  // Add CSS class for deadline

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
		        
		        Label spaceLabel = new Label("");
		        spaceLabel.getStyleClass().add("-fx-pref-width: 30px;");
		        
		        Label spaceLabel2 = new Label("");
		        spaceLabel2.getStyleClass().add("-fx-pref-width: 20px;");

		        statusLabel.getStyleClass().add("task-status"); // Add base class for shared styles
		        

		       
		        // Add labels to HBox
		        taskItem.getChildren().addAll(spaceLabel, statusLabel,spaceLabel2, titleLabel, deadlineLabel);

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
