package org.ebay_project.ebaytester.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.service.PaymentService;

@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
@Path("/payment")
public class PaymentResource {
	
	PaymentService payment = new PaymentService();
	
	
	@POST
	@Path("/{product_id}/{quantity}")
	public String PaymentValidation(@PathParam("product_id") int product_id,@PathParam("quantity") int buy_quantity,
								  @FormParam("card_number") String card_number, 
								  @FormParam("cvv")String cvv,
								  @FormParam("ex_month")String ex_month,
								  @FormParam("ex_year") String ex_year)
		{
		System.out.println("Calling validation");
		System.out.println(product_id);
		System.out.println(card_number);
		System.out.println(cvv);
		System.out.println(ex_month);
		System.out.println(ex_year);
		if(payment.Validation(product_id,buy_quantity,card_number,cvv,ex_month,ex_year))
			return "true";
		else
			return "false";
		}
	
	
	
}
