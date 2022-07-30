package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The DBUsers class.
 * This class houses the methods that access the Users table of the database.
 */
public abstract class DBUsers {

    /** The getAllUsers method.
     * This method selects all the Users from the Users table of the database.
     * @return returns a list of all users
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsersList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT User_ID, User_Name, Password FROM users;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");

                Users u = new Users(userId, userName, userPassword);
                allUsersList.add(u);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allUsersList;
    }

    public static int getFakeUserId(){
        return 1;
    }

}
