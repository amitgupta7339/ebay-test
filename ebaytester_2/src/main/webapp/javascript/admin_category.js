
//$('#Category').on('mousedown', function() {
          //  $("#Category").attr('size',10);
            
//      });
//$('#Category').on('change', function() {
//	this.blur();
//});
//$('#Category').on('blur', function() {
//	$("#Category").attr('size',0);
//});
$('#Category').on('change', function() {
    if(this.value!= ''){
          $("#NewCategory").attr('disabled','disabled');
      }
    else {
          $("#NewCategory").removeAttr('disabled');
        }
    });