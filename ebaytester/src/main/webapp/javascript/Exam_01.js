if($('#product_id').value!= ""){
          $("#Product_Exam_01").hide();
      }
$('#product_id').on('change',function() {
    if(this.value!=''){
          $("#Product_Exam_01").show();
      }
    else {
          $("#Product_Exam_01").hide();
        }
    });
