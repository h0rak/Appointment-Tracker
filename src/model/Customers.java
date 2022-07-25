package model;

public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivisionId;

    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
    }

    public String toString(){
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

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
