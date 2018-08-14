




function changeStatus(productId){
    const path = "http://localhost:8081/admin/product/status/";
    $.ajax({
        url: path + productId,
        type: "put",
        success: function (data) {
            $("#hidden"+productId).text(data)
        }
    })
};

/*function addCount(productId){

    const path = "http://localhost:8081/admin/product/count/";
    $.ajax({
        url: path + productId,
        type: "post",
        data:$("#count1").val(),
        success: function (data) {
            $("#count"+productId).text(data)
        }
    })
};*/

$(function () {
    $('table#topProd td:first-child').each(function (i) {
        var count=i+1;
        $(this).html(count);

    });
});
$(function () {
    $('table#topCust td:first-child').each(function (i) {
        var count=i+1;
        $(this).html(count);

    });
});
$(function () {
    $('table#sales td:first-child').each(function (i) {
        var count=i+1;
        $(this).html(count);

    });
});