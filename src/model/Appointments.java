package model;

import DAO.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/** The Appointments class.
 * This class houses the constructor for the Appointments data type as well as the getters and setters.
 */
public class Appointments {

    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private Timestamp startTime;
    private Timestamp endTime;
    private int customerId;
    private int userId;
    private int contactId;

    /** The Appointments constructor method.
     * This is a standard constructor.
     * @param appointmentId the appointmentId parameter is a unique ID for the object
     * @param appointmentTitle the appointmentTitle parameter is the name of the appointment
     * @param appointmentDescription the appointmentDescription parameter describes the appointment
     * @param appointmentLocation the appointmentLocation parameter is the location of the appointment
     * @param appointmentType the appointmentType parameter is the type of appointment
     * @param startTime the startTime parameter is a timestamp of the start date and time
     * @param endTime the endTime parameter is a timestamp of the end date and time
     * @param customerId the customerId parameter is a foreign key to the customers table
     * @param userId the userId parameter is a foreign key to the users table
     * @param contactId the contactId parameter is a foreign key to the contacts table
     * */
    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp startTime, Timestamp endTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /** The getAppointmentId method.
     * @return returns an appointmentId.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** The setAppointmentId method.
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** The getAppointmentTitle method.
     * @return returns an appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /** The setAppointmentTitle method.
     * @param appointmentTitle  the appointmentTitle to set
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /** The getAppointmentDescription method.
     * @return returns an appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /** The setAppointmentDescription method.
     * @param appointmentDescription the appointmentDescription to set
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /** The getAppointmentLocation method.
     * @return returns an appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /** The setAppointmentLocation method.
     * @param appointmentLocation the appointmentLocation to set
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /** The getAppointmentType method.
     * @return returns an appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /** The setAppointmentType method.
     * @param appointmentType the appointmentType to set
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /** The getStartTime method.
     * @return returns a startTime
     */
    public Timestamp getStartTime() { return startTime; }

    /** The getStartTimeDisplay method.
     * @return returns a startTime in String form
     */
    public String getStartTimeDisplay(){
        return startTime.toLocalDateTime().toString();
    }

    /** The setStartTime method.
     * @param startTime the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /** The getEndTime method.
     * @return returns an endTime
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /** The getEndTimeDisplay method.
     * @return returns an endTime in String form
     */
    public String getEndTimeDisplay(){
        return endTime.toLocalDateTime().toString();
    }

    /** The setEndTime method.
     * @param endTime the startTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    /** The getUserId method.
     * @return returns a userId
     */
    public int getUserId() {
        return userId;
    }

    /** The setUserId method.
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** The getContactId method.
     * @return returns a contactId
     */
    public int getContactId() {
        return contactId;
    }

    /** The setContactId method.
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** The inputChecker method.
     * This method generates a string to be used in an error message if the user attempts to save an appointment with incorrect values
     * @param appointmentTitle the appointmentTitle cannot be null
     * @param appointmentDescription the appointmentDescription cannot be null
     * @param appointmentLocation the appointmentLocation cannot be null
     * @param appointmentType the appointmentType cannot be null
     * @param startTime the startTime must occur before the endTime
     * @param endTime the startTime must occur before the endTime
     * @param customerId the customerId must be greater than or equal to one
     * @param userId the userId must be greater than or equal to one
     * @param contactId the contactId must be greater than or equal to one
     * @param errorMessage the errorMessage is initially blank and appended for each incorrect value
     * @return returns an errorMessage to be used in an alert if needed
     */
    public static String inputChecker(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp startTime, Timestamp endTime, int customerId, int userId, int contactId, String errorMessage) {
        if (appointmentTitle.isEmpty()) {
            errorMessage = errorMessage + "\nPlease enter a title.";
        }
        if (appointmentDescription.isEmpty()) {
            errorMessage = errorMessage + "\nPlease enter a description.";
        }
        if (appointmentLocation.isEmpty()) {
            errorMessage = errorMessage + "\nPlease enter a location.";
        }
        if (appointmentType.isEmpty()) {
            errorMessage = errorMessage + "\nPlease enter a type.";
        }
        if (startTime.compareTo(endTime) == 0) {
            errorMessage = errorMessage + "\nStart time and end time cannot be the same value.";
        }
        else if (startTime.toLocalDateTime().isAfter(endTime.toLocalDateTime())) {
            errorMessage = errorMessage + "\nStart time must come before the end time.";
        }
        if (customerId < 1) {
            errorMessage = errorMessage + "\nPlease choose a customer.";
        }
        if (contactId < 1) {
            errorMessage = errorMessage + "\nPlease choose a contact.";
        }
        return errorMessage;
    }

    /** The getCustomersAppointmentList method.
     * This method scans the allAppointmentsList for a certain customerId
     * @param customerId the customerId to search
     * @return returns a customersAppointmentList
     */
    public static ObservableList<Appointments> getCustomersAppointmentList(int customerId){
        ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
        ObservableList<Appointments> customersAppointmentList = FXCollections.observableArrayList();

        for (Appointments a : allAppointmentsList){
            if (a.getCustomerId() == customerId){
                customersAppointmentList.add(a);
            }
        }
        return customersAppointmentList;
    }

    /** The getCustomersAppointmentList method.
     * This method scans the allAppointmentsList for a certain customerId
     * @param customerId the customerId to search
     * @param appointmentId the appointmentId to ignore
     * @return returns a customersAppointmentList
     */
    public static ObservableList<Appointments> getCustomersAppointmentList(int customerId, int appointmentId){
        ObservableList<Appointments> allAppointmentsList = DBAppointments.getAllAppointments();
        ObservableList<Appointments> customersAppointmentList = FXCollections.observableArrayList();

        for (Appointments a : allAppointmentsList){
            if (a.getCustomerId() == customerId && a.getAppointmentId() != appointmentId){
                customersAppointmentList.add(a);
            }
        }
        return customersAppointmentList;
    }



}
