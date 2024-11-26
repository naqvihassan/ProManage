package application.UI.Admin;

import java.sql.SQLException;
import java.util.List;

import application.BL.MeetingsBL;
import application.BL.Model.Meeting;
import application.UI.BaseMeetingsController;
import application.UI.LoginController;
import application.UI.MeetingsLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminMeetingsController extends BaseMeetingsController implements Schedulable, MeetingsLoader{
    @FXML
    private VBox taskListContainer;  // VBox to hold tasks in the "Tasks.fxml" pane
    @FXML
    private Button allfilter;
    @FXML
    private Button missingfilter;
    @FXML
    private Button cancelledfilter;
    @FXML
    private Button scheduledfilter;
    @FXML
    private Button completedfilter;
   
    
    private AdminUIController dashboardController; // Reference to DashboardController
    
    // Method to set the DashboardController instance
    public void setDashboardController(AdminUIController controller) {
        this.dashboardController = controller;
    }
    
    @Override
    public void ScheduleMeeting() throws Exception {
    	dashboardController.loadScheduleMeetingPage(dashboardController);
    }
    
    @Override
    public void loadMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(allfilter, missingfilter, completedfilter, scheduledfilter, cancelledfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getAllMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
            		System.out.println(meeting.getMeetingId());
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
   
    @Override
    public void loadMissingMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(missingfilter, allfilter, completedfilter, scheduledfilter, cancelledfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getMissingMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    @Override
    public void loadScheduledMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(scheduledfilter, missingfilter, completedfilter, allfilter, cancelledfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getScheduledMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
            		
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    @Override
    public void loadCancelledMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(cancelledfilter, missingfilter, completedfilter, scheduledfilter, allfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getCancelledMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    
    @Override
    public void loadPendingMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(missingfilter, allfilter, completedfilter, scheduledfilter, cancelledfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getPendingMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    
    @Override
    public void loadCompletedMeetings() throws Exception {
    	MeetingsBL.updateMeetingStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(completedfilter, missingfilter, allfilter, scheduledfilter, cancelledfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Meeting> meetings = MeetingsBL.getCompletedMeetings(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of meetings: " + meetings.size());
        
        // Create and add each task to the task list container
        for (Meeting meeting : meetings) {
            HBox meetingItem = createMeetingItem(meeting);
            taskListContainer.getChildren().add(meetingItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
            	try {
            		System.out.println("Meeting Id"+ meeting.getMeetingId());
					openMeeting(meeting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            meetingItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }

    private void openMeeting(Meeting meeting) throws SQLException {
 	   if (dashboardController != null) {
        	
        	dashboardController.setDetailedMeetingId(meeting.getMeetingId(), dashboardController);
        }
 }
  
}