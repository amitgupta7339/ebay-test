//============================================================Default call of ajax to get all category============================================//
$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/category/getAllCategory",
              dataType: "JSON",
              success: function(response){
                if(response!=null)
                {var i;
                  for (i=0;i<response.length;i++) {
                  $('<option value="'+ response[i].category_name+'">' + response[i].category_name+ '</option>').appendTo('#Category');
                    // catOptions += "<option>" + array[i] + "</option>";
                 }
                }
              }
            });
//=====================================================Action events occurs after click on submit button ===========================================//
$('#admin_category').submit(function(){
   if(($('#Category').val() == '') && ($('#NewCategory').val() == ''))
   {
    alert("choose one field from Category and NewCategory");
   }
   else if(($('#Category').val() == '') || ($('#NewCategory').val() == ''))
   {
     $.ajax({
       type: "post",
       url:  "http://localhost:5224/ebaytester/webapi/category/uploadCategory",
       data: $('#admin_category').serialize(),
       dataType: 'text',
       complete: function(data){
    	   console.log(data);
          alert("Successfully upload");
       }
     });
   }
   else
   {
     alert("choose only one feild from Category and NewCategory");
   }
});
//===============================================================END OF CODE======================================================================//
