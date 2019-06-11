package com.rflpazini.rogue.entrypoint;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {

        return Response.ok().entity("Testing the endpoint").build();
    }
}
