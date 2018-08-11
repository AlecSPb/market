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
        url: "http://localhost:8081/addresses/",
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


$(document).ready(function() {
    $('.minus').click(function () {
        var $input = $(this).parent().find('input');
        var count = parseInt($input.val()) - 1;
        count = count < 1 ? 1 : count;
        $input.val(count);
        $input.change();
        return false;
    });
    $('.plus').click(function () {
        var $input = $(this).parent().find('input');
        $input.val(parseInt($input.val()) + 1);
        $input.change();
        return false;
    });
});

/*$("input[type=radio]").click(function (event) {
   var myID=this.id;
   var targetId=myID+"_div";
   $("div.choice:not(#" +targetId+")").addClass(".none");
   $("#"+targetId).removeClass(".none");
});*/
$(document).ready(function () {
    $("input[type=radio]").click(function (){
        var inputValue=$(this).attr("id");
        var targetBox=inputValue+"_div";
        $(targetBox).show();
    }  );
});
/*$('.rbn').on('change', function(){
    if($('#1').attr('checked')){
        $('#div').show();
    } else{
        $('#div').hide();
    }

});*/


