package osi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.ResultCheckStyle;



@Entity
  @Table(name="CONTACTS")
  public class Contact implements Serializable {
	
    @Id
      @Column(name="ID")
      @GeneratedValue
      private Integer id;
	
    @Column(name="name") private String name;
    @Column(name="email") private String email;
    @Column(name="address") private String address;
    @Column(name="phone") private String phone;
	
    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
    
  }