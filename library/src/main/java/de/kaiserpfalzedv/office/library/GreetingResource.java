package de.kaiserpfalzedv.office.library;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/greeter")
public class GreetingResource {

    @Path("{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") final String name) {
        return "Hello from UU Library, " + name;
    }
}