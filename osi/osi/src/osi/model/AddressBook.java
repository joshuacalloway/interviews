package osi.model;

import java.io.Serializable;


public class AddressBook implements Serializable {
  Contact selectedContact;
  Integer changeToSelectedContactId;
	
  public Integer getChangeToSelectedContactId() {
    return changeToSelectedContactId;
  }

  public void setChangeToSelectedContactID(Integer changeToSelectedContactId) {
    this.changeToSelectedContactId = changeToSelectedContactId;
  }

  public Contact getSelectedContact() {
    return selectedContact;
  }
	
  public void setSelectedContact(Contact c) { selectedContact = c; }
}
