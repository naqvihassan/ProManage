package application.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import application.BL.Model.Client;
import application.BL.Model.Task;
public class UserDB {
    private static final String URL = "jdbc:sqlserver://127.0.0.1;databaseName=promanage;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static String validateUser(String email, String password) throws Exception {
        try (Connection connection = DriverManager.getConnection(URL)) {
            String query = "SELECT User_Type FROM Users WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("User_Type");
            }
        }
        return null;
    }
    
    public static int getUserByEmailAndPassword(String email, String password) throws Exception {
    	 String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";
    	    try (Connection conn = DriverManager.getConnection(URL);
    	         PreparedStatement stmt = conn.prepareStatement(query)) {
    	        stmt.setString(1, email);
    	        stmt.setString(2, password);

    	        ResultSet rs = stmt.executeQuery();
    	        if (rs.next()) {
    	            return rs.getInt("user_id");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return -1; // Return -1 if user not found or an error occurs
    }
    
    public static List<Client> getClients() {
    	String type = "client";
        List<Client> clients = new ArrayList<Client>();

        String query = "SELECT user_id, name, email, password, user_type FROM users where user_type = ?"; // Replace with your table/column name
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(query)){
        	statement.setString(1, type);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	int client_id = resultSet.getInt("user_id");
            	String name = resultSet.getString("name");
            	String email = resultSet.getString("email");
            	String password = resultSet.getString("password");
            	String userType = resultSet.getString("user_type");

            	clients.add(new Client(client_id, name,email,password,userType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
    
    
    public static Client getUserbyUserId(int id) {
        Client client = null;

        String query = "SELECT user_id, name, email, password, user_type FROM users where user_id = ?"; // Replace with your table/column name
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(query)){
        	statement.setInt(1, id);
             ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
            	int client_id = resultSet.getInt("user_id");
            	String name = resultSet.getString("name");
            	String email = resultSet.getString("email");
            	String password = resultSet.getString("password");
            	String userType = resultSet.getString("user_type");

            	client = new Client(client_id, name,email,password,userType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }
    
    public static boolean updateUserName(int userId, String newName) throws SQLException {
        String query = "UPDATE users SET name = ? WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
        	PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0; // Returns true if update is successful
        }
    }
    
    public static boolean updateUserPassword(int userId, String newPassword) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
        	PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0; // Returns true if update is successful
        }
    }
    
    public static boolean insertClient(String name, String email, String password) {
        // SQL query to insert a new user
        String sql = "INSERT INTO users (name, email, password, user_type) VALUES (?, ?, ?, 'client')";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameter values
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Return true if insertion was successful

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurred
    }
}
