//================================================AJAX Call On Get All Product Name Bases On Login Id=================================//
$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/products/list/"+localStorage.user_Id,
              dataType: "JSON",
              success: function(response){
                if(response!=null)
                {var i;
                  for (i=0;i<response.length;i++) {
                  $('<option value="'+ response[i].product_name+'">' + response[i].product_name+ '</option>').appendTo('#Products_name');
                    // catOptions += "<option>" + array[i] + "</option>";
                 }
                }
              }
            });
//============================================Action Events occurs on Click a Delete Button===========================================//
$('#Product_delete_form').submit(function(){
	//alert("Seller id"+" : "+localStorage.user_Id);
	if($('#Products_name').val() == '')
	   {
	    alert("choose product name");
	   }
	else
		{
		$.ajax({
		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/products/deleteProduct/"+localStorage.user_Id,
		       data: $('#Product_delete_form').serialize(),
		       dataType: "JSON",
		       complete: function(response){
		    	   alert("Success");
		       }
		});
		}
});
//====================================================================END OF CODE=====================================================//
