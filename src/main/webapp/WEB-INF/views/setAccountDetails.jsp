<%--
  Created by IntelliJ IDEA.
  User: miro
  Date: 08.04.18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
            <h2>Add address</h2>
            <dl class="dl_class">
                <spring:bind path="secondname">
                    <dt>
                        Second name
                    </dt>
                    <dd>
                        <form:input type="text" path="secondName"
                                    placeholder="Second name"/>
                            <form:errors path="secondName"/>
                        <p>Indicate second name.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="firstName">
                    <dt>
                        Name
                    </dt>
                    <dd>
                        <form:input type="text" path="firstName"
                                    placeholder="Name"/>
                            <form:errors path="firstName"/>
                        <p>Indicate name.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="phonenumber">
                    <dt>
                        Phone number
                    </dt>
                    <dd>
                        <form:input type="text" path="phonenumber"
                                    placeholder="phone number"/>
                            <form:errors path="phonenumber"/>
                        <p>Indicate phone number.</p>
                    </dd>
                </spring:bind>
            </dl>
            <input type='submit' value='Add'>
        </form:form>
    </main>
</div>
</body>
</html>
