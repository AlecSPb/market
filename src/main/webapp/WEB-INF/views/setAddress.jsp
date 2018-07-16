<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>market-web</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
  <%--  <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>--%>
    <%--<link rel="script" href="../../../js/productsScript.js"/>--%>
   <%-- <script src="${contextPath}/js/makeOrder.js"></script>--%>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="account__main">
       <%-- <div class="addresses_wrapper"  id="currentAddresses${addresses}" >
            <div class="address__wrapper">
                <h2>Add address</h2>
                <dl class="dl_class">
                    <dt>
                        Country
                    </dt>
                    <dd>
                        <input type="text" id="country"
                               placeholder="Country"/>
                        <p>Add country.</p>
                    </dd>
                    <dt>
                        Region
                    </dt>
                    <dd>
                        <input type="text" id="region"
                               placeholder="Region" />
                        <p>Add region.</p>
                    </dd>
                    <dt>
                        City
                    </dt>
                    <dd>
                        <input type="text" id="city"
                               placeholder="City"/>
                        <p>Add city/</p>
                    </dd>
                    <dt>
                        Street
                    </dt>
                    <dd>
                        <input type="text" id="street"
                               placeholder="Street"/>
                        <p>Add street.</p>
                    </dd>
                    <dt>
                        Building
                    </dt>
                    <dd>
                        <input type="text" id="building"
                               placeholder="Building"/>
                        <p>Add number of building.</p>
                    </dd>
                    <dt>
                        Apartment
                    </dt>
                    <dd>
                        <input type="text" id="apartment"
                               placeholder="Apartment"/>
                        <p>Add number of apartment, if you have.</p>
                    </dd>
                    <dt>
                        Postcode
                    </dt>
                    <dd>
                        <input type="text" id="postcode"
                               placeholder="Postcode"/>
                        <p>Add postcode.</p>
                    </dd>
                </dl>
                <div class="account__change_links">
                    <a onclick="addAddress(${addresses})" class="link link_header">
                        Add address
                    </a>
                </div>
            </div>--%>

            <form:form action="${contextPath}/order/set_address" method="post" modelAttribute="orderAddress">
            <h2>Adresses:</h2>
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
                                        <form:radiobutton name="address"
                                                          value="${address.id}"
                                                    path="id"/>
                                        <span >Choose address</span>
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
                    <input type="submit" value="OK">
                </div>
                </form:form>
        </div>
    </main>
</div>
</body>
</html>

