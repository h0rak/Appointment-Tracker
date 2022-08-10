package model;

/** The Countries class.
 * This class houses the constructor for the Countries data type as well as the getters and setters.
 */
public class Countries {

    private int countryId;
    private String countryName;

    /** The Countries constructor method.
     * This is a standard constructor.
     * @param countryId the countryId is a unique ID for the country
     * @param countryName the countryName is the name of the country
     */
    public Countries(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /** The toString method.
     * This method returns a country name instead of memory location of the country
     * @return returns a countryName
     */
    @Override
    public String toString() {
        return (countryName);
    }

    /** The getCountryId method.
     * @return returns a countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /** The setCountryId method.
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** The getCountryName method.
     * @return returns a countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /** The setCountryName method.
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
