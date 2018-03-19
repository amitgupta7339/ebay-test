$('#login').click(function(){
		     $.ajax({
		       type: "post",
		       url:  "http://localhost:5224/ebaytester/webapi/user/login",
		       data: $('#login_form').serialize(),
		       dataType: "JSON",
		       success: function(response){
		         if(response!=null)
		         { localStorage.clear();
                           localStorage.user_Id=response.user_id;
		           localStorage.fName=response.user_fname;
		           localStorage.lName=response.user_lname;
		           localStorage.email=response.user_email;
			   localStorage.user_Address=response.user_address;
			   localStorage.user_State=response.user_state;
		           localStorage.user_PINCODE=response.user_pincode;
			   localStorage.user_Phone=response.user_phone;
		           if(localStorage.email=='admin@gmail.com')
		        	   {
		        	   window.location="http://localhost:5224/ebaytester/admin_category.html";
		        	   }
          
		           else{
		        	   window.location="http://localhost:5224/ebaytester/home.html";
		           }
		         }
		         else{
		               alert("enter correct password and username");
		             }
		       }
		     });
		   return false;
		 });
