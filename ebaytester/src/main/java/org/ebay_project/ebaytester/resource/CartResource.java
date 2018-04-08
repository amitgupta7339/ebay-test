package org.ebay_project.ebaytester.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Cart;
import org.ebay_project.ebaytester.service.CartService;

@Path("/cart")
public class CartResource {
//	================================= GET ALL PRODUCTS BASES ON LOGEDIN USER ID===================================//
	@GET //(written by Amit)
	@Path("/getallproduct/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cart> getCardProductList(@PathParam("user_id") int user_id) {
		CartService C1 = new CartService();
		return C1.getCartProductList(user_id);
	}
// =================================DELETE PRODUCT BASES ON PRODUCT ID AND USER ID================================//
	@GET //(written by Amit)
	@Path("/deleteproduct/{product_id}/{user_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteproduct(@PathParam("product_id") int product_id, @PathParam("user_id") int user_id) {
		CartService C1 = new CartService();
		return C1.deleteproduct(product_id, user_id);
	}
// =========================UPDATE BUYER PRODUCT QUANTITY BASES ON PRODUCT_ID AND USER_ID=========================//
	@GET //(written by Amit)
	@Path("/updateproductQuantity/{product_id}/{quantity}/{user_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProductQuantity(@PathParam("product_id") int product_id, @PathParam("quantity") int quantity,
			@PathParam("user_id") int user_id) {
		CartService C1 = new CartService();
		return C1.updateProductQuantity(product_id, quantity, user_id);
	}
// ================UPDATE CHECKED ATTRIBUTE IN TABLES BASES ON USER ID, PRODUCT ID AND CHECK STATUS===============//
	@GET //(written by Amit)
	@Path("/checkbox/{product_id}/{user_id}/{check}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkboxUpdate(@PathParam("product_id") int product_id, @PathParam("user_id") int user_id,
			@PathParam("check") int check) {
		CartService C1 = new CartService();
		return C1.checkboxUpdate(product_id, user_id, check);
	}
// ========================ADD ITEM INTO CART BASES ON PRODUCT ID, USER ID AND BUYER QUANTITY=====================//
	@POST //(written by Anamol)
	@Path("/addToCart/{product_id}/{user_id}/{quantity}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addToCart(@PathParam("product_id") int product_id, @PathParam("user_id") int user_id,
			@PathParam("quantity") int quantity) {
		CartService C1 = new CartService();
		return C1.addToCart(product_id, user_id, quantity);
	}
// ======================================GET ALL CHECKED ALL PRODUCT==============================================//	
	@GET//(written by Mayank)
   	@Path("/getallcheckedproduct/{user_id}")
   	@Produces(MediaType.APPLICATION_JSON)  
    public List<Cart> getCardBuyProductList(@PathParam("user_id")int user_id)
	{
		CartService C1=new CartService();
		return C1.getCartBuyProductList(user_id);
	}
// ================================BUY CART PRODUCT AND PAYMENT METHOD DEBIT CARD=================================//   
    @POST//(written by Mayank)
    @Path("/buyallcheckedproduct/{user_id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String buyAllCartItems(@PathParam("user_id") int user_id,@FormParam("card_number") String card_number, 
			  @FormParam("cvv")String cvv,
			  @FormParam("ex_month")String ex_month,
			  @FormParam("ex_year") String ex_year)
    {	CartService C1=new CartService();
    	return C1.buyCartItems(user_id,card_number,cvv,ex_month,ex_year);
    	
    }
// =====================================BUY CART PRODUCT AND PAYMENT METHOD WALLET================================//   
    @POST//(written by Mayank)
    @Path("/buyallcheckedproduct/wallet/{user_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String buyCartWallet(@PathParam("user_id") int user_id)
    {	CartService C1=new CartService();
    	return C1.buyCartWallet(user_id);
    	
    }
	    
}
// ===============================================END OF CODE=====================================================//