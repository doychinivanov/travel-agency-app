$(document).ready(function(){
  $(".owl-carousel").owlCarousel({
    loop: true,
    autoplay: true,
    autoplayHoverPause: true,
    responsiveClass: true,
    responsive:{
        0:{
            items: 1,
        },
        600:{
            items: 1
        },
        700:{
            items: 2
        },
        1100:{
            items:3,
        },
        1300:{
            items:4,
        }
    }
  });
});