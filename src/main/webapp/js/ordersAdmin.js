function changeStatus(orderId){
    const path = "http://localhost:8080/admin/order/status/";
    $.ajax({
        url: path + orderId,
        type: "put",
        success: function (data) {
            $("#orderId_"+orderId).text(data)
        }
    })
}