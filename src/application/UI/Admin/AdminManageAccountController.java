package application.UI.Admin;

import application.BL.LoginBL;
import application.UI.BaseManageAccountController;
import application.UI.LoginController;
import application.UI.Client.ClientUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminManageAccountController extends BaseManageAccountController{
		@FXML
		private TextField email;
		@FXML
		private TextField name;
		@FXML
		private Button cancel;
		@FXML
		private Button changeName;
		@FXML
		private TextField password;
		@FXML
		private TextField password1;
		@FXML
		private Button cancel1;
		@FXML
		private Button changePassword;
		private AdminUIController c;
		
		
		@FXML
		public void initialize() {
			email.setText(LoginBL.getUser(LoginController.getLoggedInUserId()).getEmail());
			name.setText(LoginBL.getUser(LoginController.getLoggedInUserId()).getName());
		}
		
		public void setDashboardController(AdminUIController controller) {
		        this.c = controller;
		}
		 
		@FXML
		public void modifyName() {
			changeName(name);
			c.renderName();
		}
		
		@FXML
		public void modifyPassword() {	
			changePassword(password, password1);
		}
		
		@FXML
		public void cancelModifyName() {
			name.setText(LoginBL.getUser(LoginController.getLoggedInUserId()).getName());
		}
		
		@FXML
		public void cancelModifyPassword() {
			password.setText("");
			password1.setText("");
		}
	}

