package com.rflpazini.rogue.entrypoint;

import com.rflpazini.rogue.dataprovider.model.UserModel;
import com.rflpazini.rogue.usecase.UserService;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/user")
public class UserController {
  @Inject public UserService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser() {
    return Response.ok().entity(service.getAll()).build();
  }


  @GET
  @Path("/new")
  @Produces(MediaType.APPLICATION_JSON)
  public Response saveUser() {

    UserModel userModel =
        UserModel.builder().email("rflpazini@gmail.com").name("Rafael Pazini").build();
    userModel = service.saveUser(userModel);

    return Response.ok().entity(userModel).build();
  }
}
