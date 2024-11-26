package application.UI.Client;

import javax.swing.JOptionPane;

import application.BL.LoginBL;
import application.BL.Model.Client;
import application.UI.BaseManageAccountController;
import application.UI.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageAccountController extends BaseManageAccountController{

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
	private ClientUIController c;
	
	
	@FXML
	public void initialize() {
		email.setText(LoginBL.getUser(LoginController.getLoggedInUserId()).getEmail());
		name.setText(LoginBL.getUser(LoginController.getLoggedInUserId()).getName());
	}
	
	public void setDashboardController(ClientUIController controller) {
	        this.c = controller;
	}
	 
	@FXML
	public void modifyName() {
		changeName(name);
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
