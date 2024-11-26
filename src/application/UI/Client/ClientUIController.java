package application.UI.Client;

import java.io.IOException;
import java.sql.SQLException;

import application.BL.NotificationsBL;
import application.DB.UserDB;
import application.UI.LoginController;
import application.UI.Client.MeetingsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientUIController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;
    @FXML
    private AnchorPane dynamicPane;

    // Cache the loaded Tasks pane to avoid reloading it unnecessarily
    private AnchorPane tasksPane;
    
    private AnchorPane meetingsPane;
    private AnchorPane accountPane;
    private AnchorPane dashboardPane;
    private AnchorPane notificationsPane;
    @FXML
    private Label notificationsDot;
    private boolean notify;
    @FXML
    private Label name;
    
    private static int DetailedTaskId;
    
    @FXML
    public void initialize() throws Exception {
        toggleActive(button1, button2, button3, button4, button5);
        renderName();
        loadPane1();
        checkNotifications();
    }
    
    public void checkNotifications() {
    	notify = NotificationsBL.getNotificationCount(LoginController.getLoggedInUserId());
    	if(!notify) {
    		notificationsDot.setText("");
    	}
    	else {
    		notificationsDot.setText("•");
    		notificationsDot.setStyle("-fx-text-fill: red");
    	}
    }
    
    public void renderName() {
    	name.setText(UserDB.getUserbyUserId(LoginController.getLoggedInUserId()).getName());
    }

    // Method to load Client Dashboard pane
    public void loadPane1() throws IOException {
    	//if (dashboardPane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Client/ClientDashboard.fxml"));
            dashboardPane = loader.load();

            // Access the controller of the loaded FXML
            DashboardController tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
        //}
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(dashboardPane);
        checkNotifications();

    }

    // Method to load Notifications pane into the dynamic pane
    public void loadPane2() throws Exception {
    	   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Client/Notifications.fxml"));
           notificationsPane = loader.load();

           // Access the controller of the loaded FXML
           NotificationsController tc = new NotificationsController();
           tc = loader.getController();
           
           // Pass the reference of the current DashboardController to the TasksController
           tc.setDashboardController(this);
           
           tc.loadAll();
           dynamicPane.getChildren().clear();
           dynamicPane.getChildren().add(notificationsPane);
           checkNotifications();

    }

    // Method to load Tasks pane into the dynamic pane
    public void loadPane3() throws Exception {
        if (tasksPane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Client/Tasks.fxml"));
            tasksPane = loader.load();

            // Access the controller of the loaded FXML
            TasksController tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
            tc.loadTasks();
        }
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(tasksPane);
        checkNotifications();
    }
    
    public void loadTaskPage(ClientUIController dc) throws Exception {
             FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Client/Tasks.fxml"));
             tasksPane = loader.load();

             // Access the controller of the loaded FXML
             TasksController tc = new TasksController();
             tc = loader.getController();
             
             // Pass the reference of the current DashboardController to the TasksController
             tc.setDashboardController(this);
             tc.loadTasks();
    	 
    	 
         dc.dynamicPane.getChildren().clear();
         dc.dynamicPane.getChildren().add(tasksPane);
         checkNotifications();

     }
    
	    public void loadMeetingsPage(ClientUIController dc) throws Exception {
	        FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Client/Meetings.fxml"));
	        meetingsPane = loader.load();
	
	        // Access the controller of the loaded FXML
	        MeetingsController tc = new MeetingsController();
	        tc = loader.getController();
	        
	        // Pass the reference of the current DashboardController to the TasksController
	        tc.setDashboardController(this);
	        tc.loadMeetings();
		 
		 
	    dc.dynamicPane.getChildren().clear();
	    dc.dynamicPane.getChildren().add(meetingsPane);
        checkNotifications();

	}

    // Method to load Meetings pane into the dynamic pane
    public void loadPane4() throws Exception {
    	 if (meetingsPane == null) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Client/Meetings.fxml"));
             meetingsPane = loader.load();

             // Access the controller of the loaded FXML
             MeetingsController tc = loader.getController();
             
             // Pass the reference of the current DashboardController to the TasksController
             tc.setDashboardController(this);
             
             tc.loadMeetings();
         }
         dynamicPane.getChildren().clear();
         dynamicPane.getChildren().add(meetingsPane);
         checkNotifications();

     }

    // Method to load Account Management pane into the dynamic pane
    public void loadPane5() throws IOException {
    	 if (accountPane == null) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Client/ManageAccount.fxml"));
             accountPane = loader.load();

             // Access the controller of the loaded FXML
             ManageAccountController tc = loader.getController();
             
             // Pass the reference of the current DashboardController to the TasksController
             tc.setDashboardController(this);
         }
         dynamicPane.getChildren().clear();
         dynamicPane.getChildren().add(accountPane);
         checkNotifications();

    }

    // Method to load any pane dynamically into the dynamicPane
    private void loadDynamicPane(String fxmlFile) throws IOException {
        try {
            // Load the FXML and set it in the dynamicPane
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            dynamicPane.getChildren().clear();
            dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace(); // Debugging purpose
            throw e; // Propagate the exception
        }
    }

    // Method to toggle active button style
    private void toggleActive(Button activeButton, Button... otherButtons) {
        // Set active button style
        activeButton.getStyleClass().add("active");
        // Remove active style from other buttons
        for (Button button : otherButtons) {
            button.getStyleClass().remove("active");
        }

    }

    // Event handler for Button 1
    @FXML
    private void handleButton1Click(ActionEvent event) throws IOException {
        // Clear previous content
        toggleActive(button1, button2, button3, button4, button5);
        // Load new content (for Button 1)
        loadPane1();
    }

    // Event handler for Button 2
    @FXML
    private void handleButton2Click(ActionEvent event) throws Exception {
        // Clear previous content
        toggleActive(button2, button5, button1, button3, button4);
        loadPane2();
    }

    // Event handler for Button 3
    @FXML
    private void handleButton3Click(ActionEvent event) throws Exception {
        // Clear previous content
        toggleActive(button3, button2, button1, button4, button5);
        loadPane3();
    }

    // Event handler for Button 4
    @FXML
    private void handleButton4Click(ActionEvent event) throws Exception {
        // Clear previous content
        toggleActive(button4, button2, button1, button3, button5);
        loadPane4();
    }

    // Event handler for Button 5
    @FXML
    private void handleButton5Click(ActionEvent event) throws IOException {
        toggleActive(button5, button2, button1, button3, button4);
        loadPane5();
    }
    
    public void setDetailedTaskId(int id, ClientUIController controller) {
        DetailedTaskId = id;
        try {
            // Load the FXML and set it in the dynamicPane
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/application/UI/Client/TaskDetails.fxml"));
            AnchorPane pane = loader.load();
            
            TaskDetailsController tdc = loader.getController();
            tdc.setDashboardController(this);
            tdc.LoadTaskDetails(id);
            // Access the non-static dynamicPane from the controller instance
            controller.dynamicPane.getChildren().clear();
            controller.dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace(); // Debugging purpose
            throw new RuntimeException("Error loading TaskDetails.fxml", e);
        }
        checkNotifications();

    }
    
    public void setDetailedMeetingId(int id, ClientUIController controller) throws SQLException {
        try {
            // Load the FXML and set it in the dynamicPane
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/application/UI/Client/MeetingDetails.fxml"));
            AnchorPane pane = loader.load();
            
            MeetingDetailsController tdc = loader.getController();
            tdc.setDashboardController(this);
            tdc.LoadMeetingDetails(id);
            // Access the non-static dynamicPane from the controller instance
            controller.dynamicPane.getChildren().clear();
            controller.dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace(); // Debugging purpose
            throw new RuntimeException("Error loading MeetingDetails.fxml", e);
        }
        checkNotifications();

    }
    
    @FXML
    private void LogOut(ActionEvent event) {
    	 try {
             // Load the FXML file using the package structure
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/UI/Login.fxml"));
             Parent root = fxmlLoader.load();

             // Close the current stage
             Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             currentStage.close();

             // Open a new stage
             Stage newStage = new Stage();
             newStage.setTitle("Login Page");

             Scene scene = new Scene(root);
             scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
             newStage.setScene(scene);
             newStage.setMaximized(true);
             newStage.show();

         } catch (Exception e) {
             e.printStackTrace();
             showAlert("Error", "Unable to load the dashboard.", Alert.AlertType.ERROR);
         }
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
