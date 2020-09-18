$(document).ready(function(){
 $("tr td input").mouseleave(
        function(){
        var dataId = $(this).attr("shoesId");
         $.get('cart', {
             shoesId : $(this).attr("shoesId"),
             amount:$(this).val()
         }, function(responseJSON) {
         var sum='#sum'+dataId;
          $(sum).html(responseJSON.sum);
           $('#cartSum').html(responseJSON.total);
          $("#cartCount").attr("data-count",responseJSON.dataCount);
         });
    });
   $(".btn i").on('click',
   function(){
    var dataId = $(this).attr("shoesId");
      $.get('cart', {
           amount:0,
           shoesId : $(this).attr("shoesId")
       }, function(responseJSON) {
     if(dataId>0){$('#'+dataId).remove();}
     else{
       $('tbody').remove();
     }
        $('#cartSum').html(responseJSON.total);
        $("#cartCount").attr("data-count",responseJSON.dataCount);
     });
   });
})