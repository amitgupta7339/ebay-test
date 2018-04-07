var MyOrder_product_list="";/*use to show products list*/
var result;/*use to store response */
var x;
MyOrder_list_ajax();
function MyOrder_list_ajax(){
$.ajax({
          type :"GET",
          url: "http://localhost:5224/ebaytester/webapi/MyOrder/list/"+localStorage.user_Id,
          dataType:'JSON',
          success: function(response){
          result=response;
          MyOrder_product_list+='<ul class="list-group">';
            for(i in result)
            {
              MyOrder_list(i);/*function call*/
            }
            MyOrder_product_list+='</ul>';
            document.getElementById('MyOrder_list').innerHTML=MyOrder_product_list;
          }
        });
      }
function MyOrder_list(x){
  MyOrder_product_list+=`<li class="list-group-item">
                        <div class="row">
                          <p>Trxn_id: ${result[x].txn_id}</p>
                          <p>product_id: ${result[x].product_id}</p>
                         <div class="col-sm-3">
                           <table style=" border-collapse: separate; border-spacing: 0px;" >
                             <tr >
                               <td colspan="3" style="border: 3px solid #CCC" ><a href="#"><img src="${result[x].product_img_url}" style="height:150px;width:150px"><a></td>
                             </tr>
                           </table>
                         </div>
                         <div class="col-sm-9">
                           <p>${result[x].product_name}</p>
                           <br>
                           <p style="color:grey">Order date: ${result[x].order_date}</p>
                           <p style="text-align:right" onclick="product_order_detail(${x})">view order details<span class="glyphicon glyphicon-menu-right"></span></p>
                         </div>
                        </div>
                      </li>`
                    };
function product_order_detail(x){
  localStorage.product_list_indexnumber=x;
  window.location="http://localhost:5224/ebaytester/myorder.html";
};
