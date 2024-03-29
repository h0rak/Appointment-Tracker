package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The DBContacts class.
 * This class houses methods that access the contacts table of the database.
 */
public abstract class DBContacts {

    /** The getAllContacts method.
     * This method selects all the contacts in the table.
     * @return returns a list of all contacts
     */
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> allContactsList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM client_schedule.contacts;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, contactEmail);
                allContactsList.add(c);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allContactsList;
    }

}
