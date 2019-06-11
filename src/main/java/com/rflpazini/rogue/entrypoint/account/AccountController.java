package com.rflpazini.rogue.entrypoint.account;


import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/account")
public class AccountController {

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response accountInfo(@PathParam("id") int accountId) {

    return Response.ok().build();
  }

}
