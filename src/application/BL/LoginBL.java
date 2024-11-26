package application.BL;

import java.util.List;

import application.DB.UserDB;
import application.BL.Model.Client;

public class LoginBL {
    public static String loginUser(String email, String password) {
        try {
            return UserDB.validateUser(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int getUserIdByEmailAndPassword(String email, String password) {
        try {
            return UserDB.getUserByEmailAndPassword(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static List<Client> getClients(){
    	try {
            return UserDB.getClients();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Client getUser(int id) {
    	try {
            return UserDB.getUserbyUserId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean updateUserName(String name, int id) {
    	try {
            return UserDB.updateUserName(id, name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateUserPassword(String p, int id) {
    	try {
            return UserDB.updateUserPassword(id, p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean createUser(String n, String em, String p) {
    	try {
            return UserDB.insertClient(n, em, p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}