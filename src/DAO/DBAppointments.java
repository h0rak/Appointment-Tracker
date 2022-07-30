package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import utilities.JDBC;
import java.sql.*;

/** The DBAppointments class.
 * This class houses methods that access the appointments table in the database.
 */
public abstract class DBAppointments {

    /** THe getAllAppointments method.
     * This method selects all the appointments in the table.
     * @return returns a list of all appointments
     */
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
                Timestamp startTime = Timestamp.valueOf(rs.getString("Start"));
                Timestamp endTime = Timestamp.valueOf(rs.getString("End"));
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

    /** THe getAppointmentsByContactId method.
     * This method gets a list of appointments by contact ID.
     * @param cID the cID param is the contact ID that sets the appointments in the list
     * @return returns a list of the selected contact's appointments
     */
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

    /** The getContactByAppointmentId method.
     * This method gets the contact from an appointment.
     * @param aId the aId param is the Appointment ID
     * @return returns a Contact
     */
    public static Contacts getContactByAppointmentId(int aId) {

        int cId = 0;
        Contacts contact = null;

        try {
            String sql = "SELECT Contact_ID FROM appointments WHERE Appointment_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, aId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql2 = "SELECT * FROM client_schedule.contacts WHERE Contact_ID = ?;";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setInt(1, cId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int contactId = rs2.getInt("Contact_ID");
                String contactName = rs2.getString("Contact_Name");
                String contactEmail = rs2.getString("Email");

                contact = new Contacts(contactId, contactName, contactEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    /** The getCustomerByAppointmentId method.
     * This method gets a customer from an appointment.
     * @param aId the aId param is the appointment ID
     * @return returns the Customer
     */
    public static Customers getCustomerByAppointmentId(int aId) {

        int cId = 0;
        Customers customer = null;

        try {
            String sql = "SELECT Customer_ID FROM appointments WHERE Appointment_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, aId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql2 = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers WHERE Customer_ID = ?;";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setInt(1, cId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int customerId = rs2.getInt("Customer_ID");
                String customerName = rs2.getString("Customer_Name");
                String customerAddress = rs2.getString("Address");
                String customerPostalCode = rs2.getString("Postal_Code");
                String customerPhone = rs2.getString("Phone");
                int customerDivisionId = rs2.getInt("Division_ID");

                customer = new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    /** The getUserByAppointmentId method
     * This method gets a user from an appointment.
     * @param aId the aId param is the appointment ID
     * @return returns the User
     */
    public static Users getUserByAppointmentId(int aId) {

        int uId = 0;
        Users user = null;

        try {
            String sql = "SELECT User_ID FROM appointments WHERE Appointment_ID = ?;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, aId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                uId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql2 = "SELECT User_ID, User_Name, Password FROM users WHERE User_ID = ?;";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setInt(1, uId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int userId = rs2.getInt("User_ID");
                String userName = rs2.getString("User_Name");
                String userPassword = rs2.getString("Password");

                user = new Users(userId, userName, userPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /** The getAllTypes method.
     * This method gets a distinct list of appointment types.
     * @return returns a distinct list of types
     */
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

    /** The DeleteAppointment method.
     * This method deletes a selected appointment.
     * @param appointmentId the appointmentId param is the ID of the appointment to be deleted
     */
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

    /** The AddAppointment method.
     * This method makes an insert to the appointment table of the database.
     * @param appointmentTitle Appointment Title
     * @param appointmentDescription Appointment Description
     * @param appointmentLocation Appointment Location
     * @param appointmentType Appointment Type
     * @param startTime Start Time
     * @param endTime End Time
     * @param customerId Customer ID
     * @param userId User ID
     * @param contactId Contact ID
     */
    public static void AddAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp startTime, Timestamp endTime, int customerId, int userId, int contactId){
        try {
            String sql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";
            PreparedStatement ps= JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            ps.executeUpdate(); // OR EXECUTE UPDATE or just EXECUTE?
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /** The UpdateAppointment method.
     * This method makes an update to the appointment table of the database.
     * @param appointmentId Appointment ID
     * @param appointmentTitle Appointment Title
     * @param appointmentDescription Appointment Description
     * @param appointmentLocation Appointment Location
     * @param appointmentType Appointment Type
     * @param startTime Start Time
     * @param endTime End Time
     * @param customerId Customer ID
     * @param userId User ID
     * @param contactId Contact ID
     */
    public static void UpdateAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp startTime, Timestamp endTime, int customerId, int userId, int contactId){
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?;";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, appointmentId);
            ps.executeUpdate();
        }
        catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
    }
}
