package model;

public class Contacts {

    // id name and email
   private int contactId;
   private String contactName;
   private String contactEmail;

   public Contacts(int contactId, String contactName, String contactEmail){
       this.contactId = contactId;
       this.contactName = contactName;
       this.contactEmail = contactEmail;
   }

   // possibly add methods

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
