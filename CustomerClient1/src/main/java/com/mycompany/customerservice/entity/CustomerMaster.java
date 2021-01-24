/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.customerservice.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Albus
 */
@Entity
@Table(name = "customer_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerMaster.findAll", query = "SELECT c FROM CustomerMaster c"),
    @NamedQuery(name = "CustomerMaster.findByCustomerId", query = "SELECT c FROM CustomerMaster c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "CustomerMaster.findByEmail", query = "SELECT c FROM CustomerMaster c WHERE c.email = :email"),
    @NamedQuery(name = "CustomerMaster.findByName", query = "SELECT c FROM CustomerMaster c WHERE c.name = :name"),
    @NamedQuery(name = "CustomerMaster.findByRating", query = "SELECT c FROM CustomerMaster c WHERE c.rating = :rating"),
    @NamedQuery(name = "CustomerMaster.findByAddress", query = "SELECT c FROM CustomerMaster c WHERE c.address = :address"),
    @NamedQuery(name = "CustomerMaster.findByPhoneNumber", query = "SELECT c FROM CustomerMaster c WHERE c.phoneNumber = :phoneNumber")})
public class CustomerMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_id")
    private Integer customerId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "phone_number")
    private String phoneNumber;

    public CustomerMaster() {
    }

    public CustomerMaster(Integer customerId) {
        this.customerId = customerId;
    }

    public CustomerMaster(Integer customerId, String email, String name, int rating, String address, String phoneNumber) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerMaster)) {
            return false;
        }
        CustomerMaster other = (CustomerMaster) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.customerservice.entity.CustomerMaster[ customerId=" + customerId + " ]";
    }
    
}
