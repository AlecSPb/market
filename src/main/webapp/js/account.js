function deleteAddressForm(id) {
    $("#address"+id).remove();
}

function deleteAddress(id) {
    $.ajax({
        url: "http://localhost:8080/user/addresses/"+id,
        type: 'DELETE',
        success: function () {
            deleteAddressForm(id)
        }
    })
}