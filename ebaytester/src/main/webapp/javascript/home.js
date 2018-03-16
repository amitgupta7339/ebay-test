function searchfunction(){
  if($('#Category').val() == '')
     {
     }
  else {
	  localStorage.category_name=$('#Category').val();
        window.location="http://localhost:5224/ebaytester/prod_list_1.html";
     }
};
