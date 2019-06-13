package com.rflpazini.rogue.app.entrypoint;

import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import com.rflpazini.rogue.domain.usecase.TransferService;
import java.net.URI;
import java.net.URISyntaxException;
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
@Path("/transfer")
public class TransferController {

  @Inject TransferService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll() {
    return Response.ok().entity(service.getAllTransfers()).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") String transferId) {
    return Response.ok().entity(service.getTransferById(transferId)).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response moneyTransfer(Transfer transfer)
      throws URISyntaxException {

    Transfer newTransfer = service.createTransfer(transfer);

    URI location = new URI("http://localhost:8181/rogue/v1/transfer/" + newTransfer.getId());
    return Response.created(location).build();
  }
}
