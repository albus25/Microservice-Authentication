/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.customerclient1.service;

import com.mycompany.customerservice.entity.CustomerMaster;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Albus
 */
@RegisterRestClient(configKey = "customerClient",baseUri = "http://localhost:8080/CustomerService/rest")
@ApplicationScoped
@Path("/customer")
public interface CustomerClient {
    @GET
    @Path("getCustomerMasters/{condition}/{rating}")
    public Collection<CustomerMaster> getCustomerMasters(@PathParam("condition") String condition,@PathParam("rating") int rating);
}
