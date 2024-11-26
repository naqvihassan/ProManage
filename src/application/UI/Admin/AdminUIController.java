package application.UI.Admin;

import application.BL.NotificationsBL;
import application.DB.UserDB;
import application.UI.BaseUIController;
import application.UI.LoginController;
import application.UI.Client.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;

public class AdminUIController extends BaseUIController {
    @FXML
    private Button button1, button2, button3, button4, button5, button6;
    @FXML
    private AnchorPane dynamicPane;
    
    private AnchorPane tasksPane;
    private AnchorPane notificationsPane;
    private AnchorPane meetingsPane;
    private AnchorPane accountPane;
    private AnchorPane dashboardPane;
    private AnchorPane createAccountPane;
    @FXML
    private Label notificationsDot;
    private boolean notify;
    @FXML
    private Label name;

    @FXML
    @Override
    public void initialize() throws Exception {
        toggleActive(button1, button2, button3, button4, button5, button6);
        renderName();
        loadPane1();
        checkNotifications();
    }
    
    public void renderName() {
    	name.setText(UserDB.getUserbyUserId(LoginController.getLoggedInUserId()).getName());
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

    public void loadPane1() throws IOException {
    	//if (dashboardPane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/AdminDashboard.fxml"));
            dashboardPane = loader.load();

            // Access the controller of the loaded FXML
            AdminDashboardController tc = new AdminDashboardController();
            tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
        //}
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(dashboardPane);
        checkNotifications();

    }

    public void loadPane2() throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/AdminNotifications.fxml"));
            notificationsPane = loader.load();

            // Access the controller of the loaded FXML
            AdminNotificationsController tc = new AdminNotificationsController();
            tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            tc.loadAll();
            
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(notificationsPane);
        checkNotifications();
    }

    public void loadPane3() throws Exception {
        if (tasksPane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/AdminTask.fxml"));
            tasksPane = loader.load();

            // Access the controller of the loaded FXML
            AdminTasksController tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
            tc.loadTasks();
        }
        
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(tasksPane);
        checkNotifications();
    }

    public void loadPane4() throws Exception {
    	 if (meetingsPane == null) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/AdminMeetings.fxml"));
             meetingsPane = loader.load();

             // Access the controller of the loaded FXML
             AdminMeetingsController tc = loader.getController();
             
             // Pass the reference of the current DashboardController to the TasksController
             tc.setDashboardController(this);
             
             tc.loadMeetings();
         }
         dynamicPane.getChildren().clear();
         dynamicPane.getChildren().add(meetingsPane);
         checkNotifications();

    }

    public void loadPane5() throws IOException {
    	if (accountPane == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/AdminManageAccount.fxml"));
            accountPane = loader.load();

            // Access the controller of the loaded FXML
            AdminManageAccountController tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
        }
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(accountPane);
        checkNotifications();
    }

    public void loadPane6() throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/UI/Admin/CreateUser.fxml"));
            createAccountPane = loader.load();

            // Access the controller of the loaded FXML
            CreateUserController tc = loader.getController();
            
            // Pass the reference of the current DashboardController to the TasksController
            tc.setDashboardController(this);
            
        dynamicPane.getChildren().clear();
        dynamicPane.getChildren().add(createAccountPane);
        checkNotifications();
    }

    public void loadTaskPage(AdminUIController dc) throws Exception {
        FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Admin/AdminTask.fxml"));
        tasksPane = loader.load();

        // Access the controller of the loaded FXML
        AdminTasksController tc = new AdminTasksController();
        tc = loader.getController();
        
        // Pass the reference of the current DashboardController to the TasksController
        tc.setDashboardController(this);
        tc.loadTasks();
        checkNotifications();
    
    
    dc.dynamicPane.getChildren().clear();
    dc.dynamicPane.getChildren().add(tasksPane);
}
    
    public void loadMeetingsPage(AdminUIController dc) throws Exception {
        FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Admin/AdminMeetings.fxml"));
        meetingsPane = loader.load();

        // Access the controller of the loaded FXML
        AdminMeetingsController tc = new AdminMeetingsController();
        tc = loader.getController();
        
        // Pass the reference of the current DashboardController to the TasksController
        tc.setDashboardController(this);
        tc.loadMeetings();
        checkNotifications();
    
    
    dc.dynamicPane.getChildren().clear();
    dc.dynamicPane.getChildren().add(meetingsPane);
}
    
    

	public void loadAssignTaskPage(AdminUIController dc) throws Exception {
	    FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Admin/AssignTasks.fxml"));
	    tasksPane = loader.load();
	
	    // Access the controller of the loaded FXML
	    AssignTaskController tc = new AssignTaskController();
	    tc = loader.getController();
	    
	    // Pass the reference of the current DashboardController to the TasksController
	    tc.setDashboardController(this);
	 
	 
	dc.dynamicPane.getChildren().clear();
	dc.dynamicPane.getChildren().add(tasksPane);
    checkNotifications();

	}
	
	public void loadScheduleMeetingPage(AdminUIController dc) throws Exception {
	    FXMLLoader loader = new FXMLLoader(dc.getClass().getResource("/application/UI/Admin/AdminScheduleMeeting.fxml"));
	    meetingsPane = loader.load();
	
	    // Access the controller of the loaded FXML
	    ScheduleMeetingController tc = new ScheduleMeetingController();
	    tc = loader.getController();
	    
	    // Pass the reference of the current DashboardController to the TasksController
	    tc.setDashboardController(this);
        checkNotifications();
	 
	dc.dynamicPane.getChildren().clear();
	dc.dynamicPane.getChildren().add(meetingsPane);
	}

    @FXML
    private void handleButton1Click(ActionEvent event) throws IOException {
        toggleActive(button1, button2, button3, button4, button5, button6);
        loadPane1();
    }

    @FXML
    private void handleButton2Click(ActionEvent event) throws Exception {
        toggleActive(button2, button1, button3, button4, button5, button6);
        loadPane2();
    }

    @FXML
    private void handleButton3Click(ActionEvent event) throws Exception {
        toggleActive(button3, button2, button1, button4, button5, button6);
        loadPane3();
    }

    @FXML
    private void handleButton4Click(ActionEvent event) throws Exception {
        toggleActive(button4, button2, button3, button1, button5, button6);
        loadPane4();
    }

    @FXML
    private void handleButton5Click(ActionEvent event) throws IOException {
        toggleActive(button5, button2, button3, button4, button1, button6);
        loadPane5();
    }
    @FXML
    private void handleButton6Click(ActionEvent event) throws IOException {
        toggleActive(button6, button2, button3, button4, button5, button1);
        loadPane6();
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

    public void setDetailedTaskId(int id, AdminUIController controller) {
        try {
            // Load the FXML and set it in the dynamicPane
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/application/UI/Admin/AdminTaskDetails.fxml"));
            AnchorPane pane = loader.load();
            
            AdminTaskDetailsController tdc = loader.getController();
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
    
    public void setDetailedMeetingId(int id, AdminUIController controller) throws SQLException {
        try {
            // Load the FXML and set it in the dynamicPane
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/application/UI/Admin/AdminMeetingDetails.fxml"));
            AnchorPane pane = loader.load();
            
            AdminMeetingDetailsController tdc = loader.getController();
            tdc.setDashboardController(this);
            tdc.LoadMeetingDetails(id);
            // Access the non-static dynamicPane from the controller instance
            controller.dynamicPane.getChildren().clear();
            controller.dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace(); // Debugging purpose
            throw new RuntimeException("Error loading TaskDetails.fxml", e);
        }
        checkNotifications();

    }
}
