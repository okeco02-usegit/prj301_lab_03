package DBUtils;

/**
 * @author SwordLake
 */
public class User {
    private String userName;
    private String password;
    private String lastname;
    private boolean isAdmin;

    public User() {
        this.userName = null;
        this.password = null;
        this.lastname = null;
        this.isAdmin = false;
    }

    public User(String userName, String password, String lastname, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("UserName:%s, Password:%s, LastName:%s, isAdmin:%b",
                userName, password, lastname, isAdmin);
    }
}//end class
