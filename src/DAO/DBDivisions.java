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
        ObservableList<Divisions> allDivisionsLists = FXCollections.observableArrayList();

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
                allDivisionsLists.add(d);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allDivisionsLists;
    }



}
