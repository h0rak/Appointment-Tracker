package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import utilities.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBContacts {

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
//                System.out.println(c.getContactEmail());
                allContactsList.add(c);
            }
        }
        catch (SQLException e){
            e.printStackTrace();;
        }
        return allContactsList;
    }

}
