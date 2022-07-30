package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;
import utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The DBDivisions class.
 * This class houses the methods that access the divisions table of the database.
 */
public abstract class DBDivisions {

    /** The getDivisionsByCountryId method.
     * This method selects all the divisions that belong to the given country ID.
     * @param cID the cID param is the country ID
     * @return returns a list of divisions
     */
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

    /** The getDivisionNameFromDivisionId method.
     * This method gets a division name from a given division ID.
     * @param dID the dID param is the division ID
     * @return returns a division
     */
    public static Divisions getDivisionNameFromDivisionId(int dID) {

        String sql = "SELECT DIVISION_ID, DIVISION, COUNTRY_ID FROM first_level_divisions WHERE DIVISION_ID = ?;";
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

}
