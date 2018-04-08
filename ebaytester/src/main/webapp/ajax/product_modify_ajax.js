//===============================================AJAX Call On Get All Product Name Bases On Login Id=================================//
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
//===============================================Default Ajax call to get all categories=============================================//
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
//===============================================Ajax call to get all sub categories bases on category===============================//
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
//==============================================Ajax call to Get a product data bases on product name================================//
$('#Product_name_list').on('change',function(){
	//alert("Seller id"+" : "+localStorage.user_Id);
	if($('#Products_name').val() == '')
	   {
	    alert("choose product name");
	   }
	else
		{
		var string=$('#Products_name').val();
		$.ajax({
		       type: "get",
		       url:  "http://localhost:5224/ebaytester/webapi/products/list/"+localStorage.user_Id+"/"+string,
		       data: $('#Product_name_list').serialize(),
		       dataType: "JSON",
		       success: function(response){
             $('#Item_id').val(response.item_id);
             $('#Product_Name').val(response.product_name);
             $('#Price').val(response.product_price);
             $('#Quantity').val(response.product_available_quantity);
             $('#Condition').val(response.product_condition);
             $('#Shipping').val(response.product_shipping);
             $('#Description').val(response.product_description);
             $('#Discount').val(response.product_discount);
             $('#Deal').val(response.deal);
             $('#Brand').val(response.brand);
             $('#Color').val(response.color);
             $('#Screen_size').val(response.screen_size);
             $('#Processor').val(response.processor);
             $('#Storage').val(response.storage);
             $('#Warranty').val(response.warranty);
             $('#Operating_system').val(response.operating_system);
             $('#Product_year').val(response.product_year);
             $('#Gender').val(response.gender);
             $('#Warranty_type').val(response.warranty_type);
             $('#Applicable').val(response.applicable);
             $('#Material').val(response.material);
             $('#Clothing_size').val(response.clothing_size);
             $('#Style').val(response.style);
             $('#Card_class').val(response.card_class);
	       }
		});
		}
});
//===============================================Ajax call to Set the Modify Data in DATABASE========================================//
$('#Product_modify_form').submit(function(){
	//alert("Seller id"+" : "+localStorage.user_Id);
//		alert($('#Products_name').val());
		var string=$('#Products_name').val();
		$.ajax({
		       type: "put",
		       url:  "http://localhost:5224/ebaytester/webapi/products/update/"+localStorage.user_Id+"/"+string,
		       data: $('#Product_modify_form').serialize(),
		       dataType: 'text',
		       complete: function(data){
//		    	           var a = JSON.stringify(data);
//		    	           var b = JSON.parse(a);
//		    	           alert(b.responseText);
                            window.location="http://localhost:5224/ebaytester/image_upload_form.html";
                           }
           });
});
//==================================================================END OF CODE======================================================//
