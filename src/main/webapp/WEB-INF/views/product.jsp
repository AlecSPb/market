<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" href="${contextPath}css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp" %>
    <hr>
    <main class="products__wrapper">
        <div class="items__wrapper">
            <form:form method='POST' modelAttribute="product" action="${contextPath}/product/${product.id}">
                <div class="products_item">
                    <div style="background-image: url(${contextPath}/img/products/${product.id}.jpg)"
                         class="items_img"></div>
                    <div class="products__description">
                        <dl>
                            <spring:bind path="title">
                                <dt>
                                    <form:input class="vvod" value="${product.title}" path="title"/>
                                </dt>
                            </spring:bind>
                            <spring:bind path="productParameterDTO.weight">
                                <dd>Weight:<form:input path="productParameterDTO.weight" class="vvod"
                                                       value="${product.productParameterDTO.weight}"/></dd>
                            </spring:bind>
                            <spring:bind path="category">
                            <dd>Category: <form:input class="vvod" value="${product.category}" path="category"/></dd>
                            </spring:bind>
                            <spring:bind path="productParameterDTO.brand">
                            <dd>Brand:<form:input class="vvod" value="${product.productParameterDTO.brand}" path="productParameterDTO.brand"/></dd>
                            </spring:bind>
                            <spring:bind path="productParameterDTO.color">
                            <dd>Color: <form:input  path="productParameterDTO.color" class="vvod" value="${product.productParameterDTO.color}"/></dd>
                            </spring:bind>
                            <spring:bind path="description">
                            <form:textarea path="description" class="vvod" ></form:textarea>"
                            </spring:bind>
                            <spring:bind path="count">
                                <dt>
                                    <form:input class="vvod" value="${product.count}" path="count"/>
                                </dt>
                            </spring:bind>
                            <spring:bind path="price">
                            <dd>Price: <form:input path="price" class="vvod" value="${product.price}"/></dd>
                            </spring:bind>
                        </dl>
                    </div>
                </div>
                <input class="knopka01" type='submit' value='CHANGE Details'>
            </form:form>
        </div>
    </main>

</div>
<%@include file="footer.jsp" %>
</body>

</html>