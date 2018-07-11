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
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="users__main">
        <div class="users__wrapper">
            <table border="1">
                <caption>Users:</caption>
                <tr>
                    <th>id</th>
                    <th>email</th>
                    <th>surname</th>
                    <th>name</th>
                    <th>phone</th>
                    <th>orders</th>
                    <th>addresses</th>
                </tr>
                <c:forEach var="customer" items="${users}">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.email}</td>
                        <td>${customer.secondName}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.phonenumber}</td>
                        <td><a href="${contextPath}/orders?email=${customer.email}">orders</a></td>
                        <td><a href="${contextPath}/addresses?email=${customer.email}">addresses</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>

