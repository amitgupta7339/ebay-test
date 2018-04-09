package org.ebay_project.ebaytester.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ebay_project.ebaytester.model.Product;
import org.ebay_project.ebaytester.model.Transaction;
import org.ebay_project.ebaytester.service.TransactionService;

@Path("/transaction")
public class TransactionResource {
	
	@GET 
	@Path("/generateId")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction generateId() {
		
		TransactionService ts = new TransactionService();
		return ts.generateId();
	}
	
	@GET 
	@Path("/checkTxn/{user_id}/{txn_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkTxn(@PathParam("user_id") int user_id,
			@PathParam("txn_id") String txn_id) {
		int txn_id1=Integer.parseInt(txn_id.substring(6));
		TransactionService ts = new TransactionService();
		return ts.checkTxn(user_id,txn_id1);
	}
	
	@GET 
	@Path("/getAllTransactions/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllTransactions(@PathParam("user_id") int user_id) {
		
		TransactionService ts = new TransactionService();
		return ts.getAllTransactions(user_id);
	}
	
	@GET 
	@Path("/getATransaction/{user_id}/{txn_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getATransaction(@PathParam("user_id") int user_id,
			@PathParam("txn_id") String txn_id) {
				int txn_id1=Integer.parseInt(txn_id.substring(6));
		TransactionService ts = new TransactionService();
		return ts.getATransaction(user_id,txn_id1);
	}
		
	@PUT 
	@Path("/updateSellerTransaction/{seller_id}/{txn_id}/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSellerTransaction(@PathParam("seller_id") int user_id,
			@PathParam("txn_id") String txn_id,
			@PathParam("product_id") int product_id
			 ) {
		int txn_id1=Integer.parseInt(txn_id.substring(6));
		TransactionService ts = new TransactionService();
		return ts.updateSellerTransaction(user_id,txn_id1,product_id);
	}
	
	@PUT 
	@Path("/updateUserTransaction/{user_id}/{txn_id}/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserTransaction(@PathParam("user_id") int user_id,
			@PathParam("txn_id") String txn_id,
			@PathParam("product_id") int product_id
			 ) {
		int txn_id1=Integer.parseInt(txn_id.substring(6));
		TransactionService ts = new TransactionService();
		return ts.updateUserTransaction(user_id,txn_id1,product_id);
	}
	
	@PUT 
	@Path("/rateProduct/{user_id}/{txn_id}/{product_id}/{stat}/{rating}")
	@Produces(MediaType.TEXT_PLAIN)
	public String rateProduct(@PathParam("user_id") int user_id,
			@PathParam("txn_id") String txn_id,
			@PathParam("product_id") int product_id,
			@PathParam("stat") int stat,
			@PathParam("rating") int rate ) {
		int txn_id1=Integer.parseInt(txn_id.substring(6));
		TransactionService ts = new TransactionService();
		return ts.rateProduct(user_id,txn_id1,product_id,stat,rate);
	}

}
