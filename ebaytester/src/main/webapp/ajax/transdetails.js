
$('#transaction-form').submit(function(){
		
		     $.ajax({
		    	 
		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/payment/"+localStorage.user_Id+"/"+localStorage.product_id_buynow+"/"+localStorage.buyer_product_quantity,
		       data: $('#transaction-form').serialize(),
		       dataType: 'text',
		       complete: function(data){
		    	   		var a=JSON.stringify(data);
		    	   		var b=JSON.parse(a);
		    	  
		    	   		if((b.responseText).slice(0,4)=="true"){
		    	   			alert("Purchase successful:Transaction id is " +(b.responseText).slice(4));
			    	   		localStorage.Transactionid=(b.responseText).slice(4);
			    	   		alert(localStorage.Transactionid);
			    	   			window.location="http://localhost:5224/ebaytester/transactionsuccess.html";
			    	   			}
		    	   		else
		    	   			alert("transaction failure: "+b.responseText);
		    	   
		    	   /*
		         if(response!=null)
		         { 	
		        	
		        	 if(response.something=="true")
		        		 {
		        		 	window.location("http://localhost:5224/ebaytester/transactionsuccess.html");
		        		 }
		        	 else
		        		 {
		        		 alert(response.something);
		        		 }
		         }
		         else
		         {
		         alert("Card details invalid");
		         }*/
		       }
		     });
		     return false;
		 });
