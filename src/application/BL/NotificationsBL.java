package application.BL;

import java.util.List;
import application.DB.NotificationsDB;
import application.BL.Model.Notification;

public class NotificationsBL {
	 public static List<Notification> getNotifications(int id) {
		 return NotificationsDB.getAllNotifications(id);
	 }
	 
	 public static List<Notification> getUnreadNotifications(int id) {
		 return NotificationsDB.getUnreadNotifications(id);
	 }
	 
	 public static List<Notification> getReadNotifications(int id) {
		 return NotificationsDB.getReadNotifications(id);
	 }
	 
	 public static boolean markAsRead(int id) {
		 try {
			return NotificationsDB.markAsRead(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	 }
	 
	 public static boolean getNotificationCount(int id) {
		 return NotificationsDB.hasMultipleUnreadNotifications(id);
	 }
}
