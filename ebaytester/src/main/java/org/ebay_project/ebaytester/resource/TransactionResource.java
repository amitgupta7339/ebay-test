package org.ebay_project.ebaytester.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Path("/getAllTransactions/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllTransactions(@PathParam("user_id") int user_id) {
		
		TransactionService ts = new TransactionService();
		return ts.getAllTransactions(user_id);
	}
	
	@GET 
	@Path("/getATransaction/{user_id}/{txn_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getATransaction(@PathParam("user_id") int user_id,
			@PathParam("txn_id") int txn_id ) {
		
		TransactionService ts = new TransactionService();
		return ts.getATransaction(user_id,txn_id);
	}
	
	@POST
	@Path("/addTransactionDetail/{user_id}/{txn_id}/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addTransactionDetail(@PathParam("user_id") int user_id,
			@PathParam("txn_id") int txn_id,
			@PathParam("product_id") int product_id) {
		
		TransactionService ts = new TransactionService();
		return ts.addTransactionDetail(user_id,txn_id,product_id);
	}
	
	@PUT 
	@Path("/updateSellerTransaction/{seller_id}/{txn_id}/{product_id}/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSellerTransaction(@PathParam("user_id") int user_id,
			@PathParam("txn_id") int txn_id,
			@PathParam("product_id") int product_id,
			@PathParam("status") String status ) {
		
		TransactionService ts = new TransactionService();
		return ts.updateSellerTransaction(user_id,txn_id,product_id,status);
	}
	
	@PUT 
	@Path("/updateUserTransaction/{user_id}/{txn_id}/{product_id}/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserTransaction(@PathParam("user_id") int user_id,
			@PathParam("txn_id") int txn_id,
			@PathParam("product_id") int product_id,
			@PathParam("status") String status ) {
		
		TransactionService ts = new TransactionService();
		return ts.updateUserTransaction(user_id,txn_id,product_id,status);
	}
	
	
	
	

}
