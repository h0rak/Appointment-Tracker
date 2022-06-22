package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> allDivisionsList = FXCollections.observableArrayList();
        try{
            // String sql
            String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions;";
            // PreparedStatement ps
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            // ResultSet rs
            ResultSet rs = ps.executeQuery();
            //while(rs.next())
            while(rs.next()){
                int divisionId = rs.getInt("DIVISION_ID");
                String divisionName = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                allDivisionsList.add(d);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allDivisionsList;
    }

    public static ObservableList<Divisions> getUSDivisions() { // getDivisionByCountryId(int cID)
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try{
            // String sql
            String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE COUNTRY_ID = 1;";  // ?
            // PreparedStatement ps
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//            ps.setInt(1, cID);
            // ResultSet rs
            ResultSet rs = ps.executeQuery();
            //while(rs.next())
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

    public static ObservableList<Divisions> getUKDivisions() {
        ObservableList<Divisions> UKDivisions = FXCollections.observableArrayList();
        try{
            // String sql
            String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE COUNTRY_ID = 2;";
            // PreparedStatement ps
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            // ResultSet rs
            ResultSet rs = ps.executeQuery();
            //while(rs.next())
            while(rs.next()){
                int divisionId = rs.getInt("DIVISION_ID");
                String divisionName = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                UKDivisions.add(d);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return UKDivisions;
    }

    public static ObservableList<Divisions> getCANDivisions() {
        ObservableList<Divisions> CANDivisions = FXCollections.observableArrayList();
        try{
            // String sql
            String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE COUNTRY_ID = 3;";
            // PreparedStatement ps
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            // ResultSet rs
            ResultSet rs = ps.executeQuery();
            //while(rs.next())
            while(rs.next()){
                int divisionId = rs.getInt("DIVISION_ID");
                String divisionName = rs.getString("DIVISION");
                int countryId = rs.getInt("COUNTRY_ID");

                Divisions d = new Divisions(divisionId, divisionName, countryId);
                CANDivisions.add(d);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return CANDivisions;
    }

    /*public static Divisions getDivisionIdFromName(String cDivName) throws SQLException {
        try{
            int cDivId;
            String sql = "SELECT Division_ID FROM first_level_divisions WHERE DIVISION = " + cDivName + ";";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cDivId;
    }*/

}
