var form = $('#loginform');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
console.log(data);
if(data!=null)
	{ window.location.href = "home.html";
	}
else
	{
	 alert("please enter correct username and password")
	}
}
});
return false;
});