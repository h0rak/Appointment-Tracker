package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import utilities.JDBC;
import java.sql.*;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, countries.Country, first_level_divisions.Division FROM customers, countries, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                String Country = rs.getString("Country");
                String Division = rs.getString("Division");

                Customers c = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division);
                allCustomersList.add(c);
            }
        }  catch (SQLException e){
            e.printStackTrace();
        }
        return allCustomersList;
    }



    // in addition to getAllCustomers
    // create customer
    public static void AddCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, String customerDivision) {
        try {
            String sql1 = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?)"; // will need to be adjusted
            PreparedStatement ps1= JDBC.getConnection().prepareStatement(sql1);
            ps1.setString(1, customerName);
            ps1.setString(2, customerAddress);
            ps1.setString(3, customerPostalCode);
            ps1.setString(4, customerPhone);
            ps1.setString(5, customerDivision);
            ps1.executeUpdate(); // OR EXECUTE UPDATE or just EXECUTE?

/*
            ResultSet rs1 = ps1.getGeneratedKeys();
            rs1.next();
            int customerId = rs1.getInt(1);

            String sql2 = "INSERT INTO customers  VALUES(NULL, ?, ?, ?)";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);

            ps2.setString(1);
            ps2.setString(2);
            ps2.setString(3);
            ps2.execute();
*/

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    // update customer
    public static void UpdateCustomer() {

    }

    // delete customer
    public static void DeleteCustomer() {

    }


}
