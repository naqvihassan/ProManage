package application.UI.Admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.BL.MeetingsBL;
import application.BL.TasksBL;
import application.BL.Model.Task;
import application.UI.BaseDashboardController;
import application.UI.LoginController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminDashboardController extends BaseDashboardController{
	
	@FXML
    private Label tasksAssigned;
    @FXML
    private Label tasksCompleted;
    @FXML
    private Label meetingsScheduled;
    @FXML
    private Label meetingsAttended;
    @FXML
    private VBox todayTasks;
    @FXML
    private VBox weekTasks;
    @FXML
    private Button first, second, third;
    
    private AdminUIController c;
    
    @FXML
    public void initialize() {
    	get7daysData();
    	getTodayTasks();
    	getWeekTasks();
    }
    public void setDashboardController(AdminUIController adminUIController) {
    	this.c = adminUIController;
    }

    public void get7daysData() {
    	getData(tasksAssigned, tasksCompleted, meetingsScheduled, meetingsAttended, 7, first, second, third);
    }

	 public void get30daysData() {
		 getData(tasksAssigned, tasksCompleted, meetingsScheduled, meetingsAttended, 30, second, first, third);
	    }
	 
	 public void get90daysData() {
		 getData(tasksAssigned, tasksCompleted, meetingsScheduled, meetingsAttended, 90, third, first, second);

	 }
	 
	 private void getTodayTasks() {
		    getTasks(todayTasks, "today");
		}

	 
	 private void getWeekTasks() {
		 getTasks(weekTasks, "week");
		}
	 
}
