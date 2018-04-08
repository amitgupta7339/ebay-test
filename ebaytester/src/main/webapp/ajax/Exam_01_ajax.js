$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/products/getAllproducts",
              dataType: "JSON",
              success: function(response){
                if(response!=null)
                {var i;
                  for (i=0;i<response.length;i++) {
                  $('<option value="'+ response[i].product_id+'">' + response[i].product_id+ '</option>').appendTo('#product_id');
                    // catOptions += "<option>" + array[i] + "</option>";
                 }
                }
              }
            });
  $('#product_id').on('change',function() {
                if(this.value==''){
                      alert("choose product Id");
                  }
                else {var string=$('#product_id').val();
                		$.ajax({
                		       type: "get",
                		       url:  "http://localhost:5224/ebaytester/webapi/products/getproduct/"+string,
                		       //data: $('#Product_name_list').serialize(),
                		       dataType: "JSON",
                		       success: function(response){
                             document.getElementById("Product_Name").innerHTML=response.product_name;
                             document.getElementById("Price").innerHTML="Rs. "+response.product_price;
                             
                             document.getElementById("Discount").innerHTML=response.product_price-(response.product_discount*response.product_price)/100;
                                       // $('#Product_Name').val(response.product_name);
                                       // $('#Price').val(response.product_price);
                                       // $('#Discount').val(response.product_discount);
                    }
                });
                }
              });
