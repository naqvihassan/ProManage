package application.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import application.BL.Model.Notification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationsDB {
	 private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=promanage;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
	 
	 public static List<Notification> getAllNotifications(int userId) {
		    List<Notification> notifications = new ArrayList<>();
		    String query = "SELECT id, generated_by, received_by, message, status, created_at FROM Notification " +
	                   "WHERE received_by = ? " +
	                   "ORDER BY id DESC"; // Order by id in descending order

		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        // Set parameters for the query
		        preparedStatement.setInt(1, userId);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		                int id = resultSet.getInt("id");
		                int generatedBy = resultSet.getInt("generated_by");
		                int receivedBy = resultSet.getInt("received_by");
		                String message = resultSet.getString("message");
		                String status = resultSet.getString("status");

		                // Extract created_at and remove trailing zeros
		                Timestamp timestamp = resultSet.getTimestamp("created_at");
		                String createdAt = timestamp.toLocalDateTime()
		                				   .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		                
		                // Add notification to the list
		                notifications.add(new Notification(id, generatedBy, receivedBy, message, status, createdAt));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return notifications;
		}
	 
	 public static List<Notification> getUnreadNotifications(int userId) {
		    List<Notification> notifications = new ArrayList<>();
		    String query = "SELECT id, generated_by, received_by, message, status, created_at FROM Notification " +
		                   "WHERE received_by = ? AND status = 'unread' " + // Only fetch unread notifications
		                   "ORDER BY id DESC"; // Order by id in descending order

		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        // Set parameters for the query
		        preparedStatement.setInt(1, userId);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		                int id = resultSet.getInt("id");
		                int generatedBy = resultSet.getInt("generated_by");
		                int receivedBy = resultSet.getInt("received_by");
		                String message = resultSet.getString("message");
		                String status = resultSet.getString("status");

		                // Extract created_at and remove trailing zeros
		                Timestamp timestamp = resultSet.getTimestamp("created_at");
		                String createdAt = timestamp.toLocalDateTime()
		                                           .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

		                // Add notification to the list
		                notifications.add(new Notification(id, generatedBy, receivedBy, message, status, createdAt));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return notifications;
		}

	 public static List<Notification> getReadNotifications(int userId) {
		    List<Notification> notifications = new ArrayList<>();
		    String query = "SELECT id, generated_by, received_by, message, status, created_at FROM Notification " +
		                   "WHERE received_by = ? AND status = 'read' " + // Only fetch unread notifications
		                   "ORDER BY id DESC"; // Order by id in descending order

		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        // Set parameters for the query
		        preparedStatement.setInt(1, userId);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		                int id = resultSet.getInt("id");
		                int generatedBy = resultSet.getInt("generated_by");
		                int receivedBy = resultSet.getInt("received_by");
		                String message = resultSet.getString("message");
		                String status = resultSet.getString("status");

		                // Extract created_at and remove trailing zeros
		                Timestamp timestamp = resultSet.getTimestamp("created_at");
		                String createdAt = timestamp.toLocalDateTime()
		                                           .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

		                // Add notification to the list
		                notifications.add(new Notification(id, generatedBy, receivedBy, message, status, createdAt));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return notifications;
		}

	 	
	 public static boolean markAsRead(int id) {
		    String query = "UPDATE Notification SET status = 'read' WHERE id = ?";
		    
		    try (Connection conn = DriverManager.getConnection(URL); // Assuming a method to get DB connection
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        stmt.setInt(1, id);  // Set the notification ID in the query
		        
		        int rowsUpdated = stmt.executeUpdate();  // Execute the query
		        
		        return rowsUpdated > 0;  // If at least one row was updated, return true
		        
		    } catch (SQLException e) {
		        System.out.println("Error while marking notification as read: " + e.getMessage());
		        return false;  // In case of error, return false
		    }
		}
	 
	 public static boolean hasMultipleUnreadNotifications(int loginId) {
		    String query = "SELECT COUNT(*) AS unreadCount " +
		                   "FROM Notification " +
		                   "WHERE status = 'unread' AND received_by = ?";

		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        // Set the parameter before executing the query
		        stmt.setInt(1, loginId);

		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                int unreadCount = rs.getInt("unreadCount");
		                return unreadCount >= 1; // Adjusted to check if more than 1 unread notification
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return false; // Default return if no result or an exception occurs
		}


}
