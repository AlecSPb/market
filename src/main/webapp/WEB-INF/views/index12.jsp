<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<%--//utf-8 filter Spring--%>
<%--Character encoding filter--%>
<!doctype html>
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/app.css">
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
     <div class="account__wrapper">
    <h2>Welcome to the online store HardCandy!</h2>
    <p>
        A place where you can buy cosmetics Sleek MakeUP, Essence, CATRICE, NYX, l Techniques, Makeup Revolution and many other famous brands.</p>

    <p>We appreciate you and your choice. We will try to do our best so that you can make the desired purchase as comfortable as possible!</p>

    <p>
        HardCandy - a new positive and comfortable online store, and we will try to make you not only convenient, but also profitable!</h2>
    </p>
</div>

    <main>
        <div class="mycode__logo">
            <a href="${contextPath}/"></a>
        </div>

    </main>
</div>
<%@include file="footer.jsp"%>
</body>

</html>
