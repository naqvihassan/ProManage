package application.UI.Admin;

import java.util.List;

import application.BL.TasksBL;
import application.BL.Model.Task;
import application.UI.BaseTasksController;
import application.UI.LoginController;
import application.UI.TasksLoader;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminTasksController extends BaseTasksController implements Assignable, TasksLoader{
    @FXML
    private VBox taskListContainer;  // VBox to hold tasks in the "Tasks.fxml" pane
    @FXML
    private Button allfilter;
    @FXML
    private Button missingfilter;
    @FXML
    private Button pendingfilter;
    @FXML
    private Button completedfilter;
    
    
    private AdminUIController dashboardController; // Reference to DashboardController
    
    // Method to set the DashboardController instance
    public void setDashboardController(AdminUIController controller) {
        this.dashboardController = controller;
    }
    
    @Override
    public void CreateTask() throws Exception {
    	dashboardController.loadAssignTaskPage(dashboardController);
    }
    
    @Override
    // Fetch and load tasks into the task list container
    public void loadTasks() throws Exception {
    	TasksBL.updateTaskStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(allfilter, missingfilter, pendingfilter, completedfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Task> tasks = TasksBL.getAllTasksforAdmin(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            taskListContainer.getChildren().add(taskItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    @Override
    public void loadMissingTasks() throws Exception {
    	TasksBL.updateTaskStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(missingfilter, allfilter, pendingfilter, completedfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Task> tasks = TasksBL.getMissingTasksForAdmin(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            taskListContainer.getChildren().add(taskItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    
    @Override
    public void loadPendingTasks() throws Exception {
    	
    	TasksBL.updateTaskStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(pendingfilter, missingfilter, allfilter, completedfilter);
        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Task> tasks = TasksBL.getPendingTasksForAdmin(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            taskListContainer.getChildren().add(taskItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }
    
    @Override
    public void loadCompletedTasks() throws Exception {
    	TasksBL.updateTaskStatusesForDeadlines(LoginController.getLoggedInUserId());
        toggleActive(completedfilter, missingfilter, allfilter, pendingfilter);

        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Task> tasks = TasksBL.getCompletedTasksForAdmin(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            taskListContainer.getChildren().add(taskItem);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskItem.getChildren().add(actionButton);

        }
        
        // Force layout to refresh after adding tasks
        taskListContainer.layout();
    }

    private void openTask(Task task) {
 	   if (dashboardController != null) {
        	
        	dashboardController.setDetailedTaskId(task.getTaskId(), dashboardController);
        }
 }
  
}