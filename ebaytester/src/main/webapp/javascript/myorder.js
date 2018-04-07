var x=parseInt(localStorage.product_list_indexnumber);
var MyOrder_product_list="";/*use to show products list*/
var result;/*use to store response */
var total_amount=0;
var total_amt=0;
var y;
var total_qty=0;
var discount_amount=0;
MyOrder_list_ajax();
function MyOrder_list_ajax(){
$.ajax({
          type :"GET",
          url: "http://localhost:5224/ebaytester/webapi/MyOrder/list/"+localStorage.user_Id,
          dataType:'JSON',
          success: function(response){
          result=response;
          //alert(x);
          if(result[x].deal_id!=0)
          {//alert(result[x].deal_id);
            for(i in result)
            {
              if(result[i].deal_id==result[x].deal_id)
              {//alert(result[x].deal_id);
                total_qty=parseInt(total_qty)+parseInt(result[i].quantity);
                discount_amount=parseInt(parseInt(result[i].amount)*parseInt(result[i].product_discount)/100);
                discount_amount=parseInt(result[i].amount)-discount_amount;
                //discount_amount=parseInt(result[i].amount)-parseInt(parseInt(result[i].amount)*parseInt(result[i].product_discount)));
                total_amount+=parseInt(discount_amount)*parseInt(result[i].quantity);
              }
            }
            DetailInWell(x);
            MyOrder_product_list+=`<ul class="list-group">`
            for(i in result)
            {
              if(result[i].deal_id==result[x].deal_id)
              {//alert(result[x].deal_id);
                MyOrder_list(i);
              }
            }
            MyOrder_product_list+=`</ul>`
          }
          else 
          {//alert("2");
            DetailInWell(x)
            MyOrder_product_list+=`<ul class="list-group">`
            MyOrder_list(x);
            MyOrder_product_list+=`</ul>`
          }
          document.getElementById("MyOrder_list").innerHTML=MyOrder_product_list;
          }
        });
        
      }
function DetailInWell(y){
	//alert("DetailInWell");
  MyOrder_product_list+=`<div class="well row">
    <div class="col-sm-4">
      <p><b>Buyer Address:</b></p>
      <p>${result[y].user_address}</p>
    </div>
    <div class="col-sm-4">
      <p><b>Total amount:</b> Rs.${total_amount}</p>
      <p><b>Trxn id:</b> ${result[y].txn_id}</p>`
      if(result[x].deal_id!=0)
      {
        MyOrder_product_list+=`<p><b>Deal:</b> ${result[y].product_deal}</p>`
      }
  MyOrder_product_list+=`<p><b>Total Qty:</b> ${total_qty}</p>
    </div>
    <div class="col-sm-4">
      <p><b>Seller name:</b> ${result[y].user_fname+" "+result[y].user_lname}</p>
      <p><b>Seller id:</b> ${result[y].user_email}</p>
    </div>
  </div>`
};
function MyOrder_list(y){
	//alert("MyOrder_list");
  MyOrder_product_list+=`<li class="list-group-item">
     <div class="row">
      <div class="col-sm-3">
        <table style=" border-collapse: separate; border-spacing: 0px;" >
          <tr >
            <td colspan="3" style="border: 3px solid #CCC" ><a href="#"><img src="${result[y].product_img_url}" style="height:140px;width:140px"><a></td>
          </tr>
        </table>
      </div>
      <div class="col-sm-9">
        <p>${result[y].product_name}</p>
        <div class="col-sm-4">
          <p style="color:grey"><b>Qty:</b> ${result[y].quantity}</p>`
          if(result[y].product_discount!=0)
          {
            MyOrder_product_list+=`<p style="color:grey"><b>Discount:</b> ${result[y].product_discount}%</p>`
          }
  MyOrder_product_list+=`</div>
        <div class="col-sm-4">`
        if(result[y].product_discount!=0)
        {discount_amount=parseInt(parseInt(result[i].amount)*parseInt(result[i].product_discount)/100);
         discount_amount=parseInt(result[i].amount)-discount_amount;
          //discount_amount=parseInt(result[i].amount)-parseInt(parseInt(result[i].amount)*parseInt(result[i].product_discount))/100);
          MyOrder_product_list+=`<b>Price:</b> <strike>${result[y].amount}</strike> ${discount_amount}</p>`
        }
        else {
          MyOrder_product_list+=`<b>Price:</b>${result[y].amount}</p>`
        	  discount_amount=parseInt(result[y].amount);
        }
        total_amt=parseInt(discount_amount)*parseInt(result[y].quantity)
  MyOrder_product_list+=`<p><b>Total amt:</b> ${total_amt}</p>
        </div>
      </div>
     </div>
    </li>`
}
