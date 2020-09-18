$(document).ready(function(){
    $(".card-deck div a").on("click", function(){
        var dataId = $(this).attr("shoesId");
          alert("You add in cart )");
         $.get('cart', {
         action:"add",
         amount:1,
             shoesId : $(this).attr("shoesId")
         }, function(responseJSON) {
         $("#cartCount").attr("data-count",responseJSON.dataCount);
         });
    });
});
