package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import wow.anwendungssysteme.user.User;
import wow.anwendungssysteme.user.UserManager;


@Path("user")
public class UserResource {
    @GET
    @Path("/users")
    @Produces({"application/xml","application/json"})
	public User[] getUsers() {
    	
    	return UserManager.getInstance().getUsers();
	}
    
    @GET
    @Path("/{userId}")
    @Produces({"application/xml","application/json"})
	public User getUser(@PathParam("userId") String userId) {
    	
    	return UserManager.getInstance().getUser(Integer.parseInt(userId));
	}
    
    @DELETE
    @Path("/{userId}")
    @Produces({"application/xml","application/json"})
	public void deleteUser(@PathParam("userId") String userId) {
    	
    	UserManager.getInstance().deleteUser(Integer.parseInt(userId));
	}
    
    @POST
    @Consumes({"application/xml","application/json"})
	public void createUser(User user) {
    	
    	UserManager.getInstance().addUser(user);
	}
    
}
