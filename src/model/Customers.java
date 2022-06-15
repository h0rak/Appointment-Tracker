package model;

public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String customerCountry;
    private String customerDivision;
    // Customers have a divisionID and the division has a countryID. The country has a name.

    public Customers(int customerId, String customerName, String customerAddress, String postalCode, String customerPhone, String country, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = postalCode;
        this.customerPhone = customerPhone;
        this.customerCountry = country;
        this.customerDivision = division;
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

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String country) {
        this.customerCountry = country;
    }

    public String getCustomerDivision() {
        return customerDivision;
    }

    public void setCustomerDivision(String division) {
        this.customerDivision = division;
    }
}
