package com.rflpazini.rogue.app.entrypoint;

import com.rflpazini.rogue.app.entrypoint.model.Customer;
import com.rflpazini.rogue.domain.usecase.CustomerService;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerController {
  @Inject public CustomerService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAllCustomers() {
    return Response.ok().entity(service.getAll()).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") String id) {
    Customer customer = service.findById(id);
    return Response.ok().entity(customer).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCustomer(Customer customer) throws URISyntaxException {
    Customer newCustomer = service.saveCustomer(customer);

    URI location = new URI("http://localhost:8181/rogue/v1/customer/" + newCustomer.getAccountId());
    return Response.created(location).build();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteById(@PathParam("id") String id) {
    Customer customer = service.deleteCustomer(id);
    return Response.ok().entity(customer).build();
  }
}
