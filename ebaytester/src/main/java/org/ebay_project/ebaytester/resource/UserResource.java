package org.ebay_project.ebaytester.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.User;
import org.ebay_project.ebaytester.service.UserService;



@Path("/user")
public class UserResource {
	
	private UserService userService = new UserService();
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User userLogin(@FormParam("user_email") String user_email, @FormParam("user_password") String user_password)
	{
		return userService.userLogin(user_email, user_password);	
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String userRegister(User user)
	{
		return userService.userRegister(user);
		
	}
	
	
	
	
	

}
