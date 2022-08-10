package model;

/** The Divisions class.
 * This class houses the constructor for the Division data type as well as the getters and setters.
 * */
public class Divisions {
    private int divisionId;
    private String divisionName;
    private int countryId;

    /** The Divisions constructor method.
     * This is a standard constructor.
     * @param divisionId the divisionId is a unique ID for the division
     * @param divisionName the divisionName is the name of the division
     * @param countryId the countryId is a foreign key to the countries table
     */
    public Divisions(int divisionId, String divisionName, int countryId){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /** The toString method.
     * This method returns a division name instead of memory location of the division
     * @return returns a divisionName
     */
    @Override
    public String toString() {
        return (divisionName);
    }

    /** The getDivisionId method.
     * @return returns a divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /** The setDivisionId method.
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** The getDivisionName method.
     * @return returns a divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /** The setDivisionName method.
     * @param divisionName the divisionName to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /** The getCountryId method.
     * @return returns a countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /** The setCountryId method
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}

