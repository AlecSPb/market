<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
   <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
   <script src="${contextPath}/js/makeOrder.js"></script>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="account__main">
        <div class="addresses_wrapper"  >
            <a href="${contextPath}/user/account" class="knopka01">Add shipping address</a>
            </div>

            <form:form action="${contextPath}/order/set_address" method="post" modelAttribute="orderAddress">
            <h2>Addresses:</h2>
                <div class="addresses_wrapper" id="currentAddresses">
                    <c:forEach var="address" items="${addresses}">
                        <div class="address__wrapper">
                            <dl>
                                <dt>
                                   Country:
                                </dt>
                                <dd>
                                    <p>${address.country}</p>
                                </dd>
                                <dt>
                                    Region:
                                </dt>
                                <dd>
                                    <p>${address.region}</p>
                                </dd>
                                <dt>
                                    City:
                                </dt>
                                <dd>
                                    <p>${address.city}</p>
                                </dd>
                                <dt>
                                    Street:
                                </dt>
                                <dd>
                                    <p>${address.street}</p>
                                </dd>
                                <dt>
                                    Building:
                                </dt>
                                <dd>
                                    <p>${address.building}</p>
                                </dd>
                                <c:if test="${address.apartment!=null}">
                                    <dt>
                                        Apartment:
                                    </dt>
                                    <dd>
                                        <p>${address.apartment}</p>
                                    </dd>
                                </c:if>
                                <dt>
                                    Postcode:
                                </dt>
                                <dd>
                                    <p>${address.postcode}</p>
                                </dd>
                                <dd>
                                    <label class="menu__line">
                                        <form:radiobutton class="radio " name="address"
                                                          value="${address.id}"
                                                    path="id"/>
                                        <span class="radio-custom"></span>
                                        <span >Select address</span>
                                    </label>
                                </dd>
                            </dl>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${error!=null}">
                    <div class="error">
                        <span>${error}</span>
                    </div>
                </c:if>

                <div>
                    <input  class="knopka01" type="submit" value="Checkout">
                </div>
                </form:form>
        </div>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>

