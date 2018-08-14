<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/app.css">
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <script src="${contextPath}/js/makeOrder.js"></script>
</head>

<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <br>
    <main>
        <div class="bucket__wrapper">
            <div class="bucket__head">
                <h2>Your Cart: </h2>
            </div>
            <form:form method="post" action="${contextPath}/order/set_count/" modelAttribute="ordersDTO">
                <c:forEach var="product" items="${ordersDTO.productDTOs}" varStatus="ind">
                    <div class="bucket__item">
                        <div class="bucket__item_column">
                            <dl>
                                <dt class="column__type">Title and characteristic of item</dt>
                                <dd class="bucket__item_description">
                                    <div style="background-image: url(${contextPath}/img/products/${product.id}.jpg)"
                                         class="bucket__item_img"></div>
                                    <div class="products__description">
                                        <dl>
                                            <dt>
                                                <a class="link link_product">${product.title}</a>
                                            </dt>
                                            <dd>Brand: ${product.productParameterDTO.brand}</dd>
                                            <dd>Weight: ${product.productParameterDTO.weight}</dd>
                                            <dd> Color: ${product.productParameterDTO.color}</dd>
                                            <dd> Price: ${product.price}</dd>
                                        </dl>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                        <div class="bucket__item_column">
                            <dl>
                                <dt class="column__type">Amount</dt>
                                <dd class="bucket__item_description">
                                    <p>${product.price} $.</p>
                                </dd>
                            </dl>
                        </div>
                        <div class="bucket__item_column">
                            <dl>
                                <dt class="column__type">Category</dt>
                                <dd class="bucket__item_description">
                                    <p>${product.category}</p>
                                </dd>
                            </dl>
                        </div>
                        <div class="bucket__item_column">
                            <dl>
                                <dt class="column__type">Quantity</dt>
                                <dd class="bucket__item_description">
                                    <p>${product.count}</p>
                                </dd>
                            </dl>
                        </div>
                        <div class="bucket__item_column">
                            <dl>
                                <dt class="column__type">Select</dt>
                                <dd class="bucket__item_description">
                                    <label class="menu__line">
                                        <span>Quantity: </span>
                                            <span class="minus"><button class ="button1" >-</button></span>
                                            <form:input type="text" name="counts" path="counts" value="1" size="5" readonly="true"/>
                                            <span class="plus"><button class ="button1" >+</button></span>

                                    </label>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
                </c:if>
                <input class="knopka01" type="submit" value="Add address">
            </form:form>
        </div>
    </main>

</div>
<%@include file="footer.jsp"%>
</body>

</html>
