alert("called");
var cart_product_list="";/*use to show products list*/
var result;/*use to store response */
var x;
var remove_pid;
var pid;
var total=0;
var price=[];
var count=0;
//cart_list(1);
cart_list_ajax();/*default cart ajax call*/
/*===================================================show products list in cart==================================================================*/
function cart_list_ajax(){
	cart_product_list="";
	total=0;
	price=[];
  $.ajax({
  				  type :"GET",
  					url: "http://localhost:5224/ebaytester/webapi/cart/getallcheckedproduct/"+localStorage.user_Id,
            dataType:'JSON',
  					success: function(response){
  						//result=JSON.parse(JSON.stringify(response));
            result=response;
            cart_product_list+='<ul class="list-group">';
              for(i in result)
              {
                cart_list(i);/*function call*/
              }
              cart_product_list+='</ul>';

              document.getElementById('cart').innerHTML=cart_product_list;
              document.getElementById('total_cost').innerHTML="Rs. "+total;
              localStorage.Total=total;
              for(i in result)
            	  {
            	  $('#update'+result[i].product_id).hide();
            	  }
							localStorage.count=result.length;
            }
          });
};
/*==========================================================Dynamic HTML code of products List=========================================================================================================== */
function cart_list(x){
    cart_product_list+='<li class="list-group-item">';
     cart_product_list+='<p id="seller_name"><b>FROM :'+result[x].user_fname+' '+result[x].user_lname+'</b></p>'+
     					'<div class="row">'+
     					'<div class="col-md-3">'+
     					'<img id="image1" class="img-thumbnail" src="'+result[x].product_img_url+'" style="height:100px;width:100px">'+
     					'</div>'+
     					'<div class="col-md-3">'+
     					'<p>'+result[x].product_name+'</p>'+
     					'</div>'+
     					'<div class="col-md-3">'+
     					'<p>QTY:<input type="text" value="'+result[x].product_user_quantity+'" style="width:30px" onChange="quantity('+x+')" id="Quantity'+result[x].product_id+'">'+
						 '<a  href="#" style="margin-left:10px" onclick="update('+x+')" id="update'+result[x].product_id+'">Update</a>'+
						 '</p>'+
						 '</div>'+
						 '<div class="col-md-3">'+
						 
						 '<p><b>Rs.'+result[x].product_price.toFixed(2)+'</b></p>'+
                                  
                         '</div>'+
                    '</li>'
										total=parseFloat(parseFloat(total)+parseFloat(result[x].product_price)).toFixed(2);
                                        price.push(parseFloat(result[x].product_price.toFixed(2)));

};

/*=========================================================remove product from cart==============================================================*/
function Remove(x){
  $.ajax({
            type :"GET",
            url: "http://localhost:5224/ebaytester/webapi/cart/deleteproduct/"+result[x].product_id+"/"+localStorage.user_Id,
            dataType:'text',
            complete: function(response){
              cart_list_ajax();/*function call*/
            }
          });
};

/*===============================================================update link hide/show===========================================================*/
function quantity(x){
	$('#update'+result[x].product_id).show();
}

/*==========================================================update price according to change quantity============================================*/
function update(x){
  $('#update'+result[x].product_id).hide();
  if($('#Quantity'+result[x].product_id).val()>result[x].product_available_quantity)
  {
    alert("available quantity "+result[x].product_available_quantity);
    $('#Quantity'+result[x].product_id).val(result[x].product_available_quantity);
  }
  $.ajax({
            type :"GET",
            url: "http://localhost:5224/ebaytester/webapi/cart/updateproductQuantity/"+result[x].product_id+"/"+$('#Quantity'+result[x].product_id).val()+"/"+localStorage.user_Id,
            dataType:'text',
            complete: function(response){
              cart_list_ajax();/*function call*/
            }
          });

};

/*============================================================functionality on checkedon and checkedoff==========================================*/
function checkbok(x){
	alert(result[x].product_id);
	var check=0;
	$('#Quantity'+result[x].product_id).attr('disabled','disabled');
        if($('#checkbox'+result[x].product_id).is(":checked")) {
            check=1;
						$('#Quantity'+result[x].product_id).removeAttr('disabled');
						total=parseFloat((parseFloat(total)+parseFloat(price[x])).toFixed(2));
        }
        else{
        	total=parseFloat((parseFloat(total)-parseFloat(price[x])).toFixed(2));
        }
				$.ajax({
			            type :"GET",
			            url: "http://localhost:5224/ebaytester/webapi/cart/checkbox/"+result[x].product_id+"/"+localStorage.user_Id+"/"+check,
			            dataType:'text',
			            complete: function(response){}
			          });
	              document.getElementById('order_total').innerHTML="Rs. "+total;
};
/*==========================================================================END OF CODE==========================================================*/
