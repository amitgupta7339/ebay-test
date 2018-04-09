var transaction_product_list="";/*use to show products list*/
var result;/*use to store response */
var x;
var pid;
var user = 0;
var count=0;

transaction_list();

/* order related to transaction_id product display */
function transaction_list(){
		transaction_product_list="";
	  $.ajax({
	  				  type :"GET",
	  					url: "http://localhost:5224/ebaytester/webapi/transaction/getATransaction/"+localStorage.user_Id+"/"+localStorage.track_order_txn,
	            dataType:'JSON',
	  					success: function(response){
	            result=response;
	            
	            user = result[0].a1;
	            //alert(user);
	            
	            transaction_product_list+='<ul class="list-group">';
	              for(i in result)
	              {
	            	//alert(user);
	                txn_list(i);/*function call*/
	              }
	              transaction_product_list+='</ul>';

	              document.getElementById('product_display').innerHTML=transaction_product_list;
	            }
	          });
};



function txn_list(x){
	//alert("hello");
	transaction_product_list+='<li class="list-group-item">';
	transaction_product_list+=
					'<div class="row">'+
					  '<div class = "container">'+
						'<div class="col-sm-3">'+
    							'<img id="image1" src="'+result[x].product_img_url+'" style="height:75px;width:75px">'+
    						'</div>'+
    					'<div class="col-sm-8">'+
    						'<div class="row" style="margin-top:10px;">'+
    							'<div class="col-sm-2">'+
    								'<p id = "product_id">'+result[x].product_id+'</p>'+
    								'</div>'+
    							'<div class="col-sm-6">'+
    								'<p id = "product_name">'+result[x].product_name+'</p>'+
    								'</div>'+
    							'<div class="col-sm-4">'+
    								'<button type="submit" class="btn-sm btn-success" id="rate_product" data-toggle="modal" data-target="#rateModal" data-backdrop="static" data-keyboard="false" onclick="sellerRate('+x+')" >Rate Product</button>'+
    								'</div>'+
    						'</div>'+
    						'<div class="row" style="margin-top:10px;">'+
    							'<div class="col-sm-2">'+
    								'<p>Status : </p>'+
    								'</div>'+
    							'<div class="col-sm-6">'+
    								'<p id = "product_status">'+result[x].product_status+'</p>'+
    								'</div>'+
    							'<div class="col-sm-4">'+
    								'<button type="submit" class="btn-sm btn-warning" id="update_staus" onclick="update_staus('+x+')">Update</button>'+
    								'</div>'+
    						'</div>'+
    					'</div>'+
    				'</div>'+	
    			'</div>'+
    		'</li>'
    	
};


function sellerRate(x){
	
	document.getElementById("rateModal_product_name").innerHTML = result[x].product_name ;
	
	$('#rateProduct').click(function(e){
		  //e.preventDefault();
		  var amt = $("#enterAmount").val();		  
		  var e = document.getElementById("rateselecter");
		  var strUser = e.options[e.selectedIndex].value;
		  
		  var str = "http://localhost:5224/ebaytester/webapi/transaction/rateProduct/"
			  + localStorage.user_Id + "/" + localStorage.track_order_txn + "/" + result[x].product_id+"/"+ user +"/"+strUser;
		  //alert(str);
		  
		  $.ajax({
	            type :"PUT",
	            url: "http://localhost:5224/ebaytester/webapi/transaction/rateProduct/"
	            	+ localStorage.user_Id + "/" + localStorage.track_order_txn + "/" + result[x].product_id+"/"+ user +"/"+strUser,
	            dataType:'text',
	            complete: function(response){
	            	
	            	var a = JSON.stringify(response);
					var b = JSON.parse(a);
					alert(b.responseText);
					
					$('#myModal').modal('hide');
					transaction_list();/*function call*/
					
	            }
	          });
		});
	  
	};
	

function update_staus(x) {
	
	if(user == 1){
		//alert("aaya");
		
		$.ajax({
			type : "PUT",
			url : "http://localhost:5224/ebaytester/webapi/transaction/updateUserTransaction/"
				+localStorage.user_Id+"/"+localStorage.track_order_txn+"/"+result[x].product_id,
			dataType : 'text',
			complete : function(response) {
				
				var a = JSON.stringify(response);
				var b = JSON.parse(a);
				alert(b.responseText);
				
				transaction_list();/*function call*/
			}
		});
	}else{
		$.ajax({
			type : "PUT",
			url : "http://localhost:5224/ebaytester/webapi/transaction/updateSellerTransaction/"
					+ localStorage.user_Id + "/" + localStorage.track_order_txn + "/" + result[x].product_id,
			dataType : 'text',
			complete : function(response) {
				var a = JSON.stringify(response);
				var b = JSON.parse(a);
				alert(b.responseText);
				
				transaction_list();/*function call*/
			}
		});
	}
	
};

//onclick="sellerRate('+x+')"
