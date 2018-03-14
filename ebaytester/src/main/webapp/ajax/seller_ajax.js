$.ajax({
      type: "get",
      url:  "http://localhost:5224/ebaytester/webapi/category/getAllCategory",
      dataType: "JSON",
      success: function(response){
        if(response!=null)
        {var i;
          for (i=0;i<response.length;i++) {
          $('<option value="'+ response[i].category_name+'">' + response[i].category_name+ '</option>').appendTo('#Category');
            // catOptions += "<option>" + array[i] + "</option>";
         }
        }
      }
    });

$('#Category').on('change', function() {
	$('#Subcategory').empty();
	if(this.value!= ''){
		$.ajax({
		        type: "get",
		        url:  "http://localhost:5224/ebaytester/webapi/category/"+this.value,
		        dataType: "JSON",
		        success: function(response){
		           if(response!=null)
		             {
		               var i;
		               for (i=0;i<response.length;i++) {
		            	   $('<option value="'+ response[i].sub_category_name+'">' + response[i].sub_category_name+ '</option>').appendTo('#Subcategory');
		                       }
		             }
		         }
		       });
		  }
	else{
		$('<option value="'+''+'">' + "sub Category"+ '</option>').appendTo('#Subcategory');
	}
    });

$('#Product_detail_form').submit(function(){

	$.ajax({
		type: "post",
		url:  "http://localhost:5224/ebaytester/webapi/products/uploadProduct"+localStorage.User_id,
		data: $('#Product_detail_form').serialize(),
		dataType: "JSON",
		success: function(response){
			         if(response!=null)
			         {
			           localStorage.product_id_seller=response.product_id;
			           window.location="http://localhost:5224/ebaytester/image_upload_form.html";
			         }
			       }
			     });
			   return false;
     });		     
