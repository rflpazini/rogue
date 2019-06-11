package com.rflpazini.rogue.entrypoint.user;

import com.rflpazini.rogue.entrypoint.model.UserModel;
import java.util.UUID;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/user")
public class UserController {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response sayHello() {

    UserModel userModel =
        UserModel.builder()
            .email("rflpazini@gmail.com")
            .name("Rafael Pazini")
            .uId(UUID.randomUUID().toString())
        .build();

    return Response.ok().entity(userModel).build();
  }
}
