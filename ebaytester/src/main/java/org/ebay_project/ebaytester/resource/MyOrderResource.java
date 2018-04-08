package org.ebay_project.ebaytester.resource;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.MyOrder;
import org.ebay_project.ebaytester.service.MyOrderService;
@Path("/MyOrder")
public class MyOrderResource {
//=============================================LIST OF MY ORDERS==================================================// 
	@GET//(written by Prakhar)
	@Path("/list/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MyOrder> MyOrderList(@PathParam("user_id") int user_id){
		MyOrderService os = new MyOrderService();
		return os.MyOrderList(user_id);
	}
}
//================================================END OF CODE=====================================================//