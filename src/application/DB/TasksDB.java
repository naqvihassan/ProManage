package application.DB;

import java.io.FileOutputStream;

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
import application.BL.Model.Task;

public class TasksDB {

	 private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=promanage;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

	 public static List<Task> fetchAllTasks(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.admin_id = u.user_id " +
		                       "WHERE t.client_id = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
                    String fileType = resultSet.getString("file_type");
                    String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static List<Task> fetchMissingTasks(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String s = "missing";
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.admin_id = u.user_id " +
		                       "WHERE t.client_id = ? AND t.status = ?" ;
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
                 String fileType = resultSet.getString("file_type");
                 String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static List<Task> fetchPendingTasks(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();
		    String s = "pending";
		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.admin_id = u.user_id " +
		                       "WHERE t.client_id = ? AND t.status = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
                 String fileType = resultSet.getString("file_type");
                 String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static List<Task> fetchCompletedTasks(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();
		    String s = "completed";
		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.admin_id = u.user_id " +
		                       "WHERE t.client_id = ? AND t.status = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
                 String fileType = resultSet.getString("file_type");
                 String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static Task getTaskById(int taskId) {
	        Task task = null; // Initialize the task object
	        String query = "SELECT t.task_id, t.title, t.status, t.description, t.file_data, t.file_type,t.file_name, t.deadline, t.created_at, u.name AS supervisor_name " +
	                       "FROM tasks t " +
	                       "JOIN users u ON t.admin_id = u.user_id " +
	                       "WHERE t.task_id = ?";

	        try (Connection connection = DriverManager.getConnection(URL);
	             PreparedStatement statement = connection.prepareStatement(query)) {

	            // Set the taskId parameter in the query
	            statement.setInt(1, taskId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Map the database row to the Task object
	                    int id = resultSet.getInt("task_id");
	                    String title = resultSet.getString("title");
	                    String status = resultSet.getString("status");
	                    String deadline = resultSet.getString("deadline");
	                    String description = resultSet.getString("description");
	                    byte[] fileData = resultSet.getBytes("file_data");
	                    String fileType = resultSet.getString("file_type");
	                    String fileName = resultSet.getString("file_name");
	                    String supervisorName = resultSet.getString("supervisor_name");
	                    Timestamp created_at = resultSet.getTimestamp("created_at");
			            // Format the timestamp to ensure trailing zeros are included
			            LocalDateTime createdAt = created_at.toLocalDateTime();
			            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			            String timestamp = createdAt.format(formatter);


	                    // Create a Task object
	                    task = new Task(id, title, status, deadline, description,fileData,fileType,fileName,supervisorName, timestamp);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return task; // Return the task object (null if not found)
	    }
	 
	 public static boolean updateTaskFile(int taskId, InputStream fileData, String fileType, long fileLength, String fileName) throws Exception {
		    String query = "UPDATE tasks SET file_data = ?, file_type = ?, file_name = ?, status = ? WHERE task_id = ?";
		    
		    try (Connection connection = DriverManager.getConnection(URL);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setBinaryStream(1, fileData, fileLength); 
		        preparedStatement.setString(2, fileType);                 
		        preparedStatement.setString(3, fileName);                 
		        preparedStatement.setString(4, "completed");               
		        preparedStatement.setInt(5, taskId);                     

		        int rowsUpdated = preparedStatement.executeUpdate();
		        return rowsUpdated > 0;
		    }
		}

	 
	 public static List<Task> fetchAllTasksForAdmin(int adminId) throws Exception {
		    List<Task> tasks = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.client_id = u.user_id " +
		                       "WHERE t.admin_id = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, adminId);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
                 String fileType = resultSet.getString("file_type");
                 String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 
	 public static List<Task> fetchMissingTasksForAdmin(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(URL)) {
		    	String s = "missing";
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.client_id = u.user_id " +
		                       "WHERE t.admin_id = ? AND t.status = ?" ;
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
              String fileType = resultSet.getString("file_type");
              String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static List<Task> fetchPendingTasksForAdmin(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();
		    String s = "pending";
		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.client_id = u.user_id " +
		                       "WHERE t.admin_id = ? AND t.status = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
              String fileType = resultSet.getString("file_type");
              String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static List<Task> fetchCompletedTasksForAdmin(int userId) throws Exception {
		    List<Task> tasks = new ArrayList<>();
		    String s = "completed";
		    try (Connection connection = DriverManager.getConnection(URL)) {
		        // SQL query to fetch tasks assigned to the specific user
		        String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type,t.file_name, t.created_at, u.name AS supervisor_name " +
		                       "FROM tasks t " +
		                       "JOIN users u ON t.client_id = u.user_id " +
		                       "WHERE t.admin_id = ? AND t.status = ?";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setString(2, s);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            int taskId = resultSet.getInt("task_id");
		            String title = resultSet.getString("title");
		            String status = resultSet.getString("status");
		            String deadline = resultSet.getString("deadline");
		            String description = resultSet.getString("description");
		            byte[] fileData = resultSet.getBytes("file_data");
              String fileType = resultSet.getString("file_type");
              String fileName = resultSet.getString("file_name");
		            String supervisorName = resultSet.getString("supervisor_name");
		            Timestamp created_at = resultSet.getTimestamp("created_at");
		            // Format the timestamp to ensure trailing zeros are included
		            LocalDateTime createdAt = created_at.toLocalDateTime();
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		            String timestamp = createdAt.format(formatter);


		            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName,timestamp));
		        }
		    }

		    return tasks;
		}
	 
	 public static boolean clearTaskFileDetails(int taskId) {
		    String query = "UPDATE tasks SET file_data = NULL, file_type = NULL, file_name = NULL, status = ? WHERE task_id = ?";
		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        stmt.setString(1, "pending");
		        stmt.setInt(2, taskId);
		        return stmt.executeUpdate() > 0; // Returns true if at least one row is updated
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	 
	 public static boolean assignTask(int admin_id, int client_id, String taskTitle, String taskDescription, Timestamp dueDateTime) throws SQLException {
		 String query = "INSERT INTO tasks (admin_id, client_id, title, description, deadline) VALUES (?, ?, ?, ?, ?)";
		 try (Connection conn = DriverManager.getConnection(URL);
				 PreparedStatement stmt = conn.prepareStatement(query)) {
		     stmt.setInt(1, admin_id);
		     stmt.setInt(2, client_id);
		     stmt.setString(3, taskTitle);
		     stmt.setString(4, taskDescription);
		     stmt.setTimestamp(5, dueDateTime);
		     int rowsUpdated = stmt.executeUpdate();
		     return rowsUpdated > 0;
		 }
	 }
	 
	 public static int getTasksCreatedLastNDays(int days, int id) {
	        String query = "SELECT COUNT(*) AS task_count " +
	                       "FROM tasks " +
	                       "WHERE created_at >= DATEADD(DAY, -?, GETDATE()) AND (client_id = ? OR admin_id = ?)";
	        
	        int taskCount = 0;

	        try (Connection conn = DriverManager.getConnection(URL);
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            
	            // Set the parameter for the number of days
	            pstmt.setInt(1, days);
	            pstmt.setInt(2, id);
	            pstmt.setInt(3, id);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    taskCount = rs.getInt("task_count");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return taskCount;
	    }
	 
	 public static int getTasksCreatedLastNDaysWithStatus(int days, int id) {
		 String s = "completed";
		    String query = "SELECT COUNT(*) AS task_count " +
		                   "FROM tasks " +
		                   "WHERE created_at >= DATEADD(DAY, -?, GETDATE()) " +
		                   "AND (client_id = ? OR admin_id = ?) " +
		                   "AND status = ?";

		    int taskCount = 0;

		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        
		        // Set the parameters
		        pstmt.setInt(1, days);        // Days offset
		        pstmt.setInt(2, id);         // Client ID
		        pstmt.setInt(3, id);         // Admin ID
		        pstmt.setString(4, s);  // Task status (e.g., "completed")

		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                taskCount = rs.getInt("task_count");
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return taskCount;
		}
	 
	 
	 public static List<Task> getTasksDueInNext7Days(int userId) {
		    List<Task> tasks = new ArrayList<>();
		    
		    String query = "SELECT " +
		               "t.task_id, " +
		               "t.title, " +
		               "t.status, " +
		               "t.deadline, " +
		               "t.description, " +
		               "t.file_data, " +
		               "t.file_type, " +
		               "t.file_name, " +
		               "t.created_at, " +
		               "u.name AS supervisor_name " +
		               "FROM tasks t " +
		               "JOIN users u ON t.admin_id = u.user_id " +
		               "WHERE t.deadline BETWEEN CAST(GETDATE() AS DATE) " +
		               "AND DATEADD(DAY, 7, CAST(GETDATE() AS DATE)) " +
		               "AND t.admin_id = ? OR t.client_id = ?;";


		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        
		        // Set parameters for the query
		        pstmt.setInt(1, userId);  // Client ID or Admin ID
		        pstmt.setInt(2, userId);  // Client ID or Admin ID
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {
		            while (resultSet.next()) {
		            	 int taskId = resultSet.getInt("task_id");
				            String title = resultSet.getString("title");
				            String status = resultSet.getString("status");
				            String deadline = resultSet.getString("deadline");
				            String description = resultSet.getString("description");
				            byte[] fileData = resultSet.getBytes("file_data");
		                 String fileType = resultSet.getString("file_type");
		                 String fileName = resultSet.getString("file_name");
				            String supervisorName = resultSet.getString("supervisor_name");
				            Timestamp created_at = resultSet.getTimestamp("created_at");
				            // Format the timestamp to ensure trailing zeros are included
				            LocalDateTime createdAt = created_at.toLocalDateTime();
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				            String timestamp = createdAt.format(formatter);


				            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return tasks;
		}
	 
	 
	 public static List<Task> getTasksDueToday(int userId) {
		    List<Task> tasks = new ArrayList<>();
		    
		    String query = "SELECT t.task_id, t.title, t.status, t.deadline, t.description, t.file_data, t.file_type, t.file_name, t.created_at, u.name AS supervisor_name " +
		               "FROM tasks t " +
		               "JOIN users u ON t.admin_id = u.user_id " +
		               "WHERE CAST(t.deadline AS DATE) = CAST(GETDATE() AS DATE) " +  // Match only today's date
		               "AND (t.client_id = ? OR t.admin_id = ?)";

		    try (Connection conn = DriverManager.getConnection(URL);
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        
		        // Set parameters for the query
		        pstmt.setInt(1, userId);  // Client ID or Admin ID
		        pstmt.setInt(2, userId);  // Client ID or Admin ID
		        
		        try (ResultSet resultSet = pstmt.executeQuery()) {
		            while (resultSet.next()) {
		            	 int taskId = resultSet.getInt("task_id");
				            String title = resultSet.getString("title");
				            String status = resultSet.getString("status");
				            String deadline = resultSet.getString("deadline");
				            String description = resultSet.getString("description");
				            byte[] fileData = resultSet.getBytes("file_data");
		                 String fileType = resultSet.getString("file_type");
		                 String fileName = resultSet.getString("file_name");
				            String supervisorName = resultSet.getString("supervisor_name");
				            Timestamp created_at = resultSet.getTimestamp("created_at");
				            // Format the timestamp to ensure trailing zeros are included
				            LocalDateTime createdAt = created_at.toLocalDateTime();
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				            String timestamp = createdAt.format(formatter);


				            tasks.add(new Task(taskId, title, status, deadline,description, fileData, fileType, fileName, supervisorName, timestamp));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return tasks;
		}
	 
		 public static boolean updateTaskStatusesForDeadlines(int userId) {
			    // Define the query to update tasks that are assigned to the user and have missed deadlines
			    String updateQuery = """
			        UPDATE tasks
			        SET status = 'Missing'
			        WHERE status NOT IN ('Completed', 'Missing')
			        AND deadline < GETDATE()
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
			            System.out.println(rowsAffected + " tasks updated to 'Missing' status due to missed deadlines.");
			            return true; // Return true if tasks were updated
			        } else {
			            System.out.println("No tasks were updated.");
			            return false; // Return false if no tasks were updated
			        }
			    } catch (SQLException e) {
			        System.err.println("Error updating task statuses: " + e.getMessage());
			        return false; // Return false if there was an error
			    }
			}


}
