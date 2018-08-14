function deleteAddressForm(id) {
    $("#address"+id).remove();
};

function deleteAddress(id) {
    $.ajax({
        url: "http://localhost:8081/user/addresses/"+id,
        type: 'DELETE',
        success: function () {
            deleteAddressForm(id)
        }
    })
};