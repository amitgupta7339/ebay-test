	/*var prod_info_arr =[ {"img_url" : "images/s-l225.jpg",
			"head1" : "For 10.or E Back Cover Transparent Soft Silicon Case / Tempered Glass",
			"head2" : "Best Quality Fitting ★ INDIAN VERSION ★ Lowest Price ★" ,
			"inr" : "159.00",
			"ship" : "Free Shipping"
	},
		{
	"img_url" : "images/s-l225.jpg",
	"head1" : "For 10.or E Back Cover Transparent Soft Silicon Case / Tempered Glass",
	"head2" : "Shipping From Bluedart/Fedex/Aramex/IndianPost",
	"inr" : "159.00",
	"ship" : "Free Shipping"
		}

];*/

	//document.write("Hey There");
	var sub_cat = localStorage.sub_category_name;
	var cat = localStorage.category_name;
	//document.write(sub_cat);
	//document.write(cat);
	//var cat = "Mobile & Accessories";
	var r="";
	var brand=[];
	var color=[];
	var condition = [];
	var sub_list = "";
	//brand.push(sub_cat);
	//if(cat == null || cat == ""){}
	if(!(cat == null || cat == "")){
	$.ajax({
	       type: "get",
	       url:  "http://localhost:5224/ebaytester/webapi/category/cat_name/" + cat,
	       success: function(response){
	    	   	sub_list = JSON.parse(JSON.stringify(response));
	    	   	document.getElementById("cat_name").innerHTML = cat;
	    	   	var sub_list_gui = "";
	    	   	for(y in sub_list)
	    	   		{
	    	   			sub_list_gui+='<p style="font-size:14px"><a href="#" onclick = SubCatFilter('+sub_list[y].sub_category_id+')>'+sub_list[y].sub_category_name+'</a></p>';


	    	   		}
	    	   	document.getElementById("sub_list").innerHTML = sub_list_gui;
	       }
		   })
	}
	function Buy_Now(productName , productId , productPrice)
	{
		alert("In Buy_Now");
		localStorage.productname_Buynow = productName;
		localStorage.productcost_Buynow = productPrice;
		localStorage.product_id_Buy = productId;
		window.location = "http://localhost:5224/ebaytester/orderReview.html";
	}
	function SubCatFilter(obj)
	{
		var content='<ul class="list-group">';
 	   var strng="";
 	    for(y in r)
 	   {//document.write(r[y].productBrand);
 	    if(r[y].subCatId == obj)
 	    {
 	    strng = "myfunction("+r[y].productId+")";
 	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
 	   	content+=r[y].productImageUrl+'" style="max-width:250px; max-height:50px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
 	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';
 	   //document.write("myfunction("+r[y].product_id+")");
 	    }/*else{
 	    	r.splice(y,1);
 	    	//document.write(y);
 	    }document.write(y);*/
 	    };
 	    r = r.filter(function(el) {
 		    return el.subCatId === obj;
 		});

 	   document.getElementById("prod_list").innerHTML=content+'</ul>';
 	  document.getElementById("sub_list").innerHTML = "";
 	 document.getElementById("cat_name").innerHTML = "";

	}
	var url_prcd;
	if((cat == null || cat == ""))
		{
		url_prcd = "sub_category/" +sub_cat;

		}else{

			url_prcd = "category/" +cat;
		}
	$.ajax({
	       type: "get",
	       url:  "http://localhost:5224/ebaytester/webapi/products/"+url_prcd,
	       success: function(response){

	    	   r=JSON.parse(JSON.stringify(response));
	    	   var content='<ul class="list-group">';
	    	   var strng="";
	    	    for(y in r)
	    	   {//document.write(r[y].productBrand);
	    	    	if(!brand.includes(r[y].productBrand) && r[y].productBrand !="" && r[y].productBrand != null)//if particular brand is not there in list then add new one
	    	    		{
	    	    			brand.push(r[y].productBrand);
	    	    		}
	    	    	if(!color.includes(r[y].productColor) && r[y].productColor !="" && r[y].productColor != null )
	    	    		{
	    	    			color.push(r[y].productColor);
	    	    		}
	    	    	if(!condition.includes(r[y].productCondition) && r[y].productCondition !="" && r[y].productCondition != null)//if particular brand is not there in list then add new one
    	    		{
    	    			condition.push(r[y].productCondition);
    	    		}
	    	    strng = "myfunction("+r[y].productId+")";
	    	    //alert(r[y].productName);
	    	    pn = r[y].productName;
	    	    pi = r[y].productId;
	    	    pp = r[y].productPrice;
	    	    strng2 = 'Buy_Now("'+ pn +'","'+ pi +',' + pp+')';
	    	   	alert(strng2);
	    	    content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" onclick = '+strng2+' style = "float:right"  class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';
	    	   //document.write("myfunction("+r[y].product_id+")");
	    	   };
	    	   document.getElementById("prod_list").innerHTML=content+'</ul>';
	    	   //document.write(brand.length);
	    	   var tmp = "";
	    	   if(brand.length>0)
	    		   {
	    		   		tmp+='<p style="font-size:18px;"><strong> Brand</strong></p>';
	    		   		for(i=0;i<brand.length;i++)
	    		   			{
	    		   				tmp+='<div class="checkbox"><label><input type="checkbox" value="'+brand[i]+'" onchange = "brandFilter(this)">'+brand[i]+'</label></div>'
	    		   			}
	    		   		//document.write(tmp);
	    		   		tmp+="<br>";
	    		   }
	    	   if(color.length>0)
    		   {
    		   		tmp+='<p style="font-size:18px;"><strong> Color</strong></p>';
    		   		for(i=0;i<color.length;i++)
    		   			{
    		   				tmp+='<div class="checkbox"><label><input type="checkbox" value="'+color[i]+'" onchange = "colorFilter(this)">'+color[i]+'</label></div>'
    		   			}
    		   		//document.write(tmp);
    		   		tmp+="<br>";
    		   }
	    	   if(condition.length>0)
    		   {
    		   		tmp+='<p style="font-size:18px;"><strong> Condition</strong></p>';
    		   		for(i=0;i<condition.length;i++)
    		   			{
    		   				tmp+='<div class="checkbox"><label><input type="checkbox" value="'+condition[i]+'" onchange = "conditionFilter(this)">'+condition[i]+'</label></div>'
    		   			}
    		   		//document.write(tmp);
    		   		tmp+="<br>";
    		   }

	    	   document.getElementById("filters").innerHTML = tmp;
	       //tmp="";



	       }
	     });

	//var brand=[];

	function myfunction(prod_id)
	{
		//document.write("Hey");
		localStorage.prod_id = prod_id;
		//document.write("Hey");
		//System.out.println("Till here");
		//System.out.println(localStorage.getItem("prod_id"));
		//console.log("Prod_id Fetched");
		//document.write(prod_id);
		window.location="http://localhost:5224/ebaytester/prod_desc.html";
	}

	var brand_filt = [];
	function brandFilter(obj)
	{
		if($(obj).is(":checked")){//When checked
		    brand_filt.push($(obj).val());
		    //alert(brand_filt);
		    var content='<ul class="list-group">';
	    	var strng="";
	    	for(y in r)
	    	   {//document.write(r[y].productBrand);
	    	    	if(brand_filt.includes(r[y].productBrand))//if particular brand is not there in list then add new one
	    	    		{
	    	    	    strng = "myfunction("+r[y].productId+")";
	    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
	    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
	    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

	    	    		}
	    	    //document.write("myfunction("+r[y].product_id+")");
	    	   };
	    	   document.getElementById("prod_list").innerHTML=content+'</ul>';




		  }else{
		    //alert("Not checked"); //when not checked
			  brand_filt.splice(brand_filt.indexOf($(obj).val()),1);//to delete the unchecked element
			  var content='<ul class="list-group">';
		    	var strng="";
		    	for(y in r)
		    	   {//document.write(r[y].productBrand);
		    			if(brand_filt.length==0)
		    				{
		    				strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';
		    	    		continue;
		    				}
		    	    	if(brand_filt.includes(r[y].productBrand))//if particular brand is not there in list then add new one
		    	    		{
		    	    	    strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

		    	    		}
		    	    //document.write("myfunction("+r[y].product_id+")");
		    	   };
		    	   document.getElementById("prod_list").innerHTML=content+'</ul>';

			  //alert(brand_filt);
		  }

	}


	var condition_filt = [];
	function conditionFilter(obj)
	{
		if($(obj).is(":checked")){//When checked
		    condition_filt.push($(obj).val());
		    //alert(brand_filt);
		    var content='<ul class="list-group">';
	    	var strng="";
	    	for(y in r)
	    	   {//document.write(r[y].productBrand);
	    	    	if(condition_filt.includes(r[y].productCondition))//if particular brand is not there in list then add new one
	    	    		{
	    	    	    strng = "myfunction("+r[y].productId+")";
	    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
	    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
	    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

	    	    		}
	    	    //document.write("myfunction("+r[y].product_id+")");
	    	   };
	    	   document.getElementById("prod_list").innerHTML=content+'</ul>';




		  }else{
		    //alert("Not checked"); //when not checked
			  condition_filt.splice(condition_filt.indexOf($(obj).val()),1);//to delete the unchecked element
			  var content='<ul class="list-group">';
		    	var strng="";
		    	for(y in r)
		    	   {//document.write(r[y].productBrand);
		    			if(condition_filt.length==0)
		    				{
		    				strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';
		    	    		continue;
		    				}
		    	    	if(condition_filt.includes(r[y].productCondition))//if particular brand is not there in list then add new one
		    	    		{
		    	    	    strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

		    	    		}
		    	    //document.write("myfunction("+r[y].product_id+")");
		    	   };
		    	   document.getElementById("prod_list").innerHTML=content+'</ul>';

			  //alert(brand_filt);
		  }

	}

	var color_filt = [];
	function colorFilter(obj)
	{
		if($(obj).is(":checked")){//When checked
		    color_filt.push($(obj).val());
		    //alert(brand_filt);
		    var content='<ul class="list-group">';
	    	var strng="";
	    	for(y in r)
	    	   {//document.write(r[y].productBrand);
	    	    	if(color_filt.includes(r[y].productColor))//if particular brand is not there in list then add new one
	    	    		{
	    	    	    strng = "myfunction("+r[y].productId+")";
	    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
	    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
	    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

	    	    		}
	    	    //document.write("myfunction("+r[y].product_id+")");
	    	   };
	    	   document.getElementById("prod_list").innerHTML=content+'</ul>';




		  }else{
		    //alert("Not checked"); //when not checked
			  color_filt.splice(color_filt.indexOf($(obj).val()),1);//to delete the unchecked element
			  var content='<ul class="list-group">';
		    	var strng="";
		    	for(y in r)
		    	   {//document.write(r[y].productBrand);
		    			if(color_filt.length==0)
		    				{
		    				strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';
		    	    		continue;
		    				}
		    	    	if(color_filt.includes(r[y].productColor))//if particular brand is not there in list then add new one
		    	    		{
		    	    	    strng = "myfunction("+r[y].productId+")";
		    	    	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
		    	    	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
		    	    	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>';

		    	    		}
		    	    //document.write("myfunction("+r[y].product_id+")");
		    	   };
		    	   document.getElementById("prod_list").innerHTML=content+'</ul>';

			  //alert(brand_filt);
		  }

	}



	function priceFilter(start_price,end_price)
	{
		var content='<ul class="list-group">';
	 	   var strng="";
	 	   var content="";
	 	    for(y in r)
	 	   {
	 	    	//document.write(r[y].productPrice);
	 	    	//document.write(start_price);
	 	    	//document.write(end_price);
	 	    	if((r[y].productPrice >= start_price) && (r[y].productPrice <= end_price)){
	 	    		//document.write("Inside");
	 	    	strng = "myfunction("+r[y].productId+")";
	 	   	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><a href="#" onclick='+strng+'><img src="';
	 	   	content+=r[y].productImageUrl+'" style="max-width:250px" alt="product"></a></div><div class = "col-sm-8"><h4><a class="hover" href="#" onclick='+strng+'>';
	 	     content+=r[y].productName+'</a></h4><h5 style="color:grey">'+r[y].productDescription+'</h5><h4><br><strong>Rs.'+r[y].productPrice+'</strong></h4><h5 style="color:grey"><strong>Seller : </strong> '+r[y].sellerName+'<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey"><strong>Shipping : </strong>'+r[y].productShipping+'</h5></div></div></li>'
	 	   //document.write("myfunction("+r[y].product_id+")");
	 	    	};
	 	    	};
	 	   document.getElementById("prod_list").innerHTML=content+'</ul>';
	}
/*var r=JSON.parse(JSON.stringify(prod_info_arr));
var content='<ul class="list-group">';
for(y in r)
{
	content+='<li class="list-group-item"><div class="row"><div class = "col-sm-4"><img src="';
	content+=r[y].img_url+'" alt="product"></div><div class = "col-sm-8"><h4><a class="hover" href="#">';
  	content+=r[y].head1+'</a></h4><h5 style="color:grey">'+r[y].head2+'</h5><h4><br><strong>Rs.'+r[y].inr+'</strong></h4><h5 style="color:grey">Buy it Now<button type="button" style = "float:right" class="btn btn-primary">Buy Now</button></h5><h5 style="color:grey">'+r[y].ship+'</h5></div></div></li>'
	};
document.getElementById("prod_list").innerHTML = content;
*/
