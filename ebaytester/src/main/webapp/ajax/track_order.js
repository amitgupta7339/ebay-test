

$('#Transaction_form').submit(function(){
var txt = $('#transaction_id').val();
	
	localStorage.track_order_txn = txt;
	//var numb = txt.match(/\d/g);
	//numb = numb.join("");​
	//localStorage.track_order_txn = numb;
	alert(localStorage.track_order_txn);
	
	document.location.href = "http://localhost:5224/ebaytester/home.html";
});
