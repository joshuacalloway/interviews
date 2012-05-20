package osi.dao;

import java.util.List;
import osi.model.Contact;

public interface ContactDAO {
  public List<Contact> getContacts();
  public Contact getContactById(Integer id);
  public void add(Contact contact);
  public void delete(Contact contact);
  public void save(Contact contact);
}
