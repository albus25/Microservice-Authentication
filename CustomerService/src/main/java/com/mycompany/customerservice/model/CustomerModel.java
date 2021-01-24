/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.customerservice.model;

import com.mycompany.customerservice.entity.CustomerMaster;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Albus
 */
@Named
@ApplicationScoped
public class CustomerModel {
    EntityManager em;
    Collection<CustomerMaster> customerMasters;

    public CustomerModel() {
        em = Persistence.createEntityManagerFactory("CustomerPU").createEntityManager();
    }

    public Collection<CustomerMaster> getCustomerMasters(String condition,int rating) {
        customerMasters = new ArrayList<>();
        if(condition.equals("lt")) {
            customerMasters = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.rating < " + rating).getResultList();
        }
        if(condition.equals("gt")) {
            customerMasters = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.rating > " + rating).getResultList();
        }
        if(condition.equals("lte")) {
            customerMasters = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.rating <= " + rating).getResultList();
        }
        if(condition.equals("gte")) {
            customerMasters = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.rating >= " + rating).getResultList();
        }
        if(condition.equals("eq")) {
            customerMasters = em.createQuery("SELECT c FROM CustomerMaster c WHERE c.rating = " + rating).getResultList();
        }
        return customerMasters;
    }

    public void setCustomerMasters(Collection<CustomerMaster> customerMasters) {
        this.customerMasters = customerMasters;
    }
}
