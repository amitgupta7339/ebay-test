//===============================================Default Ajax call to get all categories==============================================//
$.ajax({
      type: "get",
      url:  "http://localhost:5224/ebaytester/webapi/category/getAllCategory",
      dataType: "JSON",
      success: function(response){
        if(response!=null)
        {var i;
          for (i=0;i<response.length;i++) {
          $('<option value="'+ response[i].category_name+'">' + response[i].category_name+ '</option>').appendTo('#Category_product');
            // catOptions += "<option>" + array[i] + "</option>";
         }
        }
      }
    });
//===============================================Ajax call to get all sub categories bases on category================================//
$('#Category_product').on('change', function() {
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
//======================================AJAX CALL ON SUBMIT A PRODUCT DETAIL FORM FOR DATA STORAGE====================================//
$('#Product_detail_form').submit(function(){
  alert("seller id: "+localStorage.user_Id);
	$.ajax({
		type: "post",
		url:  "http://localhost:5224/ebaytester/webapi/products/uploadProduct/"+localStorage.user_Id,
		data: $('#Product_detail_form').serialize(),
		dataType: "JSON",
		success: function(response){
			         if(response!=null)
			         {
			           localStorage.product_id_seller=response.product_id;
			           alert("category id: "+response.category_id);
			           alert("sub_category id: "+response.sub_category_id);
			           //alert(localStorage.product_id_seller);
                       alert("item id: "+response.item_id);
			           window.location="http://localhost:5224/ebaytester/image_upload_form.html";
			         }
			       }
			     });
			   return false;
     });
//=======================================================================END OF CODE==================================================//
