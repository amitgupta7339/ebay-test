//var tmp = {"category_id":4,"color":"Seller refurbished: An item that has been restored to working order by the eBay seller or a third party not approved b","brand":"Motorola","Model":"E3 Power","product_available_quantity":10,"seller_name":"well-biz","product_size":[7,8,12],"product_color":["Red","Blue","Yellow","Black"],"product_condition":"new","product_description":"Branded Metal Body 4 Star","product_discount":0,"product_id":1,"product_img_url":"http://localhost:5224/ebaytester/images/123.jpg","product_name":"Motorola Cover","product_price":250000,"product_shipping":"fre","product_sold_quantity":10,"sub_category_id":7};

//var tmp="";
//document.write(localStorage.prod_id);
//var prod_id = localStorage.getItem("prod_id");
prod_id = localStorage.getItem("prod_id");

function buybutton()
{
  localStorage.product_id_buynow=prod_id;
  localStorage.place_order=null;
  if(localStorage.user_Id==null)
	{
	 window.location = "http://localhost:5224/ebaytester/login.html";
	}
  else if(parseInt(localStorage.user_PINCODE)==8888)
  {
    window.location = "http://localhost:5224/ebaytester/address_form_buyer.html";
  }
else
	{
	 window.location = "http://localhost:5224/ebaytester/orderReview.html";
	}
}
function addToCartButton() {
	alert(prod_id);
	if(localStorage.user_Id==null)
		{
		window.location="http://localhost:5224/ebaytester/login.html";
		}
	else
		{
		$.ajax({
		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/cart/addToCart/"+prod_id+"/"+localStorage.user_Id+"/"+1,
		       complete: function(response){
	             window.location="http://localhost:5224/ebaytester/cart.html";
		       	}
			   })
		}
}
//document.write(prod_id);
	$.ajax({
	       type: "get",
	       url:  "http://localhost:5224/ebaytester/webapi/products/"+prod_id,
	       async:false ,
	       success: function(tmp){
	    	   //tmp=response;
	    	   //document.write(tmp['category_id']);

	    	 //Adding image to page
	    	   var img = document.createElement('img');
	    	   img.setAttribute("src",tmp['product_img_url']);
	    	   img.setAttribute("alt","Product Image Not available");
	    	   //img.style.width = "500px";
	    	   img.style.maxWidth = "400px";
	    	   img.style.height = "400px";
	    	   document.getElementById("prod_img").appendChild(img);

	    	   //Adding top product description
	    	   var prod_name = document.createElement('p');
	    	   var mk_bld = document.createElement('b');//making header bold
	    	   var text_node = document.createTextNode(tmp['product_name']);
	    	   mk_bld.appendChild(text_node);
	    	   prod_name.appendChild(mk_bld);
	    	   prod_name.style.fontSize = "25px";
	    	   document.getElementById("prod_name").appendChild(prod_name);

	    	   var prod_det = document.createElement('p');
	    	   text_node = document.createTextNode(tmp['product_description']);
	    	   prod_det.appendChild(text_node);
	    	   prod_det.style.color = 'grey';
	    	   prod_det.style.fontSize = "15px"
	    	   document.getElementById("prod_name").appendChild(prod_det);

	    	   //Adding other product info
	    	   var cond = document.createElement('p');
	    	   text_node = document.createTextNode("Condition:");
	    	   cond.appendChild(text_node);
	    	   cond.style.textAlign = "right";
	    	   cond.style.color = 'grey';
	    	   document.getElementById("first").appendChild(cond);

	    	   /*var prod_cond = document.createElement('p');
	    	   text_node = document.createTextNode(tmp['product_condition']);
	    	   prod_cond.appendChild(text_node);
	    	   document.getElementById("second").appendChild(prod_cond);*/

	    	   var prod_cond = '<p>'+ tmp['product_condition'] +'</p>';

	    	   //Color
	    	   var col_div ="";
	    	   if(tmp['product_color'])

	    	   	{
	    	   	var col = document.createElement('p');
	    	   	text_node = document.createTextNode("Color:");
	    	   	col.appendChild(text_node);
	    	   	col.style.textAlign = "right";
	    	   	col.style.color = 'grey';
	    	   	document.getElementById("first").appendChild(col);

	    	   	col_div = '<select id="color" class="form-control" style="height:30px; width:150px"><option value="">-Select-</option>';
	    	   	for(i in tmp['product_color'])
	    	   		{
	    	   			col_div+= '<option>'+tmp['product_color'][i]+'</option>';
	    	   			//document.write(tmp['product_color'][i]);
	    	   		}
	    	   	col_div += '</select>';
	    	   	//document.getElementById("second").innerHTML = col_div;

	    	   	}

	    	   //size
	    	   var sz_div="";//for line break
	    	   var t="";
	    	   if(tmp['product_size'])
	    	   {
	    	   	t="";
	    	   	if(tmp['product_color']){
	    	   		t="<br>";
	    	   		var br = document.createElement('br');
	    	   		document.getElementById("first").appendChild(br);
	    	   	}

	    	   	var sz = document.createElement('p');
	    	   	text_node = document.createTextNode("Size:");
	    	   	sz.appendChild(text_node);
	    	   	sz.style.textAlign = "right";
	    	   	sz.style.color = 'grey';
	    	   	document.getElementById("first").appendChild(sz);

	    	   	sz_div = '<select id="size" class="form-control" style="height:30px; width:150px"><option value="">-Select-</option>';
	    	   	for(i in tmp['product_size'])
	    	   		{
	    	   			sz_div+= '<option>'+tmp['product_size'][i]+'</option>';
	    	   		//document.write(tmp['product_color'][i]);
	    	   		}
	    	   	sz_div += '</select>';
	    	   //document.write(sz_div);

	    	   }

	    	   //Use below code for getting selected value
	    	   //document.write(document.getElementById("size").options[document.getElementById("size").selectedIndex].text);


	    	   //Quantity

	    	   var quant = document.createElement('p');
	    	   text_node = document.createTextNode("Quantity:");
	    	   quant.appendChild(text_node);
	    	   quant.style.textAlign = "right";
	    	   quant.style.color = 'grey';
	    	   if(tmp['product_size'] || tmp['product_color']){
	    	   	document.getElementById("first").appendChild(document.createElement("br"));
	    	   }
	    	   document.getElementById("first").appendChild(quant);

	    	   var prod_quant = "";
	    	   if(tmp['product_size'] || tmp['product_color']){
	    	   	prod_quant="<br>";
	    	   }
	    	   if(tmp['product_available_quantity']==0)
	    	   	{
	    	   		prod_quant += "<p>Not Available</p>";
	    	   	}
	    	   else{
	    	   		prod_quant += '<input id="quantity" style="height:30px; width:30px" type="text" value=1>'
	    	   }

	    	   document.getElementById("second").innerHTML = prod_cond + col_div+t+sz_div + prod_quant;


	    	   //Seller info
	    	   document.getElementById("seller").innerHTML = tmp['seller_name'];

	    	   //Price
	    	   document.getElementById("price").innerHTML = "Rs. "+tmp['product_price'];




	    	   if(tmp['product_shipping'].toLowerCase()=="free"){

	    	   	document.getElementById("ship_det").innerHTML = "FREE";
	    	   }else{
	    	   	document.getElementById("ship_det").innerHTML = 'Read item description or <a href="#"> contact seller</a> for shipping options. | <a href="#" style="color:grey">See details</a>';
	    	   }




	    	   //document.getElementById("ship").innerHTML = '<p style="color:grey;text-align:right">Shipping:</p>';

	    	   var sec=0,cont="";
	    	   for( k in tmp)
	    	   	{
	    	   		if(tmp[k] == "" || k.includes("seller") || k=='category_id' || k=='product_available_quantity' || k=='seller_name' || k=='product_size' || k=='product_color' || k=='product_condition' || k=='product_description' || k=='product_discount' || k=='product_id' || k=='product_img_url' || k=='product_name' || k=='product_sold_quantity' ||k=='product_price' || k=='product_shipping' || k=='sub_category_id')
	    	   		{
	    	   			continue;//if product detail is unrelevent to other description then skip the loop
	    	   		}
	    	   		if(sec == 0)
	    	   		{
	    	   			//document.write("Hey")
	    	   			cont+= '<div class="row"><div class="col-sm-6"><div class="row"><div class="col-sm-6">'+ k +':</div><div class="col-sm-6">'+tmp[k];
	    	   			cont+= '</div></div></div>';
	    	   			sec=1;
	    	   		}else{
	    	   			if(sec==1)
	    	   				{
	    	   				cont+= '<div class="col-sm-6"><div class="row"><div class="col-sm-6">'+  k +':</div><div class="col-sm-6">'+tmp[k];
	    	   				cont+= '</div></div></div></div><br>';
	    	   				sec=0;
	    	   				}
	    	   		}
	    	   	}
	    	   	if(sec==1)
	    	   		{
	    	   			cont+='<div class="col-sm-6></div></div>'
	    	   		}
	    	   document.getElementById("prod_desc_div").innerHTML = cont;

	    	   document.getElementById("shipp").innerHTML = tmp['product_shipping'];


	       }
	     });



//var tmp = {"category_id":4,"product_available_quantity":10,"seller_name":"well-biz","product_size":[7,8,12],"product_color":["Red","Blue","Yellow","Black"],"product_condition":"new","product_description":"Branded Metal Body 4 Star","product_discount":0,"product_id":1,"product_img_url":"http://localhost:5224/ebaytester/images/s-l1600.jpg","product_name":"Motorola Cover","product_price":250000,"product_shipping":"free","product_sold_quantity":10,"sub_category_id":7};
