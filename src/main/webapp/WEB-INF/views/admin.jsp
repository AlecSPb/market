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
    <%--<link rel="script" href="../../../js/productsScript.js"/>--%>
    <script src="${contextPath}/js/account.js"></script>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="account__main">
        <div class="account__wrapper">
            <div class="account__change_links">
                <ul>
                    <li>
                        <a href="${contextPath}/admin/users" class="knopka01">Customers</a>
                    </li>
                    <li>
                        <a href="${contextPath}/admin/production" class="knopka01">Product</a>
                    </li>
                    <li>
                        <a href="${contextPath}/admin/add_new_product" class="knopka01">Add new product</a>
                    </li>
                    <li>
                        <a href="${contextPath}/admin/statistic" class="knopka01">Statistic</a>
                    </li>
                </ul>
            </div>
        </div>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>

