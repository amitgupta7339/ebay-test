$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/deal/getAllDealName",
              dataType: "JSON",
              success: function(response){
                if(response!=null)
                {var i;
                  for (i=0;i<response.length;i++) {
                  $('<option value="'+ response[i].deal_name+'">' + response[i].deal_name+ '</option>').appendTo('#Deal');
                    // catOptions += "<option>" + array[i] + "</option>";
                 }
                }
              }
            });
free_count = 0;
paid_count = 0;
$('#deal_fields').hide();
$('#Deal').on('change', function() {
    if(this.value == ""){
    	$('#deal_fields').hide();
    }else{
    	$.ajax({
            type: "get",
            url:  "http://localhost:5224/ebaytester/webapi/deal/" + this.value,
            dataType: "JSON",
            success: function(response){
              if(response!=null)
              {
            	  var i;
            	  var product_list="";
            	  //Dropdown for selecting products
            	  //alert(localStorage.user_Id);
            	  $.ajax({
                      type: "get",
                      url:  "http://localhost:5224/ebaytester/webapi/products/list/"+localStorage.user_Id,
                      dataType: "JSON",
                      success: function(received){
                    	  //alert("insie");
                        if(received!=null)
                        {
                        	alert("Inside");
                        	$('#free_tag').show();
                        	$('#paid_tag').show();
                        	product_list = received; 
                        	alert(product_list.length);
                      	 // if(product_list != ""){
                        	 $('#free').empty();  
                        	for(i=0;i<response.free_products;i++)
                        		 {
                        		  var id = 'free' + (i+1);
                        		 
                        		  $(`<select  name="${id}" id="${id}" style="width:150px;"><option value="">None</option></select>`).appendTo('#free');
                         		 for(var j=0;j<product_list.length;j++)
                    			 {
                    			 	$('<option value="'+ product_list[j].product_id+'">' + product_list[j].item_id+ '</option>').appendTo(`#${id}`);
                    			 }
                        		 }
                        	  free_count = i;
                        	  if(free_count==0)$('#free_tag').hide();
                        	$('#paid').empty();
                        	  for(i=0;i<response.paid_products;i++)
                     		 {
                        		  var id = 'paid' +(i+1);
                     		  $(`<select  name="${id}" id="${id}" style="width:150px;"><option value="">None</option></select>`).appendTo('#paid');
                      		 for(var j=0;j<product_list.length;j++)
                			 {
                			 	$('<option value="'+ product_list[j].product_id+'">' + product_list[j].item_id+ '</option>').appendTo(`#${id}`);
                			 }
                     		 }
                        	  paid_count = i;
                        	  if(paid_count==0)$('#paid_tag').hide();
                        	  
                        	 /* }else{
                        		  	alert("No products in list to select");
                        	  }*/
                        }
                        else{
                        	alert("No Products TO select");
                        }
                      }
                    });

            	  
              }else{
            	  alert("No such Deal Exist");
              }
            }
          });
    	$('#deal_fields').show();
    }
});

$(function(){
	   $('.datepicker').datepicker({
	      format: 'yyyy-mm-dd'
	    });
	});
$(document).ready(function () {
    $('#pickyDate').datepicker({
        format: "dd/mm/yyyy"
    });
});

function addNewDeal()
{
	//alert("Inside");
	//alert($('#start_date').val());
	var free_string="";
	var id;
	for(var i=0;i<free_count;i++)
		{
			id = "free"+(i+1);//creating id
			free_string+=$(`#${id}`).val() + '|';
		}
	free_string = free_string.slice(0,-1);
	
	var paid_string="";
	var id;
	for(var i=0;i<paid_count;i++)
		{
			id = "paid"+(i+1);//creating id
			paid_string+=$(`#${id}`).val() + '|';
		}
	paid_string = paid_string.slice(0,-1);
	
	//alert(free_string);
	//alert(paid_string);
	
	var deal_object = {"free_products":free_string,
            "paid_products":paid_string,
						   "start_date":$('#start_date').val(),
						   "end_date":$('#end_date').val(),
						   "overall_discount": $('#Overall_Discount').val(),
						   "deal_name": $('#Deal').val(),
						   "seller_id": localStorage.user_Id
							};
	//alert(deal_object);
	deal_object = JSON.stringify(deal_object);//converting to json
	   $.ajax({
		  	type :"POST",
			  url: "http://localhost:5224/ebaytester/webapi/deal/addProductsToDeal",
			  data : deal_object,
			  contentType: "application/json;",
			  success: function(response){
								console.log(response)
								if(response!=null)
									  {alert("Uploaded");
									  window.location="http://localhost:5224/ebaytester/Seller_dashboard.html";
									  }
								  else
									  {
									   alert("Error");
								     window.location="http://localhost:5224/ebaytester/Seller_dashboard.html";
									  }
						  }
});
	
	
	
}



