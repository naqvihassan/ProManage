package application.UI.Admin;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.BL.MeetingsBL;
import application.BL.Model.Meeting;
import application.UI.BaseMeetingDetailController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminMeetingDetailsController extends BaseMeetingDetailController{

		private static int meeting_id;
	    @FXML
	    private Label title;
	    @FXML
	    private Label admin;
	    @FXML
	    private Label deadline;
	    @FXML
	    private Label description;
	    @FXML
	    private Label requestLabel;

	    @FXML
	    private Button back;
	    @FXML
	    private Button accept;
	    @FXML
	    private Button decline;
	    @FXML
	    private Button complete;

	    private AdminUIController dcontroller;

	    // Set the DashboardController reference
	    public void setDashboardController(AdminUIController controller) {
	        this.dcontroller = controller;
	    }

	    // Load meeting details into the UI
	    public void LoadMeetingDetails(int id) throws SQLException {
	    	meeting_id = id;
	        Meeting t = MeetingsBL.getMeetingbyMeetingid(id);
	        if (t != null) {
	        		
	        ShowMeetingDetail(title, admin, deadline, description, t);
	        if(!t.getStatus().toLowerCase().equals("scheduled")) {
	        	complete.setText("Marked as completed");
	        	complete.setDisable(true);
	        	complete.setStyle("-fx-background-color: white;" +
	                    "-fx-border-color: #d3d3d3;" +
	                    "-fx-border-width: 1;" +
	                    "-fx-cursor: HAND;"  +
	                    "-fx-text-fill: black");

	        }
	        else {
	        	complete.setText("Mark as Complete");
	        }
	            
	            if (t.getRequest() == null || !t.getStatus().equals("scheduled") || ("declined".equals(t.getRequest()))) {
	                // Change the text
	                accept.setText("Accept");
	                decline.setText("Decline");

	                // Change the button styles
	                accept.setStyle(
	                    "-fx-background-color: white;" +
	                    "-fx-border-color: #d3d3d3;" +
	                    "-fx-border-width: 1;" +
	                    "-fx-cursor: HAND;"  +
	                    "-fx-text-fill: black"
	                );
	                decline.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
	                if ("declined".equals(t.getRequest())) {
	                	requestLabel.setText("Client's request was declined");
	                }
	                else {
	                requestLabel.setText("");
	                }

	                // Disable the button
	                accept.setDisable(true);
	                decline.setDisable(true);
	            }
	            
	            else {
	                accept.setText("Accept");
	                decline.setText("Decline");
	                decline.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
	                
	                requestLabel.setText("Client has requested to " + t.getRequest() + " the meeting.");
	               
	            }
	            
	        } else {
	            title.setText("Meeting not found.");
	        }
	    }
	  
	@FXML
	public void AcceptRequest() throws Exception {
		
		   int confirmResult = JOptionPane.showConfirmDialog(
	                null,
	                "Do you really want to accept the client's request?",
	                "Accept Request",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (confirmResult == JOptionPane.YES_OPTION) {
	            	 if (confirmResult == JOptionPane.YES_OPTION) {
	 	            	MeetingsBL.updateMeetingRequest("accepted", meeting_id);
	 	            	MeetingsBL.updateMeetingStatus("cancelled", meeting_id);
	 	            	accept.setDisable(true);
	 	            	decline.setDisable(true);
	 	            	accept.setStyle(
	 		                    "-fx-background-color: white;" +
	 		                    "-fx-border-color: #d3d3d3;" +
	 		                    "-fx-border-width: 1;" +
	 		                    "-fx-cursor: HAND;"  +
	 		                    "-fx-text-fill: black"
	 		                );
	 		                decline.setStyle(
	 			                    "-fx-background-color: white;" +
	 			                    "-fx-border-color: #d3d3d3;" +
	 			                    "-fx-border-width: 1;" +
	 			                    "-fx-cursor: HAND;"  +
	 			                    "-fx-text-fill: black"
	 			                );
	 		                	
	 		                complete.setDisable(true);
	 			        	complete.setStyle("-fx-background-color: white;" +
	 			                    "-fx-border-color: #d3d3d3;" +
	 			                    "-fx-border-width: 1;" +
	 			                    "-fx-cursor: HAND;"  +
	 			                    "-fx-text-fill: black");
	 		            Meeting m = MeetingsBL.getMeetingbyMeetingid(meeting_id);
	 		            requestLabel.setText("The meeting was " + m.getStatus() + " successfully on client's request.");
	 	            }
	            }
		
	}
	
	@FXML
	public void DeclineRequest() throws Exception {
		 int confirmResult = JOptionPane.showConfirmDialog(
	                null,
	                "Do you really want to decline the client's request?",
	                "Decline Request",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (confirmResult == JOptionPane.YES_OPTION) {
	            	MeetingsBL.updateMeetingRequest("declined", meeting_id);
	            	accept.setDisable(true);
	            	decline.setDisable(true);
	            	accept.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
		                decline.setStyle(
			                    "-fx-background-color: white;" +
			                    "-fx-border-color: #d3d3d3;" +
			                    "-fx-border-width: 1;" +
			                    "-fx-cursor: HAND;"  +
			                    "-fx-text-fill: black"
			                );
		                Meeting m = MeetingsBL.getMeetingbyMeetingid(meeting_id);
	 		            requestLabel.setText("Client's request to was declined.");
	            }
	}
	
	@FXML
	public void MarkAsComplete() throws Exception {
		 int confirmResult = JOptionPane.showConfirmDialog(
	                null,
	                "Do you really want to mark the meeting as complete?",
	                "Mark as Complete",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (confirmResult == JOptionPane.YES_OPTION) {
	            	MeetingsBL.updateMeetingStatus("completed", meeting_id);
	            	accept.setDisable(true);
	            	decline.setDisable(true);
	            	complete.setDisable(true);
	            	complete.setText("Marked as completed");
	            	complete.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
	            	accept.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
		                decline.setStyle(
			                    "-fx-background-color: white;" +
			                    "-fx-border-color: #d3d3d3;" +
			                    "-fx-border-width: 1;" +
			                    "-fx-cursor: HAND;"  +
			                    "-fx-text-fill: black"
			                );
	 		            requestLabel.setText("The meeting was marked as completed.");
	 		            
	            }

		
	}

	    // Go back to the previous pane
	    public void goBack() throws Exception {
	        // Use the existing DashboardController instance to navigate
	        if (dcontroller != null) {
	        	dcontroller.loadMeetingsPage(dcontroller);
	        }
	    }
}
