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
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="registration__main">
        <form:form  method='POST' modelAttribute="customer" class="registration__wrapper">
            <h2>Change password</h2>
            <dl class="dl_class">
                <spring:bind path="parole">
                    <dd>
                        <form:input type="password" path="parole" class="vvod"
                                    placeholder="Password*"/>
                        <p>Enter new password.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="paroleConfirm">

                    <dd>
                        <form:input type="password" path="paroleConfirm" class="vvod"
                                    placeholder="Confirm password*"/>
                        <p>Repeat new password. Passwords must match!</p>
                    </dd>
                </spring:bind>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <input class="knopka01" type='submit' value='Change password'>
        </form:form>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
