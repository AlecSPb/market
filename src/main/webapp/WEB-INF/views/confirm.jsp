<%--
  Created by IntelliJ IDEA.
  User: miro
  Date: 08.04.18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>market-web</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <script src="${contextPath}/js/makeOrder.js"></script>
    <script src="${contextPath}/js/productsScript.js"></script>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="registration__main">
        <form:form method="post" action="${contextPath}/order/order_confirm" modelAttribute="orderDto">
            <dl>
                <dd>
                    Метод оплаты:
                </dd>
                <dt>
                    <c:forEach varStatus="loop" var="method" items="${paymentMethods}" >
                    <label>
                        <form:radiobutton  class = "radio" id="${loop.count}" name="method" value="${method}" path="paymentMethod" />
                        <span class="radio-custom"></span>
                        <span class="menu__line">${method.description}</span>
                    </label>
                    </c:forEach>
                </dt>
                <div id="1_div" class="none_choice" style="display:none;">
                    <h2>блок с инпутами для ввода данных карты</h2>
                </div>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <div class="popup">
                <input type="submit"  value="Checkout" onclick="confirmFunction()" >
                <span class="popupText" id="myPopup">Your order is confirmed!</span>
            </div>
        </form:form>
    </main>
</div>
</body>
</html>
