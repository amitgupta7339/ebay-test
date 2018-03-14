$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/products/products_name"+localStorage.User_id,
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
$('#Product_delete_form').submit(function(){
   if($('#Products_name').val() == '')
   {
    alert("choose product name");
   }
   else 
   {
     $.ajax({
       type: "post",
       url:  "http://localhost:5224/ebaytester/webapi/products/delete_product"+localStorage.User_id,
       data: $('#Product_delete_form').serialize(),
       dataType: 'text',
       complete: function(data){
    	   console.log(data);
          alert("Successfully delete");
       }
     });
   }
});
