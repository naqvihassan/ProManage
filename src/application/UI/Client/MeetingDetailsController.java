package application.UI.Client;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.BL.MeetingsBL;
import application.BL.Model.Meeting;
import application.UI.BaseMeetingDetailController;
import application.UI.Admin.AdminUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MeetingDetailsController extends BaseMeetingDetailController{

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
		    private Button requestButton;

		    private ClientUIController dcontroller;

		    // Set the DashboardController reference
		    public void setDashboardController(ClientUIController controller) {
		        this.dcontroller = controller;
		    }

		    // Load task details into the UI
		    public void LoadMeetingDetails(int id) throws SQLException {
		    	meeting_id = id;
		        Meeting t = MeetingsBL.getMeetingbyMeetingid(id);
		        if (t != null) {
		        		
		        ShowMeetingDetail(title, admin, deadline, description, t);
		            
		            if ((t.getRequest() == null && !t.getStatus().equals("scheduled"))|| (t.getRequest()!=null)) {
		                requestButton.setDisable(true);
		                requestButton.setStyle(
		                    "-fx-background-color: white;" +
		                    "-fx-border-color: #d3d3d3;" +
		                    "-fx-border-width: 1;" +
		                    "-fx-cursor: HAND;"  +
		                    "-fx-text-fill: black"
		                );
		                
		                if ("declined".equals(t.getRequest())) {
		                	requestButton.setText("Requested");
		                	 requestLabel.setText("Your request was declined.");
		                }
		                else if ("accepted".equals(t.getRequest())){
		                	requestButton.setText("Requested");
		                requestLabel.setText("Your request was accepted");
		                }
		            }
		            
		            else {
		                requestButton.setText("Request for cancellation");
		            }
		            
		        } else {
		            title.setText("Meeting not found.");
		        }
		    }
		  
		@FXML
		public void RequestForCancellation() throws Exception {
			
			   int confirmResult = JOptionPane.showConfirmDialog(
		                null,
		                "Do you really want to request the admin to cancel the meeting?",
		                "Request Cancellation",
		                JOptionPane.YES_NO_OPTION
		            );

		            if (confirmResult == JOptionPane.YES_OPTION) {
		            	 if (confirmResult == JOptionPane.YES_OPTION) {
		 	            	MeetingsBL.updateMeetingRequest("cancel", meeting_id);
		 	            	requestButton.setText("Requested");
		 	            	requestButton.setDisable(true);
		 	            	requestButton.setStyle(
		 		                    "-fx-background-color: white;" +
		 		                    "-fx-border-color: #d3d3d3;" +
		 		                    "-fx-border-width: 1;" +
		 		                    "-fx-cursor: HAND;"  +
		 		                    "-fx-text-fill: black"
		 		                );
		 		               
		 		            requestLabel.setText("You have requested to cancel the meeting.");
		 	            }
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