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
                    <dt>
                        Password*
                    </dt>
                    <dd>
                        <form:input type="parole" path="parole"
                                    placeholder="New password"/>
                        <form:errors path="parole"/>
                        <p>Add new parole.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="paroleConfirm">
                    <dt>
                        Repeat password*
                    </dt>
                    <dd>
                        <form:input type="parole" path="paroleConfirm"
                                    placeholder="Confirm new password/"/>
                        <form:errors path="paroleConfirm"/>
                        <p>Repeat new password. Passwords must match!</p>
                    </dd>
                </spring:bind>
            </dl>
            <input type='submit' value='Change password'>
        </form:form>
    </main>
</div>
</body>
</html>
