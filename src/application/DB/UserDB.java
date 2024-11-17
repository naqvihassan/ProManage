package application.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
}
