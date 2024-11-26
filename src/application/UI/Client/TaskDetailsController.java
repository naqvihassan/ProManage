package application.UI.Client;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import application.BL.TasksBL;
import application.BL.Model.Task;
import application.UI.BaseTaskDetailController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TaskDetailsController extends BaseTaskDetailController{

	private static int task_id;
    @FXML
    private Label title;
    @FXML
    private Label admin;
    @FXML
    private Label deadline;
    @FXML
    private Label description;
    @FXML
    private Label uploadedFile;

    @FXML
    private Button back;
    @FXML
    private Button submit;

    private ClientUIController dcontroller;

    // Set the DashboardController reference
    public void setDashboardController(ClientUIController controller) {
        this.dcontroller = controller;
    }

    // Load task details into the UI
    public void LoadTaskDetails(int id) {
    	task_id = id;
        Task t = TasksBL.getTaskbyTaskId(id);
        if (t != null) {
            ShowTaskDetail(title, admin, deadline, description, t);
            
            if (t.getData() != null) {
                submit.setText("Unsubmit");

                // Change the button styles
                submit.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #d3d3d3;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: HAND;"  +// Set the cursor to "not-allowed"
                    "-fx-text-fill: black"
                );
                uploadedFile.setText(t.getName()+ "." + t.getType()+ " is submitted.");
            }
            
        } else {
            title.setText("Task not found.");
        }
    }
    
    public void SubmitTask() {
        int taskId = task_id;
        Task t = TasksBL.getTaskbyTaskId(taskId);

        if (t.getData() != null) {
            // Task already submitted, show confirmation dialog
            int confirmResult = JOptionPane.showConfirmDialog(
                null,
                "This task is already submitted. Do you really want to unsubmit it?",
                "Unsubmit Task",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmResult == JOptionPane.YES_OPTION) {
                // User confirmed to unsubmit the task
                boolean success = TasksBL.unsubmitTask(taskId);

                if (success) {
                    System.out.println("Task successfully unsubmitted.");
                    submit.setText("Submit Task");

                    // Change the button styles
                    submit.setStyle(
                        "-fx-background-color: #4f46e5;" +
                        "-fx-border-color: #4f46e5;" +
                        "-fx-border-width: 1;" +
                        "-fx-cursor: HAND;" +
                        "-fx-text-fill: white"
                    );

                    // Enable the button
                    submit.setDisable(false);

                    uploadedFile.setText("");
                } else {
                    System.out.println("Failed to unsubmit the task.");
                    JOptionPane.showMessageDialog(null, "Failed to unsubmit the task.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Task remains submitted.");
            }
        } else {
            // Task not submitted yet, proceed with normal submission
            try {
                // Let the user select a file
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select a File to Upload");

                int result = fileChooser.showOpenDialog(null);
                if (result != JFileChooser.APPROVE_OPTION) {
                    System.out.println("No file selected.");
                    return;
                }

                File selectedFile = fileChooser.getSelectedFile();

                // Call Business Logic Layer
                boolean success = TasksBL.updateTaskWithFile(taskId, selectedFile);

                if (success) {
                    System.out.println("File updated successfully for Task ID: " + taskId);
                    submit.setText("Unsubmit");

                    // Change the button styles
                    submit.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-border-color: #d3d3d3;" +
                        "-fx-border-width: 1;" +
                        "-fx-cursor: HAND;" +
                        "-fx-text-fill: black"
                    );

                    // Disable the button
                    submit.setDisable(false);

                    Task tk = TasksBL.getTaskbyTaskId(taskId);
                    tk.setStatus("completed");
                    uploadedFile.setText(tk.getName() + "." + tk.getType() + " is submitted.");
                } else {
                    System.out.println("Failed to update the file.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    // Go back to the previous pane
    public void goBack() throws Exception {
        // Use the existing DashboardController instance to navigate
        if (dcontroller != null) {
        	dcontroller.loadTaskPage(dcontroller);
        }
    }
}
