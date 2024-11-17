package application.UI;

import application.BL.LoginBL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=promanage;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty.", Alert.AlertType.ERROR);
            return;
        }

        String userType = LoginBL.loginUser(email, password);

        if (userType == null) {
            showAlert("Login Failed", "Email or password is incorrect.", Alert.AlertType.ERROR);
            return;
        }

        if ("client".equalsIgnoreCase(userType)) {
            openForm(event, "/application/UI/Client/ClientUI.fxml", "Client Dashboard");
        } else if ("admin".equalsIgnoreCase(userType)) {
            openForm(event, "/application/UI/Admin/AdminUI.fxml", "Admin Dashboard");
        }
    }
    
    

    private void openForm(ActionEvent event, String fxmlPath, String title) {
        try {
            // Load the FXML file using the package structure
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            // Close the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Open a new stage
            Stage newStage = new Stage();
            newStage.setTitle(title);

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
