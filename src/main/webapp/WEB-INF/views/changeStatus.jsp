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
        <form:form method="post" action="${contextPath}/change_status" modelAttribute="ordersDTO">
            <dl>
                <dd>
                    Метод оплаты:
                </dd>
                <dt>
                    <c:forEach var="status" items="${orderStatuses}" >
                        <label>
                            <form:radiobutton name="method" value="${status}" path="paymentMethod" />
                            <span>${status}</span>
                        </label>
                    </c:forEach>
                </dt>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <input type="submit" value="Order">
        </form:form>
    </main>
</div>
</body>
</html>
