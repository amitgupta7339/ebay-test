$('#Category').on('change', function() {
    if(this.value!= ''){
          $("#NewCategory").attr('disabled','disabled');
      }
    else {
          $("#NewCategory").removeAttr('disabled');
        }
});

$('#sign_out').hover(function(){
  $(this).css('color', 'black');
});

$('#sign_out').mouseover(function(){
  $(this).css('color', 'blue');
});

$('#sign_out').click(function(){
  localStorage.clear();
  alert("signOut");
  window.location="http://localhost:5224/ebaytester/home.html";
});
