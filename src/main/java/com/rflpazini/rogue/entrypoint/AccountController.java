package com.rflpazini.rogue.entrypoint;

import com.rflpazini.rogue.dataprovider.model.Account;
import com.rflpazini.rogue.dataprovider.model.Money;
import com.rflpazini.rogue.usecase.AccountService;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/account")
public class AccountController {

  @Inject AccountService accountService;

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response accountInfo(@PathParam("id") String accountId) {

    Account a = accountService.getAccountInfo(accountId);
    return Response.ok().entity(a).build();
  }

  @POST
  @Path("/{id}/deposit")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response depositToAccount(Money money, @PathParam("id") String accountId) {
    Account a = accountService.deposit(accountId, money.getAmount());

    return Response.ok().entity(a).build();
  }

  @POST
  @Path("/{id}/withdraw")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response withdrawAccount(Money money, @PathParam("id") String accountId) {
    Account a = accountService.withdraw(accountId, money.getAmount());

    return Response.ok().entity(a).build();
  }
}
