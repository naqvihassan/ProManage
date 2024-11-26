package application.BL.Model;

public class Client {
    private int id;
    private String name;
    private String email;
    private String password;
    private String userType;
    

    public Client(int id, String name, String email, String password, String userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getuserType() {
        return userType;
    }

    @Override
    public String toString() {
        return name + " (Id=" + id + ")";
    }
}
