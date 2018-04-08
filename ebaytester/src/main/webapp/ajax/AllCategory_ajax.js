//=============================================================Default call of ajax to get all category============================================//
$.ajax({
        type: "get",
        url:  "http://localhost:5224/ebaytester/webapi/category/getAllCategory",
        dataType: "JSON",
        success: function(response){
          if(response!=null){
            var i;
            for (i=0;i<response.length;i++) {
            $('<option value="'+ response[i].category_name+'">' + response[i].category_name+ '</option>').appendTo('#Category');
              // catOptions += "<option>" + array[i] + "</option>";
           }
          }
        }
     });
//========================================================================END OF CODE==============================================================//
