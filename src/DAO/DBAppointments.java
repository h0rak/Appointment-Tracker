package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class DBAppointments {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM client_schedule.appointments;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
//              Injecting Timestamp
//              ps.setTimestamp();
//              Extracting Timestamp
//              rs.getTimestamp("");
//              DateTimeFormatter
//              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                Timestamp startTime = rs.getTimestamp("Start");
                Timestamp endTime = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, customerId, userId, contactId);
                allAppointmentsList.add(a);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allAppointmentsList;
    }

    public static ObservableList<Appointments> getAppointmentsByContactId(int cID) {
        ObservableList<Appointments> contactAppointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM client_schedule.appointments WHERE Contact_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, cID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
//              Injecting Timestamp
//              ps.setTimestamp();
//              Extracting Timestamp
//              rs.getTimestamp("");
//              DateTimeFormatter
//              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                Timestamp startTime = rs.getTimestamp("Start");
                Timestamp endTime = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, customerId, userId, contactId);
                contactAppointmentsList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactAppointmentsList;
    }

        public static ObservableList<String> getAllTypes(){
        ObservableList<String> allTypesList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT distinct Type FROM client_schedule.appointments;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String type = rs.getString("Type");

                allTypesList.add(type);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allTypesList;
    }

    public static void DeleteAppointment(int appointmentId){
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // TODO Add Appointment method.
/*
    public static void AddAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp startTime, Timestamp endTime, int customerId, int userId, int contactId){
        try {
            String sql = "INSERT INTO appointment VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)"; // TODO will need to be adjusted for appointment
            PreparedStatement ps= JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostalCode);
            ps.setString(4, customerPhone);
            ps.setInt(5, customerDivisionId);
            ps.executeUpdate(); // OR EXECUTE UPDATE or just EXECUTE?
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
*/

}
