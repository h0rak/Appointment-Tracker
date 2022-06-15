package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, countries.Country, first_level_divisions.Division, FROM customers, countries, first_level_divisions WHERE customers.Division_ID = divisions.Division_ID AND divisions.Country_ID = countries.Country_ID "; // ADD STUFF IN THE QUOTES
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // datatype, attribute name, = , rs.getDatatype("attribute name");
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                int Postal_Code = rs.getInt("Postal_Code");
                String Phone = rs.getString("Phone");
                String Country = rs.getString("Country");
                String Division = rs.getString("Division");

                Customers c = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division);
                allCustomersList.add(c);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allCustomersList;
    }



    // in addition to getAllCustomers
    // create customer
    public static void AddCustomer() {
        try {
            String sqlci = "INSERT INTO customers VALUES(NULL, ?, ?, ?)"; // will need to be adjusted
            PreparedStatement psci = JDBC.getConnection().prepareStatement(sqlci, Statement.RETURN_GENERATED_KEYS);
            // psci.setData( 1, desc1);
            // psci.setData( 2, desc2);
            // psci.setData( 3, exampleID);

            // psci.execute();

            ResultSet rs = psci.getGeneratedKeys();
            rs.next();
            // int toyinfoID = rs.getInt( columnindex: 1);

            // mysql statement
            String sqlc = "INSERT INTO customers  VALUES(NULL, ?, ?, ?)";
            PreparedStatement psc = JDBC.getConnection().prepareStatement(sqlc);
            /*
            psc.setString(1);
            psc.setString(2);
            psc.setString(3);
             */

            psc.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    // update customer
    public static void UpdateCustomer() {

    }
    // delete customer


}
