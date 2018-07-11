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
    <%--<script src="http://code.jquery.com/jquery-3.3.1.js"--%>
            <%--integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="--%>
            <%--crossorigin="anonymous"></script>--%>
    <%--<script src="${contextPath}/js/adminProducts.js"></script>--%>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="users__main">
        <div class="table__wrapper">
            <table border="1">
                <caption>Статистика по продажам:</caption>
                <tr>
                    <th>id</th>
                    <th>Название</th>
                    <th>Категория</th>
                    <th>Цена</th>
                    <th>Цвет</th>
                    <th>размер</th>
                    <th>Количество проданного товара:</th>
                    <th>На сумму:</th>
                </tr>
                <c:forEach var="ord" items="${products}">
                    <tr>
                        <td>${ord.id}</td>
                        <td>${ord.title}</td>
                        <td>${ord.category}</td>
                        <td>${ord.productParameterDTO}</td>
                        <td>${ord.productParameterDTO.color}</td>
                        <td>${ord.productParameterDTO.weight}</td>
                        <td>${ord.count}</td>
                        <td>${ord.count*ord.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="table__wrapper">
            <table border="1">
                <caption>Статистика по клиентам:</caption>
                <tr>
                    <th>id</th>
                    <th>Почта</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Количество купленного товара:</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.secondName}</td>
                        <td>${user.phonenumber}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>

