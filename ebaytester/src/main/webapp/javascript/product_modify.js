$('#Products_name').on('change', function() {
    if(this.value!= ''){
          $("#Product_modify_form").attr('disabled','disabled');
      }
    else {
          $("#Product_modify_form").removeAttr('disabled');
        }
    });
