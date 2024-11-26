package application.UI.Admin;

import javax.swing.JOptionPane;

import application.BL.LoginBL;
import application.DB.UserDB;
import application.UI.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateUserController {
			@FXML
			private TextField email;
			@FXML
			private TextField name;
			@FXML
			private Button cancel;
			@FXML
			private Button createAccount;
			@FXML
			private TextField password;
			private AdminUIController c;
			
			
			
			public void setDashboardController(AdminUIController controller) {
			        this.c = controller;
			}
			 
			@FXML
			public void createAccount() {
				String emailAddress = email.getText();
				String username = name.getText();
				String pass = password.getText();
				if (pass.length() < 8) {
			        // Alert for insufficient password length
			        Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Password Too Short");
			        alert.setHeaderText("Password Length Requirement");
			        alert.setContentText("The password must be at least 8 characters long.");
			        alert.showAndWait();
				}
				else {
				int confirmResult = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure you want to create user with enter credentials?",
			            "Create User",
			            JOptionPane.YES_NO_OPTION
			        );

			        if (confirmResult == JOptionPane.YES_OPTION) {
			            // Update the password in the database
			            UserDB.insertClient(username, emailAddress, pass);

			            // Clear the password fields
			            password.setText("");

			            // Show success dialog
			            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
			            successAlert.setTitle("User created");
			            successAlert.setHeaderText("Success");
			            successAlert.setContentText("New user has been successfully added.");
			            successAlert.showAndWait();
			        }
				}
			}
			
			
			@FXML
			public void cancelCreateAccount() {
				email.setText("");
				name.setText("");
				password.setText("");
			}
			
	}
