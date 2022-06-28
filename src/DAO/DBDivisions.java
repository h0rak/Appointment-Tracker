package DAO;

import controller.AddCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBDivisions {

    public static ObservableList<Divisions> getDivisionsByCountryId(int cID) { // getDivisionByCountryId(int cID)
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE COUNTRY_ID = ?;";  // ?
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, cID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int divisionId = rs.getInt("DIVISION_ID");
                String divisionName = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                divisions.add(d);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return divisions;
    }

    public static Divisions getDivisionNameFromDivisionId(int dID) {

        String sql = "SELECT DIVISION FROM first_level_divisions WHERE DIVISION_ID = ?;";
        Divisions d = null;
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, dID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("DIVISION_ID");
                String divisionName = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");

                d = new Divisions(divisionId, divisionName, countryId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }


// TODO Finish setting up methods
/*
    public static Divisions getDivisionIdFromName(String dName){
        try{
            String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = '?';";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, dName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ranNum = rs.getInt("Division_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ranNum;
    }
*/

}
