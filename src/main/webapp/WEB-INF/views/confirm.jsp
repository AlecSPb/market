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
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
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
                <div id="card" class="none_choice" style="display:none;">
                    <fieldset>
                        <div id="legend">
                            <legend class="">Payment</legend>
                        </div>

                        <!-- Name -->
                        <div class="control-group">
                            <label class="control-label"  for="username">Card Holder's Name</label>
                            <div class="controls">
                                <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
                            </div>
                        </div>

                        <!-- Card Number -->
                        <div class="control-group">
                            <label class="control-label" for="email">Card Number</label>
                            <div class="controls">
                                <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
                            </div>
                        </div>

                        <!-- Expiry-->
                        <div class="control-group">
                            <label class="control-label" for="password">Card Expiry Date</label>
                            <div class="controls">
                                <select class="span3" name="expiry_month" id="expiry_month">
                                    <option></option>
                                    <option value="01">Jan (01)</option>
                                    <option value="02">Feb (02)</option>
                                    <option value="03">Mar (03)</option>
                                    <option value="04">Apr (04)</option>
                                    <option value="05">May (05)</option>
                                    <option value="06">June (06)</option>
                                    <option value="07">July (07)</option>
                                    <option value="08">Aug (08)</option>
                                    <option value="09">Sep (09)</option>
                                    <option value="10">Oct (10)</option>1
                                    <option value="11">Nov (11)</option>
                                    <option value="12">Dec (12)</option>
                                </select>
                                <select class="span2" name="expiry_year">
                                    <option value="13">2013</option>
                                    <option value="14">2014</option>
                                    <option value="15">2015</option>
                                    <option value="16">2016</option>
                                    <option value="17">2017</option>
                                    <option value="18">2018</option>
                                    <option value="19">2019</option>
                                    <option value="20">2020</option>
                                    <option value="21">2021</option>
                                    <option value="22">2022</option>
                                    <option value="23">2023</option>
                                </select>
                            </div>
                        </div>

                        <!-- CVV -->
                        <div class="control-group">
                            <label class="control-label"  for="password_confirm">Card CVV</label>
                            <div class="controls">
                                <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="span2">
                            </div>
                        </div>



                    </fieldset>
                </div>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <div class="popup">
                <input type="submit" class="knopka01" value="Checkout" onclick="confirmFunction()" >
                <span class="popupText" id="myPopup">Your order is confirmed!</span>
            </div>
        </form:form>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
