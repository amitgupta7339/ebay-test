$(document).ready(function(){
      $("#aphone").hover(function(){
        document.getElementById('image1').src="image/s-l200.jpg",
        document.getElementById('image2').src="image/s-l140.jpg",
        document.getElementById('image3').src="image/s-l1400.jpg"
      });
      $("#headphone").hover(function(){
        document.getElementById('image1').src="image/s-l201.jpg",
        document.getElementById('image2').src="image/s-l141.jpg",
        document.getElementById('image3').src="image/s-l1401.jpg"
      });
      $("#power").hover(function(){
        document.getElementById('image1').src="image/s-l202.jpg",
        document.getElementById('image2').src="image/s-l142.jpg",
        document.getElementById('image3').src="image/s-l1402.jpg"
      });
      $("#Irressistibly").hover(function(){
        document.getElementById('image1').src="image/s-l203.jpg",
        document.getElementById('image2').src="image/s-l143.jpg",
        document.getElementById('image3').src="image/s-l1403.jpg"
      });
      $("#speaker").hover(function(){
        document.getElementById('image1').src="image/s-l204.jpg",
        document.getElementById('image2').src="image/s-l144.jpg",
        document.getElementById('image3').src="image/s-l1404.jpg"
      });
      $("#trendy").hover(function(){
        document.getElementById('image1').src="image/s-l205.jpg",
        document.getElementById('image2').src="image/s-l145.jpg",
        document.getElementById('image3').src="image/s-l1405.jpg"
      });
    });
    $(document).ready(function () {
      $('.navbar-nav.myTabs .dropdown').hover(function () {
              $(this).find('.dropdown-menu').first().stop(true, true).slideDown(0);
              $(this).toggleClass('open');
          }, function () {
              $(this).find('.dropdown-menu').first().stop(true, true).slideUp(0);
              $(this).toggleClass('open');
          });
      });
      $(document).ready(function () {
        $('.mydrop ').hover(function () {
                $(this).find('.dropdown-menu').first().stop(true, true).slideDown(0);
                $(this).toggleClass('open');
            }, function () {
                $(this).find('.dropdown-menu').first().stop(true, true).slideUp(0);
                $(this).toggleClass('open');
            });
        });
