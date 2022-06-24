package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import model.Customers;
import utilities.JDBC;
import java.sql.*;

public abstract class DBCustomers {

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

//    TODO finish this method
    public static void AddCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivision) {
        try {
            String sql1 = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)"; // will need to be adjusted
            PreparedStatement ps1= JDBC.getConnection().prepareStatement(sql1);
            ps1.setString(1, customerName);
            ps1.setString(2, customerAddress);
            ps1.setString(3, customerPostalCode);
            ps1.setString(4, customerPhone);
            ps1.setInt(5, customerDivision);
            ps1.executeUpdate(); // OR EXECUTE UPDATE or just EXECUTE?
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    // TODO UpdateCustomer method
    public static void UpdateCustomer() {

    }

    // TODO DeleteCustomer method
    public static void DeleteCustomer() {

    }


}
