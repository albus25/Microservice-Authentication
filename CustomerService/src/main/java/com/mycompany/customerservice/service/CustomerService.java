package com.mycompany.customerservice.service;

import com.mycompany.customerservice.entity.CustomerMaster;
import com.mycompany.customerservice.model.CustomerModel;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerService {
    @Inject CustomerModel customerModel;
    
    @GET
    @Path("getCustomerMasters/{condition}/{rating}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerMaster> getCustomerMasters(@PathParam("condition") String condition,@PathParam("rating") int rating){
        return customerModel.getCustomerMasters(condition, rating);
    }

}
