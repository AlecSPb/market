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
                        <p>${message}</p>
                    </dt>
                    <dd>
                        <input type="text" class="vvod" name="email" id="email"
                               placeholder="E-mail">
                        <span>${error}</span>
                    </dd>
                    <dt>
                    </dt>
                    <dd>
                        <input type="password" class="vvod" name="parole" id="parole"
                                placeholder="Password*">
                    </dd>
                </dl>
                <div class="sign-up">Already have an account? <a href="${contextPath}/user/registration">Sign up</a></div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <input  class="knopka01" type='submit' value='Sign in'>
            </form>
        </main>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>

