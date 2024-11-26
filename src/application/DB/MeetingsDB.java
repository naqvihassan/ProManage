package application.DB;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import application.BL.Model.Meeting;

public class MeetingsDB {
	 private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=promanage;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
	 
	 public static List<Meeting> fetchAllMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch meetings assigned to the specific user (either client or admin)
		        String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
		                       "c.name AS client_name, a.name AS admin_name " +
		                       "FROM meetings m " +
		                       "JOIN users c ON m.client_id = c.user_id " +
		                       "JOIN users a ON m.admin_id = a.user_id " +
		                       "WHERE m.client_id = ? OR m.admin_id = ?";
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);  // Set client_id parameter
		        preparedStatement.setInt(2, userId);  // Set admin_id parameter (assuming user can be admin as well)
		        
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String status = resultSet.getString("status");
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            Timestamp createdAtTimestamp = resultSet.getTimestamp("created_at");
		            
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            
		            // Format the timestamps to LocalDateTime and convert to string for display
		            LocalDateTime createdAt = createdAtTimestamp.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String createdAtStr = createdAt.format(formatter);
		            
		            // Optional: Get client and admin names if needed
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, status, scheduledAt, duration, request, createdAtStr, clientName, adminName));
		        }
		    }
		    return meetings;
		}
	 
	 public static List<Meeting> fetchMissingMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();
		    String status = "missing";  // Meeting status to filter

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
	                       "c.name AS client_name, a.name AS admin_name " +
	                       "FROM meetings m " +
	                       "JOIN users c ON m.client_id = c.user_id " +
	                       "JOIN users a ON m.admin_id = a.user_id " +
	                       "WHERE (m.client_id = ? OR m.admin_id = ?) AND m.status = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, userId);
		        preparedStatement.setString(3, status);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String meetingStatus = resultSet.getString("status");
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the Meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus, scheduledAt, duration, request, timestamp, clientName, adminName));
		        }
		    }
		    return meetings;
		}

	 public static List<Meeting> fetchScheduledMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();
		    String status = "scheduled";  // Meeting status to filter

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
	                       "c.name AS client_name, a.name AS admin_name " +
	                       "FROM meetings m " +
	                       "JOIN users c ON m.client_id = c.user_id " +
	                       "JOIN users a ON m.admin_id = a.user_id " +
	                       "WHERE (m.client_id = ? OR m.admin_id = ?) AND m.status = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, userId);
		        preparedStatement.setString(3, status);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String meetingStatus = resultSet.getString("status");
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the Meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus, scheduledAt, duration, request, timestamp, clientName, adminName));
		        }
		    }
		    return meetings;
		}
	 
	 public static List<Meeting> fetchCancelledMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();
		    String status = "cancelled";  // Meeting status to filter

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
	                       "c.name AS client_name, a.name AS admin_name " +
	                       "FROM meetings m " +
	                       "JOIN users c ON m.client_id = c.user_id " +
	                       "JOIN users a ON m.admin_id = a.user_id " +
	                       "WHERE (m.client_id = ? OR m.admin_id = ?) AND m.status = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, userId);
		        preparedStatement.setString(3, status);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String meetingStatus = resultSet.getString("status");
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the Meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus, scheduledAt, duration, request, timestamp, clientName, adminName));
		        }
		    }
		    return meetings;
		}

	 public static List<Meeting> fetchPendingMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();
		    String status = "pending";  // Meeting status to filter

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
	                       "c.name AS client_name, a.name AS admin_name " +
	                       "FROM meetings m " +
	                       "JOIN users c ON m.client_id = c.user_id " +
	                       "JOIN users a ON m.admin_id = a.user_id " +
	                       "WHERE (m.client_id = ? OR m.admin_id = ?) AND m.status = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, userId);
		        preparedStatement.setString(3, status);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String meetingStatus = resultSet.getString("status");
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the Meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus, scheduledAt, duration, request, timestamp, clientName, adminName));
		        }
		    }
		    return meetings;
		}
	 
	 
	 public static List<Meeting> fetchCompletedMeetings(int userId) throws Exception {
		    List<Meeting> meetings = new ArrayList<>();
		    String status = "completed";  // Meeting status to filter

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, m.scheduled_at, m.duration, m.request, m.created_at, " +
	                       "c.name AS client_name, a.name AS admin_name " +
	                       "FROM meetings m " +
	                       "JOIN users c ON m.client_id = c.user_id " +
	                       "JOIN users a ON m.admin_id = a.user_id " +
	                       "WHERE (m.client_id = ? OR m.admin_id = ?) AND m.status = ?";

		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, userId);
		        preparedStatement.setString(3, status);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int meetingId = resultSet.getInt("meeting_id");
		            int clientId = resultSet.getInt("client_id");
		            int adminId = resultSet.getInt("admin_id");
		            String purpose = resultSet.getString("purpose");
		            String description = resultSet.getString("description");
		            String meetingStatus = resultSet.getString("status");
		            Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
		            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String scheduledAt = scheduledat.format(sformatter);
		            int duration = resultSet.getInt("duration");
		            String request = resultSet.getString("request");
		            
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);
		            String clientName = resultSet.getString("client_name");
		            String adminName = resultSet.getString("admin_name");

		            // Add the Meeting to the list
		            meetings.add(new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus, scheduledAt, duration, request, timestamp, clientName, adminName));
		        }
		    }
		    return meetings;
		}
	 
	 public static Meeting getMeetingbyMeetingId(int id) throws SQLException {
		    Meeting meeting = null;
		    try (Connection connection = DriverManager.getConnection(URL)) {
		        String query = "SELECT m.meeting_id, m.client_id, m.admin_id, m.purpose, m.description, m.status, " +
		                       "m.scheduled_at, m.duration, m.request, m.created_at, " +
		                       "c.name AS client_name, a.name AS admin_name " +
		                       "FROM meetings m " +
		                       "JOIN users c ON m.client_id = c.user_id " +
		                       "JOIN users a ON m.admin_id = a.user_id " +
		                       "WHERE m.meeting_id = ?";

		        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		            preparedStatement.setInt(1, id);

		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                // Check if the ResultSet contains a row
		                if (resultSet.next()) {
		                    int meetingId = resultSet.getInt("meeting_id");
		                    int clientId = resultSet.getInt("client_id");
		                    int adminId = resultSet.getInt("admin_id");
		                    String purpose = resultSet.getString("purpose");
		                    String description = resultSet.getString("description");
		                    String meetingStatus = resultSet.getString("status");
		                    Timestamp scheduled_at = resultSet.getTimestamp("scheduled_at");
				            // Format the timestamp to ensure trailing zeros are included
				            LocalDateTime scheduledat = scheduled_at.toLocalDateTime();
				            DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				            String scheduledAt = scheduledat.format(sformatter);
		                    int duration = resultSet.getInt("duration");
		                    String request = resultSet.getString("request");

		                    Timestamp created_at = resultSet.getTimestamp("created_at");
		                    LocalDateTime createdAt = created_at != null ? created_at.toLocalDateTime() : null;
		                    String timestamp = createdAt != null
		                            ? createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
		                            : null;

		                    String clientName = resultSet.getString("client_name");
		                    String adminName = resultSet.getString("admin_name");

		                    // Construct the Meeting object
		                    meeting = new Meeting(meetingId, clientId, adminId, purpose, description, meetingStatus,
		                            scheduledAt, duration, request, timestamp, clientName, adminName);
		                }
		            }
		        }
		    }
		    return meeting;
		}
	 
	 public static boolean updateMeetingStatus(String s, int Id) throws Exception {
		    String query = "UPDATE meetings SET status = ? WHERE meeting_id = ?";
		    
		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setString(1, s); 
		        preparedStatement.setInt(2, Id);                 
		                      

		        int rowsUpdated = preparedStatement.executeUpdate();
		        return rowsUpdated > 0;
		    }
		}
	 
	 public static boolean updateMeetingRequest(String s, int Id) throws Exception {
		    String query = "UPDATE meetings SET request = ? WHERE meeting_id = ?";
		    
		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setString(1, s); 
		        preparedStatement.setInt(2, Id);                 
		                      

		        int rowsUpdated = preparedStatement.executeUpdate();
		        return rowsUpdated > 0;
		    }
		}
	 
	 public static boolean scheduleMeeting(int admin_id, int client_id, String title, String description, Timestamp scheduledAt, int duration) throws SQLException {
		 String query = "INSERT INTO meetings (admin_id, client_id, purpose, description, scheduled_at, duration) VALUES (?, ?, ?, ?, ?, ?)";
		 try (Connection conn = DriverManager.getConnection(URL);
				 PreparedStatement stmt = conn.prepareStatement(query)) {
		     stmt.setInt(1, admin_id);
		     stmt.setInt(2, client_id);
		     stmt.setString(3, title);
		     stmt.setString(4, description);
		     stmt.setTimestamp(5, scheduledAt);
		     stmt.setInt(6, duration);
		     int rowsUpdated = stmt.executeUpdate();
		     return rowsUpdated > 0;
		 }
	 }
	 
	 public static int getMeetingsCreatedLastNDays(int days, int id) {
		 String query = "SELECT COUNT(*) AS meeting_count " +
                 "FROM meetings " +
                 "WHERE created_at >= DATEADD(DAY, -?, GETDATE()) " +
                 "AND (client_id = ? OR admin_id = ?) ";
	        
	        int meetingsCount = 0;

	        try (Connection conn = DriverManager.getConnection(URL);
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            
	            // Set the parameter for the number of days
	            pstmt.setInt(1, days);
	            pstmt.setInt(2, id);
	            pstmt.setInt(3, id);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    meetingsCount = rs.getInt("meeting_count");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return meetingsCount;
	    }
	 
	 public static int getMeetingsCreatedLastNDaysWithStatus(int days, int id) {
		 String s = "completed";
		    String query = "SELECT COUNT(*) AS meeting_count " +
		                   "FROM meetings " +
		                   "WHERE created_at >= DATEADD(DAY, -?, GETDATE()) " +
		                   "AND (client_id = ? OR admin_id = ?) " +
		                   "AND status = ?";

		    int meetingsCount = 0;

		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        
		        // Set the parameters
		        pstmt.setInt(1, days);        // Days offset
		        pstmt.setInt(2, id);         // Client ID
		        pstmt.setInt(3, id);         // Admin ID
		        pstmt.setString(4, s);  // Task status (e.g., "completed")
		        
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		            	meetingsCount = rs.getInt("meeting_count");
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return meetingsCount;
		}

	 public static boolean updateMeetingStatusesForDeadlines(int userId) {
		    // Define the query to update meetings that are assigned to the user and have missed the scheduled time
		    String updateQuery = """
		        UPDATE meetings
		        SET status = 'missing'
		        WHERE status NOT IN ('completed', 'missed')
		        AND scheduled_at < GETDATE()
		        AND (client_id = ? OR admin_id = ?);
		    """;
		    
		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
		        
		        // Set the userId parameter for both client_id and admin_id
		        pstmt.setInt(1, userId);  // client_id
		        pstmt.setInt(2, userId);  // admin_id
		        
		        // Execute the update query
		        int rowsAffected = pstmt.executeUpdate();
		        
		        // Check if any rows were updated
		        if (rowsAffected > 0) {
		            System.out.println(rowsAffected + " meetings updated to 'Missed' status due to missed scheduled time.");
		            return true; // Return true if meetings were updated
		        } else {
		            System.out.println("No meetings were updated.");
		            return false; // Return false if no meetings were updated
		        }
		    } catch (SQLException e) {
		        System.err.println("Error updating meeting statuses: " + e.getMessage());
		        return false; // Return false if there was an error
		    }
		}

}
