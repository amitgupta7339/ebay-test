$('#wallet-transaction-form').submit(function(){

		     $.ajax({

		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/walletPayment/"+localStorage.amt+"/"+localStorage.user_Id,
		       data: $('#wallet-transaction-form').serialize(),
		       dataType: 'text',
		       complete: function(data){
		    	   		var a=JSON.stringify(data);
		    	   		var b=JSON.parse(a);
                        alert(b.responseText);

		    	   		if(b.responseText=="Successfully added balance to your wallet"){
		    	   		 window.location="http://localhost:5224/ebaytester/transactionsuccess_wallet.html";
                 }
		    	   		else{
		    	   			alert("transaction failure: "+b.responseText);
                }
		       }
		     });
		     return false;
		 });
