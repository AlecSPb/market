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
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <%--<link rel="script" href="../../../js/productsScript.js"/>--%>
    <script src="${contextPath}/js/account.js"></script>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="account__main">
        <div class="account__wrapper">
            <h2>Account</h2>
            <form:form id="accountForm" method="post" modelAttribute="customer" action="${contextPath}/user/account">
                <dl>
                    <dt>
                        E-mail:
                    </dt>
                    <dd>
                        <p>
                                ${customer.email}
                        </p>
                    </dd>

                    <dl class="dl_class">
                        <spring:bind path="secondName">
                    <dt>
                        Second Name:
                    </dt>
                        <dd>
                        <p>
                            <c:if test="${customer.secondName==null}">
                                Indicate second name
                            </c:if>
                            <c:if test="${customer.secondName!=null}">
                                ${customer.secondName}
                            </c:if>
                        </p>
                    </dd>
                        </spring:bind>
                        <spring:bind path="firstName">
                    <dt>
                        Name:
                    </dt>
                    <dd>

                        <p>
                            <c:if test="${customer.firstName==null}">
                                Indicate name
                            </c:if>
                            <c:if test="${customer.firstName!=null}">
                                ${customer.firstName}
                            </c:if>
                        </p>
                    </dd>
                        </spring:bind>
                        <spring:bind path="phonenumber">
                    <dt>
                        Phone:
                    </dt>
                    <dd>

                        <p>
                            <c:if test="${customer.phonenumber==null}">
                                Indicate phone
                            </c:if>
                            <c:if test="${customer.phonenumber!=null}">
                                ${customer.phonenumber}
                            </c:if>
                        </p>
                    </dd>
                        </spring:bind>
                </dl>
            </form:form>
            <div class="account__change_links">
                <ul>
                    <li>
                        <a href="${contextPath}/user/change_details" class="link link_header">Edit</a>
                    </li>

                    <li>
                        <a href="${contextPath}/user/change_parole" class="link link_header">Change password</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="addresses_wrapper">
            <h2>Addresses:</h2>
            <c:if test="${addresses!=null}">
                    <c:forEach var="address" items="${addresses}">
                        <div id="address${address.id}" class="address__wrapper">
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
                                <dd><a onclick="deleteAddress(${address.id})"
                                       class="link link_header">Remove address</a> </dd>
                            </dl>
                        </div>
                    </c:forEach>
            </c:if>
            <c:if test="${addresses==null}">
                <h3>Address not specified</h3>
            </c:if>
            <div class="account__change_links">
                        <a href="${contextPath}/user/add_address" class="link link_header">
                            Add address
                        </a>
            </div>
        </div>
    </main>
</div>
</body>
</html>

