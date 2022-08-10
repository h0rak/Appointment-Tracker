package model;

/** The Contacts class.
 * This class houses the constructor for the Contacts data type as well as the getters and setters.
 */
public class Contacts {

   private int contactId;
   private String contactName;
   private String contactEmail;

   /** The Contacts constructor method.
    * This is a standard constructor
    * @param contactId the contactId is a unique ID for the contact
    * @param contactName the contactName is the name of the contact
    * @param contactEmail the contactEmail is the contact's email
    */
   public Contacts(int contactId, String contactName, String contactEmail){
       this.contactId = contactId;
       this.contactName = contactName;
       this.contactEmail = contactEmail;
   }

   /** The toString method.
    * This method returns a contact name instead of memory location of the contact
    * @return returns a contactName
    */
    @Override
    public String toString(){
        return contactName;
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

    /** The getContactName method.
     * @return returns a contactName
     */
    public String getContactName() {
        return contactName;
    }

    /** The setContactName method.
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** The getContactEmail method.
     * @return returns a contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /** The setContactEmail method.
     * @param contactEmail the contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
