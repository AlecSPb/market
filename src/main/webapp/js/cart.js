
$(document).ready(function () {



    var body = $('body');



    if (window.history && window.history.pushState) {



        $(window).on('popstate', function () {

            var hashLocation = location.hash;

            var hashSplit = hashLocation.split("#");

            var hashName = hashSplit[1];



            if (hashName !== '' && hashName !== 'order' && hashName !== 'success' && hashName !== 'login') {

                if (hashName !== undefined && hashName.indexOf('offset') === -1) {

                    var hash = window.location.hash;

                    window.history.pushState('forward', null, hashName);

                    if (hash === '') {

                        window.location.href = window.location.href.replace(/[?#].*|$/, "");

                    }

                }

            }

        });



    }



    var errors = document.getElementsByClassName('errors'),

        messages = document.getElementsByClassName('messages'),

        delay = 0;



    var cart = $("#cart"),

        cartList = $(".cart-list"),

        decodedCart = JSON.parse(localStorage.getItem("cart")),

        totalQty = localStorage.getItem("totalQty");



    if (errors.length > 0) {

        for (var n = 0; n < errors.length; n++) {

            var item = errors[n];



            new Noty({

                type: 'error',

                text: item.value,

                layout: 'bottomRight',

                timeout: 5000 + delay,

                animation: {

                    open: 'animated fadeInRight',

                    close: 'animated fadeOutRight'

                }

            }).show();

            delay += 500;

        }

        delay = 0;

    }



    if (messages.length > 0) {

        for (var z = 0; z < messages.length; z++) {

            var message = messages[z];



            new Noty({

                type: 'success',

                theme: 'nest',

                text: message.value,

                layout: 'bottomRight',

                timeout: 5000 + delay,

                animation: {

                    open: 'animated fadeInRight',

                    close: 'animated fadeOutRight'

                }

            }).show();

            delay += 500;

        }

    }



    function updatePrice() {

        var sum = 0,

            updatedCart = JSON.parse(localStorage.getItem("cart"));



        if (updatedCart !== null) {

            for (var i = 0; i < updatedCart.length; i++) {

                var good = JSON.parse(updatedCart[i]);

                sum += good.price * good.qty;

            }

        }

        return sum;

    }



    Array.prototype.remove = function (from, to) {

        var rest = this.slice((to || from) + 1 || this.length);

        this.length = from < 0 ? this.length + from : from;

        return this.push.apply(this, rest);

    };





    if (totalQty === null) {

        totalQty = "0";

        localStorage.setItem("totalQty", totalQty);

        document.getElementById('cart-qty').innerHTML = '0';

        cart.removeClass("dropdown");

    }



    if (totalQty === '0') cart.removeClass("dropdown");



    document.getElementById('cart-qty').innerHTML = totalQty;

    document.getElementById('itemsSelected').innerHTML = totalQty + ' Item(s) selected';

    document.getElementById('subtotal').innerHTML = 'SUBTOTAL: ' + updatePrice();





    if (decodedCart !== null && localStorage.getItem("cart") !== "[]") {

        for (var i = 0; i < decodedCart.length; i++) {

            var good = JSON.parse(decodedCart[i]);

            cartList.append("" +

                "<div id=\"product" + product.id + "\" class=\"product-widget\">\n" +

                " <div class=\"product-img\":url(/img/products/" + product.id + ".jpg)\"" +

                "  <img src=\"" + good.image + "\">\n" +

                " </div>\n" +

                " <div class=\"product-body\">\n" +

                "  <h3 class=\"product-name\"><a href=\"" + good.url + "\">" + good.title + "</a></h3>\n" +

                "  <h4 class=\"product-price\"><span id=\"product" + good.id + "-qty\" class=\"qty\">" + good.qty + "</span>" + good.price + "</h4>\n" +

                " </div>\n" +

                " <button data-url=\"" + good.url + "\" data-category=\"" + good.category + "\" data-id=\"" + good.id + "\" " +

                " data-image-url=\"" + good.image + "\" data-title=\"" + good.title + "\" data-price=\"" + good.price + "\"  class=\"delete\"><i class=\"fa fa-minus\"></i></button>\n" +

                "</div>")

        }

        cart.addClass("dropdown");

    }



    cartList.on('click', '.delete', function () {

        var decodedCart = JSON.parse(localStorage.getItem("cart")),

            totalQty = localStorage.getItem("totalQty"),

            id = $(this).data("id");



        localStorage.setItem("totalQty", --totalQty);

        document.getElementById('cart-qty').innerHTML--;

        document.getElementById('itemsSelected').innerHTML = totalQty + ' Item(s) selected';



        for (var i = 0; i < decodedCart.length; i++) {

            var cartElement = JSON.parse(decodedCart[i]);

            if (cartElement.id === id) {

                --cartElement.qty;

                document.getElementById('product' + id + '-qty').innerHTML = cartElement.qty;

                if (cartElement.qty === 0) {

                    var elem = document.getElementById('product' + id);

                    elem.parentNode.removeChild(elem);

                    decodedCart.remove(decodedCart.indexOf(cartElement));

                    if (decodedCart.length === 0) {

                        cart.removeClass("dropdown");

                    }

                    break;

                }

                decodedCart[i] = JSON.stringify(cartElement);

            }

        }

        localStorage.setItem("cart", JSON.stringify(decodedCart));

        document.getElementById('subtotal').innerHTML = 'SUBTOTAL: ' + updatePrice();

    });



    if (localStorage.getItem("paid") === 'true') {

        cart.removeClass("dropdown");

        cart.on('click', function () {

            $(location).attr('href', 'http://localhost:8080/checkout')

        });

    }

    else {

        body.on('click', '.add-to-cart-btn', function () {

            var cart = $("#cart"),

                cartList = $(".cart-list");

            cart.addClass("dropdown");

            var decodedCart = JSON.parse(localStorage.getItem("cart")),

                totalQty = localStorage.getItem("totalQty"),

                id = $(this).data("id"),

                qty = $(this).data("qty"),

                title = $(this).data("title"),

                price = $(this).data("price"),

                image = $(this).data("image-url"),

                url = $(this).data("url"),

                category = $(this).data("category"),

                color = $(this).data("color");



            var item = {

                id: id,

                title: title,

                price: price,

                image: image,

                url: url,

                category: category,

                qty: qty,

                color: color

            };



            localStorage.setItem("totalQty", ++totalQty);

            document.getElementById('cart-qty').innerHTML++;

            document.getElementById('itemsSelected').innerHTML = totalQty + ' Item(s) selected';



            var exists = false;

            if (decodedCart === null || localStorage.getItem("cart") === "[]") {

                var cart = [];

                cart[0] = JSON.stringify(item);



                localStorage.setItem("cart", JSON.stringify(cart));

                cartList.append("" +

                    "<div id=\"product" + id + "\" class=\"product-widget\">\n" +

                    " <div class=\"product-img\">\n" +

                    "  <img src=\"" + image + "\">\n" +

                    " </div>\n" +

                    " <div class=\"product-body\">\n" +

                    "  <h3 class=\"product-name\"><a href=\"" + url + "\">" + title + "</a></h3>\n" +

                    "  <h4 class=\"product-price\"><span id=\"product" + id + "-qty\" class=\"qty\">1</span>" + price + "</h4>\n" +

                    " </div>\n" +

                    " <button data-color=\"" + color + "\" data-category=\"" + category + "\" data-id=\"" + id + "\" class=\"delete\"><i class=\"fa fa-minus\"></i></button>\n" +

                    "</div>");

            }



            else if (decodedCart.length > 0) {

                for (var i = 0; i < decodedCart.length; i++) {

                    var cartElement = JSON.parse(decodedCart[i]);



                    if (cartElement.id === id) {

                        ++cartElement.qty;

                        document.getElementById('product' + id + '-qty').innerHTML = cartElement.qty;

                        exists = true;

                        decodedCart[i] = JSON.stringify(cartElement);

                        localStorage.setItem("cart", JSON.stringify(decodedCart));

                        break;

                    }

                }

                if (exists === false) {

                    decodedCart[decodedCart.length] = JSON.stringify(item);

                    localStorage.setItem("cart", JSON.stringify(decodedCart));



                    cartList.append("" +

                        "<div id=\"product" + id + "\" class=\"product-widget\">\n" +

                        " <div class=\"product-img\">\n" +

                        "  <img src=\"" + image + "\">\n" +

                        " </div>\n" +

                        " <div class=\"product-body\">\n" +

                        "  <h3 class=\"product-name\"><a href=\"" + url + "\">" + title + "</a></h3>\n" +

                        "  <h4 class=\"product-price\"><span id=\"product" + id + "-qty\" class=\"qty\">1</span>" + price + "</h4>\n" +

                        " </div>\n" +

                        " <button data-color=\"" + color + "\" data-category=\"" + category + "\" data-id=\"" + id + "\" class=\"delete\"><i class=\"fa fa-minus\"></i></button>\n" +

                        "</div>");

                }

                document.getElementById('itemsSelected').innerHTML = totalQty + ' Item(s) selected';

            }

            document.getElementById('subtotal').innerHTML = 'SUBTOTAL: ' + updatePrice();

        });



        $("#clear").on('click', function () {

            localStorage.setItem("cart", "[]");

            localStorage.setItem("totalQty", '0');

            window.location.href = window.location.href.replace(/[?#].*|$/, "");

        });



    }



    $('#color-select').on('change', function () {

        var newColor = $("#color-select option:selected").text();

        $('#addbtn').attr("data-color", newColor);

        if (newColor === "My own color") {

            new Noty({

                type: 'info',

                text: 'You can specify the color in order`s notes',

                timeout: 5000,

                layout: 'bottomRight',

                animation: {

                    open: 'animated fadeInRight',

                    close: 'animated fadeOutRight'

                }

            }).show();

        }

    });



    $.getJSON('/api/categories.getAll', function (data) {



        if (data.length === 0) $("#bottom-cats").append("<li>Soon!</li>");



        if (window.location.toString() === 'http://localhost:8080/') {

            var leftUrl = document.getElementById("leftUrl"),

                rightUrl = document.getElementById("rightUrl"),

                leftTitle = $("#leftTitle"),

                rightTitle = $("#rightTitle");



            switch (data.length) {

                case 0:

                    leftTitle.append("Coming soon");

                    leftUrl.setAttribute("style", "display: none");

                    rightTitle.append("Upcoming");

                    rightUrl.setAttribute("style", "display: none");

                    break;



                case 1:

                    leftTitle.append(data[0].name);

                    leftUrl.setAttribute("href", "/category/" + data[0].id);

                    rightTitle.append("Upcoming");

                    rightUrl.setAttribute("style", "display: none");

                    break;



            }



            if (data.length >= 2) {

                leftTitle.append(data[0].name);

                leftUrl.setAttribute("href", "/category/" + data[0].id);

                rightTitle.append(data[1].name);

                rightUrl.setAttribute("href", "/category/" + data[1].id);

            }



        }



        $.each(data, function (key, value) {

            $("#searchCategories").append("<option id=\"" + value.id + "\" value=\"" + value.id + "\">" + value.name + "</option>");

            $("#bottom-cats").append("<li><a href='/category/" + value.id + "'>" + value.name + "</a></li>");

        });



    });



    eval(function (p, a, c, k, e, r) {

        e = function (c) {

            return c.toString(a)

        };

        if (!''.replace(/^/, String)) {

            while (c--) r[e(c)] = k[c] || e(c);

            k = [function (e) {

                return r[e]

            }];

            e = function () {

                return '\\w+'

            };

            c = 1

        }

        while (c--) if (k[c]) p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);

        return p

    }('$("#0").1(\'l\',3(){4 5({6:\'7\',8:\'9 a b 0 c 1 p e!\',f:\'g\',h:i,j:{k:\'2 m\',n:\'2 o\'}}).d()});', 26, 26, 'easter|on|animated|function|new|Noty|type|info|text|There|are|no|eggs|show|website|layout|bottomRight|timeout|1000|animation|open|click|fadeInRight|close|fadeOutRight|this'.split('|'), 0, {}))



})

;