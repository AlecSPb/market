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
        <main class="registration__main">
            <form action="${contextPath}/user/login" method='POST' class="registration__wrapper">
                <h2>Sign in</h2>
                <dl class="dl_class ${error != null ? 'has-error' : ''}">
                    <dt>
                        E-mail
                        <p>${message}</p>
                    </dt>
                    <dd>
                        <input type='text' name="email" id="email"
                               placeholder="E-mail">
                        <p>Enter email.</p>
                        <span>${error}</span>
                    </dd>
                    <dt>
                        Password
                    </dt>
                    <dd>
                        <input type="password" name="parole" id="parole"
                                placeholder="Password*">
                        <p>Enter password.</p>
                    </dd>
                </dl>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <input  class="button19" type='submit' value='SIGN IN'>
            </form>
        </main>
    </div>
</body>
</html>

