package application.UI.Client;

import java.util.List;

import application.UI.BaseTasksController;
import application.BL.TasksBL;
import application.BL.Model.Task;
import application.UI.LoginController;
import application.UI.TasksLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TasksController extends BaseTasksController implements TasksLoader{

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
    
    private ClientUIController dashboardController; // Reference to DashboardController
    
    
    // Method to set the DashboardController instance
    public void setDashboardController(ClientUIController controller) {
        this.dashboardController = controller;
    }

    @Override
    // Fetch and load tasks into the task list container
    public void loadTasks() throws Exception {
    	TasksBL.updateTaskStatusesForDeadlines(LoginController.getLoggedInUserId());
    	
        toggleActive(allfilter, missingfilter, pendingfilter, completedfilter);
        // Clear any existing tasks before loading new ones
        taskListContainer.getChildren().clear();
        
        // Fetch tasks from the business logic layer (BL)
        List<Task> tasks = TasksBL.getAllTasks(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskItem.getChildren().add(actionButton);

            taskListContainer.getChildren().add(taskItem);
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
        List<Task> tasks = TasksBL.getMissingTasks(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskListContainer.getChildren().add(taskItem);
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
        List<Task> tasks = TasksBL.getPendingTasks(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskListContainer.getChildren().add(taskItem);
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
        List<Task> tasks = TasksBL.getCompletedTasks(LoginController.getLoggedInUserId());

        // Log the number of tasks
        System.out.println("Number of tasks: " + tasks.size());
        
        // Create and add each task to the task list container
        for (Task task : tasks) {
            HBox taskItem = createTaskItem(task);
            Button actionButton = new Button("Details");
            actionButton.getStyleClass().add("details-button"); // Add CSS class for button
            
            // Set action to show task details
            actionButton.setOnAction(event -> {
                
               openTask(task);
            });
            taskListContainer.getChildren().add(taskItem);
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