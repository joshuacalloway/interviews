package osi.service;

import osi.dao.ContactDAO;
import osi.model.Contact;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
  public class ContactManager {
  protected final Log logger = LogFactory.getLog(getClass());
  @Autowired private ContactDAO contactDAO;
	
  public ContactDAO getContactDAO() {
    return contactDAO;
  }
  public void setContactDAO(ContactDAO contactDAO) {
    this.contactDAO = contactDAO;
  }

  @Transactional(readOnly = true)
    public List<Contact> getContacts() {
    return contactDAO.getContacts();
  }
	
  @Transactional
    public void delete(Contact contact) {
    contactDAO.delete(contact);
  }
	
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Contact contact) {
    logger.debug("Saving contact : " + contact.getName());
    if (contact.getId() == null) {
      contactDAO.add(contact);
    } else {
      contactDAO.save(contact);				
    }
  }	
}