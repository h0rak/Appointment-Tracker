package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBCountries {

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> allCountriesList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Country_ID, Country FROM countries;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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

    public static Countries getCustomerCountryFromDivisionId(int customerDivisionId) {
        Countries c = null;
        int cID = 0;

        try{
            String sql1 = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE DIVISION_ID = ?;";
            PreparedStatement ps1 = JDBC.getConnection().prepareStatement(sql1);
            ps1.setInt(1, customerDivisionId);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                cID = rs1.getInt("COUNTRY_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            String sql2 = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?;";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setInt(1, cID);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                int countryID = rs2.getInt("Country_ID");
                String countryName = rs2.getString("Country");

                c = new Countries(countryID, countryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

}
