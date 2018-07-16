function getAddressDto() {

    let addressDto = {

        country: $("#country").val(),
        region: $("#region").val(),
        city: $("#city").val(),
        street: $("#street").val(),
        building: $("#building").val(),
        apartment: $("#apartment").val(),
        postcode: $("#postcode").val(),

    };

    return addressDto
}

function getAddressHtml(address) {
    return [
        "<div class=\"address__wrapper\">" +
        "                            <dl>" +
        "                                <dt>" +
        "                                    Country:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.country + "</p>" +
        "                                </dd>" +
        "                                <dt>" +
        "                                    Region:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.region + "</p>" +
        "                                </dd>" +
        "                                <dt>" +
        "                                    City:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.city + "</p>" +
        "                                </dd>" +
        "                                <dt>" +
        "                                    Street:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.street + "</p>" +
        "                                </dd>" +
        "                                <dt>" +
        "                                    Building:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.building + "</p>" +
        "                                </dd>" +
        "                                    <dt>" +
        "                                        Apartment:" +
        "                                    </dt>" +
        "                                    <dd>" +
        "                                        <p>" + address.apartment + "</p>" +
        "                                    </dd>" +
        "                                <dt>" +
        "                                    Postcode:" +
        "                                </dt>" +
        "                                <dd>" +
        "                                    <p>" + address.postcode+ "</p>" +
        "                                </dd>" +
        "                                <dd>" +
        "                                    <label class=\"menu__line\">" +
        "                                        <input type='radio' name=\"id\"" +
        "                                                          value=\"" + address.id + "\"" +
        "                                                    />" +
        "                                        <span >Choose address</span>" +
        "                                    </label>" +
        "                                </dd>" +
        "                            </dl>" +
        "                        </div>"
    ]
}

function addAddress() {
    let addressDTO = getAddressDto();

    $.ajax({
        url: "http://localhost:8080/addresses/",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(addressDTO),
        success: function (data) {
            addressDTO.id = data;
            $("#currentAddresses").append(getAddressHtml(addressDTO))
        },
        error: function (error) {
            console.log(error);
        }
    })
}