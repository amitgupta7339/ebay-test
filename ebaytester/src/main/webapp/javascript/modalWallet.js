$('#myPaisaPay').on('click',function(){
  if(localStorage.user_Id == null){
    window.location="http://localhost:5224/ebaytester/login.html";
  }
  else {
    $.ajax({
      type: "get",
      url:  "http://localhost:5224/ebaytester/webapi/user/wallet/"+localStorage.user_Id,
      dataType: "JSON",
      success: function(response){
        // HERE WALLET BALANCE W.R.T THE USER WILL BE SHOWN
        // walletModal_balance
        $('#walletModal_balance').html(response.wallet_balance);
        localStorage.wallet_balance=response.wallet_balance;

      }
    });
  }
});


$('#walletModal_user_name').html(localStorage.fName+" "+localStorage.lName);



// var amt = document.getElementById('enterMoney').value;
// function addMoney(){
//   var amt = $("#enterAmount").val();
//   alert("entered amt is :"+ amt);
//
//   localStorage.amt = amt;
//   alert(localStorage.amt);
//   window.location="http://localhost:5224/ebaytester/Paymentmethod.html";
//
// };

$('#addMoney').click(function(e){
  e.preventDefault();
  var amt = $("#enterAmount").val();
  // alert("entered amt is :"+ amt);

  localStorage.amt = amt;
  // alert(localStorage.amt);
  if(localStorage.amt > 0){
    window.location="http://localhost:5224/ebaytester/Paymentmethod_wallet.html";
  }
  else {
    alert("Enter a valid amount");
  }

  // $('#myModal').modal('hide');
});
