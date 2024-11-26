package application.UI;

import javax.swing.JOptionPane;

import application.BL.LoginBL;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class BaseManageAccountController {

	public void changeName(TextField name) {
	if(LoginBL.getUser(LoginController.getLoggedInUserId()).getName().equals(name.getText())) {
	    // Create an alert of type INFORMATION
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Duplicate Name");
	    alert.setHeaderText("Same Name Entered");
	    alert.setContentText("The entered name is already your current name.");

	    // Show the alert and wait for user response
	    alert.showAndWait();
	}		
	else{
		int confirmResult = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to make changes?",
                "Change Username",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmResult == JOptionPane.YES_OPTION) {
            	LoginBL.updateUserName(name.getText(), LoginBL.getUser(LoginController.getLoggedInUserId()).getId());
            	name.setText(LoginBL.getUser(LoginBL.getUser(LoginController.getLoggedInUserId()).getId()).getName());
            	 Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
 	            successAlert.setTitle("Password Changed");
 	            successAlert.setHeaderText("Success");
 	            successAlert.setContentText("Your password has been successfully changed.");
 	            successAlert.showAndWait();
            }
	}
	}
	
	
	public void changePassword(TextField password, TextField password1) {
		String newPassword = password.getText();
	    String confirmPassword = password1.getText();

	    // Check if passwords match and meet the minimum length requirement
	    if (!newPassword.equals(confirmPassword)) {
	        // Alert for mismatched passwords
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Password Mismatch");
	        alert.setHeaderText("Passwords Do Not Match");
	        alert.setContentText("The entered passwords do not match. Please try again.");
	        alert.showAndWait();
	    } else if (newPassword.length() < 8 || confirmPassword.length() < 8) {
	        // Alert for insufficient password length
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Password Too Short");
	        alert.setHeaderText("Password Length Requirement");
	        alert.setContentText("The password must be at least 8 characters long.");
	        alert.showAndWait();
	    } else {
	        // Confirmation dialog to proceed with password modification
	        int confirmResult = JOptionPane.showConfirmDialog(
	            null,
	            "Are you sure you want to make changes?",
	            "Change Password",
	            JOptionPane.YES_NO_OPTION
	        );

	        if (confirmResult == JOptionPane.YES_OPTION) {
	            // Update the password in the database
	            LoginBL.updateUserPassword(newPassword, LoginBL.getUser(LoginController.getLoggedInUserId()).getId());

	            // Clear the password fields
	            password.setText("");
	            password1.setText("");

	            // Show success dialog
	            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	            successAlert.setTitle("Password Changed");
	            successAlert.setHeaderText("Success");
	            successAlert.setContentText("Your password has been successfully changed.");
	            successAlert.showAndWait();
	        }
	    }
	}
	
}
