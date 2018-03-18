if($('#Products_name').value!= ""){
          $("#Product_modify_form_upload").hide();
      }
$('#Products_name').on('change',function() {
    if(this.value!=''){
          $("#Product_modify_form_upload").show();
      }
    else {
          $("#Product_modify_form_upload").hide();
        }
    });
