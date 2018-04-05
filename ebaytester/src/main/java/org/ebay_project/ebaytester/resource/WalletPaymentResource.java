package org.ebay_project.ebaytester.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.service.WalletPaymentService;

@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
@Path("/walletPayment")
public class WalletPaymentResource 
{	
	WalletPaymentService walletPayment = new WalletPaymentService();
	
	@POST//(written by Bhavuk)
	@Path("/{walletAmount}/{user_id}")
	public String WalletPaymentValidation(@PathParam("walletAmount") double walletAmount,
										  @PathParam("user_id") int user_id,
										  @FormParam("card_number") String card_number, 
										  @FormParam("cvv")String cvv,
										  @FormParam("ex_month")String ex_month,
										  @FormParam("ex_year") String ex_year)
		{
			walletPayment.Validation(walletAmount,card_number,cvv,ex_month,ex_year, user_id);
			return walletPayment.result;
		
		}
}