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
    <title>Market</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main>
        <div class="mycode__logo">
            <a href="${contextPath}/"></a>
        </div>
    </main>
</div>

</body>

</html>
