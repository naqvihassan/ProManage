package application.BL;

import application.DB.UserDB;

public class LoginBL {
    public static String loginUser(String email, String password) {
        try {
            return UserDB.validateUser(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
