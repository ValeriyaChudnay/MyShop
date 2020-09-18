$(document).ready(
    function() {
        $(".custom-select").change(
            function() {
                $("#pageCurrent").attr("at", "1");
                getAll();
            }
        );
        $("#form").submit(
            function(event) {
                event.preventDefault();
                $("#pageCurrent").attr("at", "1");

                getAll();
            });
        $(".page-link").click(
            function() {

                $("#pageCurrent").attr("at", $(this).attr("at"));

                getAll();

            });
    })

function getAll() {
    let url = new URL('http://localhost:8080/catalog?');
    let params = new URLSearchParams(url.search.slice(1));
    url.searchParams.set('sortType', $('#sortType').val());
    url.searchParams.set('amountOnPage', $('#amountOnPage').val());
    url.searchParams.set('pageNumber', $("#pageCurrent").attr("at"));
    $.each($("#category option:selected"), function() {
        url.searchParams.append('idCategory', $(this).val());
    });
    $.each($("#brand option:selected"), function() {
        url.searchParams.append('idBrand', $(this).val());
    });

    if (!!$("#name").val()) {
        url.searchParams.append('productName', $("#name").val());
    }
    if (!!$("#priceFrom").val()) {
        url.searchParams.append('priceFrom', $("#priceFrom").val());
    }
    if (!!$("#priceTo").val()) {
        url.searchParams.append('priceTo', $("#priceTo").val());
    }
    alert(url);
    location.replace(url);
}

//$(document).ready(function(){
//    $(".card-deck div").on("click", function(){
//
//        var dataId = $(this).attr("shoesId");
//          alert( $(this).attr("amount"));
//        let am=$(this).attr("amount");
//         $.get('cart', {
//             shoesId : $(this).attr("shoesId"),
//             amount:am
//         }, function(responseText) {
//         var data = jQuery.parseJSON( responseText );
//         alert(data.amount);
//          $("#cartCount").attr("data-count",data.dataCount);
//          $(this).attr("amount",data.amount);
//          alert( $(this).attr("amount"));
//         alert(responseText);
//         });
//    });
//});



