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
	  					url: "http://localhost:5224/ebaytester/webapi/cart/getallproduct/"+localStorage.user_Id,
	            dataType:'JSON',
	  					success: function(response){
	            result=response;
	            cart_product_list+='<ul class="list-group">';
	              for(i in result)
	              {
	                cart_list(i);/*function call*/
	              }
	              cart_product_list+='</ul>';

	              document.getElementById('cart').innerHTML=cart_product_list;
	              document.getElementById('order_total').innerHTML="Rs. "+total;
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
     cart_product_list+='<div class="row">'+
                          '<div class=row >'+
                           '<p style="Color:grey;margin-left:20px" ><strong>From </strong>'+result[x].user_fname+' '+result[x].user_lname+'</p>'+
                          '</div>'+
                          '<div class=row>'+
                            '<a href="#" onclick="Remove('+x+')">'+
                            '<p style="Color:grey;text-align:right;margin-right:50px"><span class="glyphicon glyphicon-remove"></span>Remove</p>'+
                            '</a>'+
                          '</div>'+
                          '<div>'+
                            '<span class="col-sm-1" style="text-align:right ;width:20px">'+
															 	'<input type="checkbox" onChange="checkbok('+x+')" id="checkbox'+result[x].product_id+'" name="'+result[x].product_id+'" value="'+result[x].product_id+'" checked>'+
														'</span>'+
                            '<span class="col-sm-11"><div class=row>'+
                                '<span class="col-sm-1" style="margin-left:20px">'+
                                  '<table style=" border-collapse: separate; border-spacing: 4px;" >'+
                                    '<tr >'+
                                      '<td colspan="2" style="border: 3px solid #CCC" ><a href="#"><img id="image1" src="'+result[x].product_img_url+'" style="height:100px;width:100px"><a></td>'+
                                    '</tr>'+
                                  '</table>'+
                                '</span>'+
                                '<span class="col-sm-5" style="margin-left:50px"><p>'+result[x].product_name+'</p></span>'+
                                '<span class="col-sm-3">'+
																	 '<p>QTY:<input type="text" value="'+result[x].product_user_quantity+'" style="width:30px" onChange="quantity('+x+')" id="Quantity'+result[x].product_id+'">'+
																	 '<a  href="#" style="margin-left:10px" onclick="update('+x+')" id="update'+result[x].product_id+'">Update</a>'+
																	 '</p>'+
																	 '<p>Shipping</p>'+
																'</span>'+
                                '<span class="col-sm-2"><p><b>Rs.'+result[x].product_price.toFixed(2)+'</b></p>'+
                                  '<p>Free</p>'+
																'</span>'+
																'</div>'+
                            '</span>'+
                         '</div>'+
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
/*=========================================================ACTION EVents on shop more and place order button=====================================*/
$('#shop_more').click(function(){
	window.location="http://localhost:5224/ebaytester/home.html";
});
$('#place_order').click(function(){
	//localStorage.product_id_buynow=null;
	alert(localStorage.product_id_buynow);
	window.location="http://localhost:5224/ebaytester/orderReviewCart.html";
});
/*==========================================================================END OF CODE==========================================================*/
