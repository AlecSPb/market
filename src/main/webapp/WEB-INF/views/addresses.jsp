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
            <table>
                <caption>${email}'s Addresses:</caption>
                <tr>
                    <th>id</th>
                    <th>postcode</th>
                    <th>country</th>
                    <th>region</th>
                    <th>city</th>
                    <th>street</th>
                    <th>building</th>
                    <th>apartment</th>
                </tr>
                <c:forEach var="adr" items="${addresses}">
                    <tr>
                        <td>${adr.id}</td>
                        <td>${adr.postcode}</td>
                        <td>${adr.country}</td>
                        <td>${adr.region}</td>
                        <td>${adr.city}</td>
                        <td>${adr.street}</td>
                        <td>${adr.building}</td>
                        <td>${adr.apartment}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>

