package model;

/** The Customers class.
 * This class houses the constructor for the Customers data type as well as the getters and setters.
 */
public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivisionId;

    /** The Customers constructor method.
     * This is a standard constructor.
     * @param customerId the customerId is a unique ID for the customer
     * @param customerName the customerName is the name of the customer
     * @param customerAddress the customerAddress is the address of the customer
     * @param customerPostalCode the customerPostalCode is the postal code of the customer
     * @param customerPhone the customerPhone is the phone number of the customer
     * @param customerDivisionId the customerDivisionId is the division ID of the customer
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
    }

    /** The toString method.
     * This method returns a customer name instead of memory location of the customer
     * @return returns a customerName
     */
    public String toString(){
        return customerName;
    }

    /** The getCustomerId method.
     * @return returns a customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /** The setCustomerId method.
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** The getCustomerName method.
     * @return returns a customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /** The setCustomerName method.
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** The getCustomerAddress method.
     * @return returns a customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /** The setCustomerAddress method.
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /** The getCustomerPostalCode method.
     * @return returns a customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /** The setCustomerPostalCode method.
     * @param customerPostalCode the customerPostalCode to set
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /** The getCustomerPhone method.
     * @return returns a customerPhone*/
    public String getCustomerPhone() {
        return customerPhone;
    }

    /** The setCustomerPhone method.
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /** The getCustomerDivisionId method.
     * @return returns a customerDivisionId
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /** The setCustomerDivisionId method.
     * @param customerDivisionId the customerDivisionId to set
     */
    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /** The inputChecker method.
     * This method generates a string to be used in an error message if the user attempts to save a customer with incorrect values
     * @param customerName the customerName cannot be blank
     * @param customerAddress the customerAddress cannot be blank
     * @param customerPostalCode the customerPostalCode cannot be blank
     * @param customerPhone the customerPhone cannot be blank
     * @param customerDivisionId the customerDivisionId must be greater than or equal to one
     * @param errorMessage the errorMessage is initially blank and appended for each incorrect value
     * @return returns an errorMessage to be used in an alert if needed
     * */
    public static String inputChecker(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId, String errorMessage){
        if (customerName.isBlank()){
            errorMessage += "\nPlease enter a customer name.";
        }
        if (customerAddress.isBlank()){
            errorMessage += "\nPlease enter an address.";
        }
        if (customerPostalCode.isBlank()){
            errorMessage += "\nPlease enter a postal code.";
        }
        if (customerPhone.isBlank()){
            errorMessage += "\nPlease enter a phone number.";
        }
        if (customerDivisionId < 1){
            errorMessage += "\nPlease choose a country followed by\na state, province, or district.";
        }
        return errorMessage;
    }

}
