var check="";
var txt;
var a,b;
$('#Transaction_form').submit(function(){
	
	txt = $('#transaction_id').val();
	
	$.ajax({
		type : "GET",
		url : "http://localhost:5224/ebaytester/webapi/transaction/checkTxn/"
			+localStorage.user_Id+"/"+localStorage.track_order_txn,
		dataType : 'text',
		complete : function(response) {
			localStorage.track_order_txn = txt;
			window.location = "http://localhost:5224/ebaytester/track_transaction.html";
			
//			 a=JSON.stringify(response);
//	   		 b=JSON.parse(a);
//			alert(b.responseText);
//			check=b.responseText;
			
		}
	});
	
	//var numb = txt.match(/\d/g);
	//numb = numb.join("");​
	//localStorage.track_order_txn = numb;
	
//	if(check == "Success"){
//		
//		localStorage.track_order_txn = txt;
//		window.location = "http://localhost:5224/ebaytester/track_transaction.html";
//	}else{
//		alert(check);
//	}
//	
	
});
