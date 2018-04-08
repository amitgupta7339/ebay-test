$.ajax({
              type: "get",
              url:  "http://localhost:5224/ebaytester/webapi/subcategory/getAllsubcategoryname",
              dataType: "JSON",
              success: function(response){
                if(response!=null)
                {var i;
                  for (i=0;i<response.length;i++) {
                  $('<option value="'+ response[i].sub_category_name+'">' + response[i].sub_category_name+ '</option>').appendTo('#subCategory_product');
                    // catOptions += "<option>" + array[i] + "</option>";
                 }
                }
              }
            });
