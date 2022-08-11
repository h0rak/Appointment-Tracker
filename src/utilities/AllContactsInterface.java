package utilities;

import javafx.collections.ObservableList;
import model.Contacts;

/** The AllContactsInterface.
 * The purpose of this interface is to collect a list of all contacts for use in the ReportsController.
 */
public interface AllContactsInterface {
    ObservableList<Contacts> getAllContacts();
}
