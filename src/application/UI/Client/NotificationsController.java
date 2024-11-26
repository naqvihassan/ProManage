package application.UI.Client;

import application.UI.BaseNotificationsController;
import application.UI.Notifiable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NotificationsController extends BaseNotificationsController implements Notifiable{

	
	 private ClientUIController dc; // Reference to DashboardController
	 
	 @FXML
	 private Button all, unread, read;
	 @FXML
	 public void initialize() throws Exception {
		 loadAll();
	 }
	 @FXML
	 private VBox notifications;
	    // Method to set the DashboardController instance
	    public void setDashboardController(ClientUIController controller) {
	        this.dc = controller;
	    }
	    
	    @FXML
	    @Override
	    public void loadAll() throws Exception {
	    	toggleActive(all, unread, read);
	    	loadNotification(notifications, "all");
	       
	    }
	    
	    @FXML
	    @Override
	    public void loadRead() throws Exception {
	    	toggleActive(read, unread, all);
	    	loadNotification(notifications, "read");
	       
	    }
	    
	    @FXML
	    @Override
	    public void loadUnread() throws Exception {
	    	toggleActive(unread, read, all);
	    	loadNotification(notifications, "unread");
	       
	    }
	    
	  
}
