function changeStatus(productId){
    const path = "http://localhost:8081/product/status/";
    $.ajax({
        url: path + productId,
        type: "put",
        success: function (data) {
            $("#hidden"+productId).text(data)
        }
    })
}