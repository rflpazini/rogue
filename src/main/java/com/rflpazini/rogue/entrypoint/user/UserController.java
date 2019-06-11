package com.rflpazini.rogue.entrypoint.user;

import com.rflpazini.rogue.dataprovider.DataBase;
import com.rflpazini.rogue.entrypoint.model.UserModel;
import java.util.UUID;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/user")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser() {
    UserModel userModel =
        UserModel.builder()
            .email("rflpazini@gmail.com")
            .name("Rafael Pazini")
        .build();

    DataBase.INSTANCE.add(userModel.toString());

    LOGGER.info("getUser");

    return Response.ok().entity(DataBase.INSTANCE.getAllRegistries()).build();
  }

  @GET
  @Path("/new")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsers() {

    UserModel userModel =
        UserModel.builder()
            .email("rflpazini@gmail.com")
            .name("Rafael Pazini")
            .id(UUID.randomUUID().toString())
            .build();

    return Response.ok().entity(userModel).build();
  }
}
