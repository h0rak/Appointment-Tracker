package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> allCountriesList = FXCollections.observableArrayList();
        try{
            // String sql
            String sql = "SELECT Country_ID, Country FROM countries;";
            // PreparedStatement ps
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            // ResultSet rs
            ResultSet rs = ps.executeQuery();
            //while(rs.next())
            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Countries c = new Countries(countryId, countryName);
                allCountriesList.add(c);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allCountriesList;
    }
}
