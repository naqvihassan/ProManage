package application.UI.Admin;

import java.io.File;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;

import application.BL.TasksBL;
import application.BL.Model.Task;
import application.UI.BaseTaskDetailController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminTaskDetailsController extends BaseTaskDetailController{

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

    private AdminUIController dcontroller;

    // Set the DashboardController reference
    public void setDashboardController(AdminUIController controller) {
        this.dcontroller = controller;
    }

    // Load task details into the UI
    public void LoadTaskDetails(int id) {
    	task_id = id;
        Task t = TasksBL.getTaskbyTaskId(id);
        if (t != null) {
        	
        ShowTaskDetail(title, admin, deadline, description, t);
            
            if (t.getData() == null) {
                // Change the text
                submit.setText("Download");

                // Change the button styles
                submit.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #d3d3d3;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: wait;"  +
                    "-fx-text-fill: black"
                );

                // Disable the button
                submit.setDisable(true);
                uploadedFile.setText("Client has not submitted the task yet.");
            }
            
            else {
                submit.setText("Download");
                uploadedFile.setText(t.getName()+ "." + t.getType() + " is submitted.");
            }
            
        } else {
            title.setText("Task not found.");
        }
    }
    
    public void DownloadTask() {
        int taskId = task_id;
        try {
            // Call Business Logic Layer to get the file details
            Task taskFile = TasksBL.getTaskbyTaskId(taskId);

            if (taskFile == null) {
                System.out.println("No file found for Task ID: " + taskId);
                return;
            }

            // Let the user choose a download location (without worrying about the extension)
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Download Location");
            fileChooser.setSelectedFile(new File(taskFile.getName())); // Set default file name (without extension)

            int result = fileChooser.showSaveDialog(null);
            if (result != JFileChooser.APPROVE_OPTION) {
                System.out.println("Download location not selected.");
                return;
            }

            File selectedFile = fileChooser.getSelectedFile();

            // Ensure the file has the correct extension
            String fileType = taskFile.getType();
            File destinationFile = selectedFile;
            if (fileType != null && !fileType.isEmpty()) {
                String selectedPath = selectedFile.getAbsolutePath();
                if (!selectedPath.endsWith("." + fileType)) {
                    destinationFile = new File(selectedPath + "." + fileType);
                }
            }

            // Write the file to the selected location
            try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
                fileOutputStream.write(taskFile.getData());
                fileOutputStream.flush();
                System.out.println("File downloaded successfully to: " + destinationFile.getAbsolutePath());
            } catch (Exception ex) {
                System.out.println("Error writing file to disk.");
                ex.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error downloading the file.");
            e.printStackTrace();
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
