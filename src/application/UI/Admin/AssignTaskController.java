package application.UI.Admin;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import application.BL.LoginBL;
import application.BL.TasksBL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import application.BL.Model.Client;
import javafx.fxml.*;
import application.UI.LoginController;

public class AssignTaskController {
	@FXML
    private Spinner<Integer> hourSpinner;
	@FXML
	private Spinner<Integer> minuteSpinner;
	@FXML
    private DatePicker dueDatePicker;
	@FXML
    private ComboBox<String> clientComboBox;
	@FXML
	private TextField title;
	@FXML
	private TextArea description;
	
	private AdminUIController dcontroller;
	
	 private List<Client> clients;  // Store list of Client objects
	 private String selectedClient;  // Store the selected client
	
	 public void setDashboardController(AdminUIController controller) {
	        this.dcontroller = controller;
	    }
	@FXML
    public void initialize() {
		
	    dueDatePicker.setValue(LocalDate.now());
        clients = LoginBL.getClients();
        for(Client c: clients) {
        	 clientComboBox.getItems().add(c.getName() + " (" + c.getId() + ")");
        }
       
		 if (!clients.isEmpty()) {
	            clientComboBox.setValue(clients.get(0).getName() + " (" + clients.get(0).getId() + ")");
	            selectedClient = clients.get(0).getName() + " (" + clients.get(0).getId() + ")";
	        }
	        clientComboBox.setOnAction(event -> handleComboBoxAction());
        // Set a range for the Spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 23);
        hourSpinner.setValueFactory(valueFactory);
        hourSpinner.valueProperty().addListener((obs, oldValue, newValue) -> handleSpinnerAction());

        
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 59);
        minuteSpinner.setValueFactory(minuteFactory);
        minuteSpinner.valueProperty().addListener((obs, oldValue, newValue) -> handleSpinnerAction());

        // Make the Spinner editable (optional)
        hourSpinner.setEditable(true);
        minuteSpinner.setEditable(true);
        
        dueDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> handleDatePickerAction(newDate));

    }

	
    private void handleSpinnerAction() {
        int selectedHour = hourSpinner.getValue();
        int selectedMinute = minuteSpinner.getValue();

        System.out.println("Selected Time: " + selectedHour + " hour(s) and " + selectedMinute + " minute(s)");
    }
    
    private void handleDatePickerAction(LocalDate selectedDate) {
        if (selectedDate != null) {
            System.out.println("Selected Date: " + selectedDate);
        } else {
            System.out.println("Date selection cleared.");
        }
    }
    
    private void handleComboBoxAction() {
        // Get the selected client object
        selectedClient = clientComboBox.getValue();

        // Store client ID and name separately
        if (selectedClient != null) {
            // Print or use the selected client's ID and name
            System.out.println("Selected Client: " + selectedClient);
        } else {
            System.out.println("No client selected.");
        }
    }
    
    @FXML
    public void assignTask() throws Exception {
        int admin_id = LoginController.getLoggedInUserId();
        int start = selectedClient.indexOf('(') + 1;
        int end = selectedClient.indexOf(')');
        int client_id = Integer.parseInt(selectedClient.substring(start, end));
        System.out.println(client_id);

        String taskTitle = title.getText().trim();
        if (taskTitle.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Title is required", "Please enter a task title.");
            return;
        }

        String taskDescription = description.getText().trim();
        if (taskDescription.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Description is required", "Please enter a task description.");
            return;
        }

        LocalDate selectedDate = dueDatePicker.getValue();
        if (selectedDate == null) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Due Date is required", "Please select a due date.");
            return;
        }

        int hour = hourSpinner.getValue();
        int minute = minuteSpinner.getValue();

        // Format the date and time as a SQL DATETIME string
        LocalTime selectedTime = LocalTime.of(hour, minute);
        java.time.LocalDateTime dueDateTime = selectedDate.atTime(selectedTime);

        // Convert to java.sql.Timestamp for SQL compatibility
        java.sql.Timestamp sqlDueDateTime = java.sql.Timestamp.valueOf(dueDateTime);
        System.out.println(sqlDueDateTime.toString());

        boolean success = TasksBL.assignTask(admin_id, client_id, taskTitle, taskDescription, sqlDueDateTime);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Task Assigned", "The task has been successfully created and assigned.");
            dcontroller.loadTaskPage(dcontroller);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Task Assignment Failed", "An error occurred while assigning the task.");
        }
    }
    
    @FXML
    public void Cancel() throws Exception {
        dcontroller.loadTaskPage(dcontroller);
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    public static String formatAsSQLDateTime(LocalDate date, int hour, int minute) {
    	if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        LocalTime time = LocalTime.of(hour, minute);

        // Combine LocalDate and LocalTime and format as DATETIME
        String dateTime = date.atTime(time).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime;
    }
    
    
}
