package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import utilities.JDBC;
import java.sql.*;

/** The DBCustomers class.
 * This class houses the methods that access the customers table of the database.
 */
public abstract class DBCustomers {

    /** The getAllCustomers method.
     * This method selects all the customers in the table.
     * @return returns a list of all customers
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID FROM customers;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int Division_ID = rs.getInt("Division_ID");

                Customers c = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID);
                allCustomersList.add(c);
            }
        }  catch (SQLException e){
            e.printStackTrace();
        }
        return allCustomersList;
    }

    /** The AddCustomer method.
     * This method makes an insert to the customers table of the database.
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerPostalCode Customer Postal Code
     * @param customerPhone Customer Phone
     * @param customerDivisionId Customer Division ID
     */
    public static void AddCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        try {
            String sql = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)"; // will need to be adjusted
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

    /** The UpdateCustomer method.
     * This method makes an update to the customers table of the database.
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerPostalCode Customer Postal Code
     * @param customerPhone Customer Phone
     * @param customerDivisionId Customer Division ID
     */
    public static void UpdateCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostalCode);
            ps.setString(4, customerPhone);
            ps.setInt(5,customerDivisionId);
            ps.setInt(6, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /** The getTotalCustomers method.
     * This method performs a count function on the customers table of the database.
     * This method was replaced by a lambda expression per requirement.
     * @return returns the count of customers to a String
     */
    public static String getTotalCustomers() { // Created Lambda Expression to replace this method
        String sql = "SELECT COUNT(*) FROM client_schedule.customers;";
        String totalCustomers = null;
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalCustomers = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCustomers;
    }
}
