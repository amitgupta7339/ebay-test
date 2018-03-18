
$('#transaction-form').submit(function(){
		
		     $.ajax({
		    	 
		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/payment/1/2",//+localStorage.product_id_Buy+"/"+localStorage.buyer_product_quantity,
		       data: $('#transaction-form').serialize(),
		       dataType: 'text',
		       complete: function(data){
		    	   		var a=JSON.stringify(data);
		    	   		var b=JSON.parse(a);
		    	   		if(b.responseText=="true")
		    	   		 window.location="http://localhost:5224/ebaytester/transactionsuccess.html";
		    	   		else
		    	   			alert("transaction failure");
		    	   
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
