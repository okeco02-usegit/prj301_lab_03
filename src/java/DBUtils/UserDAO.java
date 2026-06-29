package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SwordLake
 */
public class UserDAO {

    // <editor-fold defaultstate="collapsed" desc="getConnection Method">
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=SampleDB";
            //SQL Server Authentication
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="login Method">
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;
        try {
            cnn = getConnection();
            String sql =
                "select LastName,isAdmin from Registration where [UserName]=? and [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User(userName, password, lastName, isAdmin);
            }//end while
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return user;
    }//end login
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="searchUserByLastName Method">
    public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select UserName,Password,LastName,isAdmin from Registration "
                       + " where LastName like ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                User user = new User(userName, password, lastName, isAdmin);
                userList.add(user);
            }//end while
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        if (userList.isEmpty()) {
            return null;
        }
        return userList;
    }//end searchUserByLastName
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="deleteUser Method">
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "delete Registration Where UserName=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }//end deleteUser
    // </editor-fold>

}//end class
