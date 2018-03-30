var x;
var content="";
var result="";
//=============================================default ajax call for deals show in Carousel=======================================================//
$.ajax({
  type : "get",
  url : "http://localhost:5224/ebaytester/webapi/products/getDealproductsImage",
  dataType : "JSON",
  success : function(response) {
    if (response != null) {
      result=response;
      var i=0;
      var count=1;
      content="";
      console.log(response.length);
      for(i=0;i<Math.ceil((response.length)/3);i++)
      {
    	  if(response[i].deal!="")
    	  {
           deal_image(i);
    	  }
        if(++count<=response.length && response[i+1].deal!="")
        {
          deal_image(i+1);
        }
        if(++count<=response.length && response[i+2].deal!="")
        {
          deal_image(i+2);
        }
      }
      $('#Deal_Images_List').html(content);
    }
  }
});
//============================================================function of show images dynamic HTML code===========================================//
function deal_image(x)
{
  content+=`<div class="item active">
  <span class="col-sm-4"><a href="#" onclick="deal_products(${x})"><img src="${result[x].product_img_url}" alt="Chicago" style="width:100%;"></a>
  <div class="carousel-caption">
    <h3>${result[x].deal}</h3>
  </div>
  </span>
  </div>`
};
//======================================================after click on image redireact into deal products page in grid view=======================//
function deal_products(x)
{
  localStorage.deal_name=result[x].deal;
  alert(localStorage.deal_name);
  window.location="login.html";//pending
};
//========================================================================END OF CODE=============================================================//
