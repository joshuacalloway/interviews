package osi.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import osi.model.Contact;
import osi.model.AddressBook;
import osi.service.ContactManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
  public class AddressBookController {

  protected final Log logger = LogFactory.getLog(getClass());
  @Autowired private ContactManager contactManager;
  

  public ContactManager getContactManager() {
    return contactManager;
  }

  public void setContactManager(ContactManager contactManager) {
    this.contactManager = contactManager;
  }

  @ModelAttribute("contactList")
    public List<Contact> populateContactList() {
    return contactManager.getContacts();
  }
 
  @ModelAttribute
    public AddressBook newRequest(@RequestParam(required=false) Integer changeToSelectedContactId) {
    logger.debug("CONTACTCONTROLLER returning new contact(), changeToSelectedContactId: " + (changeToSelectedContactId == null? "null" : changeToSelectedContactId.toString()));
    AddressBook book = new AddressBook();
    List<Contact> contacts = populateContactList();
    Contact selectedContact = new Contact();
    for (Contact i : contacts) {
      if (i.getId().equals(changeToSelectedContactId)) selectedContact = i;
    }
    logger.debug("CONTACTCONTROLLER selectedContact : " + selectedContact.getName());

    book.setSelectedContact(selectedContact);
    book.setChangeToSelectedContactID(selectedContact.getId());
    return book;
  }
  
  @RequestMapping(value="/addressbook")
    public void form() {}
  
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("addressBook") AddressBook addressBook) {	
    contactManager.delete(addressBook.getSelectedContact());	
    return "redirect:/addressbook";  
  }
  
  @RequestMapping(value = "/save",  method = RequestMethod.POST)
    public String save(@ModelAttribute("addressBook") AddressBook addressBook) {
    Contact selectedContact = addressBook.getSelectedContact();
    logger.debug("CONTACTCONTROLLER save.. : " + selectedContact.getName());
    contactManager.save(selectedContact);
    return "redirect:/addressbook";  
  }   
}