package org.ebay_project.ebaytester.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Produces(MediaType.APPLICATION_JSON)
	public User userRegister(User user)
	{
		return userService.userRegister(user);
		
	}
		
		@POST
		@Path("/updateAddress/{user_email}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public User AddressUpdate(@PathParam("user_email") String user_email,
									@FormParam("country") String country,
									@FormParam("address") String address,
									@FormParam("state") String state, 
									@FormParam("pincode") int pincode, 
									@FormParam("emailid") String emailid,
									@FormParam("mobile") String mobile)
		{

			UserService addService=new UserService();
			return addService.updateAddress(user_email,country,address,state,pincode,emailid,mobile);
			
		}

}
	
