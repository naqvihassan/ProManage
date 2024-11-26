package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.BL.NotificationsBL;
import application.BL.Model.Notification;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BaseNotificationsController {
	
	
	  public void loadNotification(VBox notifications, String s) throws Exception {
	    	
	        // Clear any existing tasks before loading new ones
	    	notifications.getChildren().clear();
	    	 List<Notification> n = null;
	        // Fetch tasks from the business logic layer (BL)
	    	 if(s.equals("all")) {
	    		 	n = NotificationsBL.getNotifications(LoginController.getLoggedInUserId());
	    	 }
	    	 else if(s.equals("unread")) {
	    		 	n = NotificationsBL.getUnreadNotifications(LoginController.getLoggedInUserId());
	    	 }
	    	 else if(s.equals("read")) {
	    		 	n = NotificationsBL.getReadNotifications(LoginController.getLoggedInUserId());
	    	 }

	        // Log the number of tasks
	        System.out.println("Number of tasks: " + n.size());
	        
	        // Create and add each task to the task list container
	        for (Notification ns : n) {
	        	Button actionButton = new Button("Mark as Read");
		        actionButton.getStyleClass().add("mark-as-read"); // Add CSS class for button
		        if ("read".equals(ns.getStatus())) {
		            actionButton.setDisable(true);
		            actionButton.setText("Marked as Read");
		            actionButton.setStyle("-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 0.1;" + 
		                    "-fx-text-fill: gray;");
		        }
	            HBox item = createNotificationItem(ns, actionButton);
	            notifications.getChildren().add(item);
	            // Set action to show task details
	            actionButton.setOnAction(event -> {
	                
	               markAsRead(ns);
	               actionButton.setDisable(true);
		            actionButton.setText("Marked as Read");
		            actionButton.setStyle("-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 0.1;" + 
		                    "-fx-text-fill: gray;");
		            item.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0.1 0;");
	              
	            });
	            item.getChildren().add(actionButton);
	        }
	        
	        // Force layout to refresh after adding tasks
	        notifications.layout();
	    }
	    
	    private HBox createNotificationItem(Notification n, Button b) {
	        HBox notificationItem = new HBox();
	        notificationItem.setSpacing(10);
	        HBox.setMargin(b, new Insets(0, 0, 10, 0)); // Adds bottom margin to the button
	        notificationItem.setPrefWidth(899); // Set preferred width to 600px
	        notificationItem.setPadding(new Insets(10, 0, 0, 0)); // Top, Right, Bottom, Left
	        notificationItem.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0.1 0;"); // Top, Right, Bottom, Left widths
	        if ("unread".equals(n.getStatus())) {
	            notificationItem.setStyle("-fx-background-color: lightpink; -fx-border-color: black; -fx-border-width: 0 0 0.1 0;");
	        } else {
	            notificationItem.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0.1 0;");
	        }
	        // Create task details labels
	        Label messageLabel = new Label(n.getMessage());
	        messageLabel.getStyleClass().add("notification-message");  // Add CSS class for title

	        String createdAt = n.getCreatedAt();  // Assuming deadline is a string like "2024-11-25 12:00:00.0"
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	        LocalDateTime created = LocalDateTime.parse(createdAt, inputFormatter);

	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
	        String formattedCreatedAt = created.format(outputFormatter);

	        Label createdAtLabel = new Label(formattedCreatedAt);	   
	        createdAtLabel.getStyleClass().add("notification-time");  // Add CSS class for deadline

	     
	        notificationItem.getChildren().addAll(messageLabel, createdAtLabel);

	        return notificationItem;
	    }
	    
	    private void markAsRead(Notification n) {
	    	NotificationsBL.markAsRead(n.getId());
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
