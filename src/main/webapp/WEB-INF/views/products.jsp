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
        <script src="http://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
                crossorigin="anonymous"></script>
        <%--<link rel="script" href="../../../js/productsScript.js"/>--%>
        <script src="${contextPath}/js/productsScript.js"></script>
        <script src="${contextPath}/js/smoke-pure.js"></script>

    </head>
    <body>
        <div class="bg__wrapper"></div>
        <div class="container__wrapper">
            <%@include file="header.jsp"%>
            <hr>
            <main class="products__wrapper">
                    <div class="products__menu">
                    <h3>Categories:</h3>
                        <ul>
                        <c:forEach items="${categories}" var="category">
                            <li>
                                <label>
                                    <input class="checkbox" type="checkbox"  name="brand" value="${category}">
                                    <span class="checkbox-custom"></span>
                                    <span class="menu__line">
                                        <span>${category}</span>
                                    </span>
                                </label>
                            </li>
                        </c:forEach>
                    </ul>
                    <input type="submit"
                           class="knopka01"
                           value="Show"
                           onclick="makeItWork()">
                </div>
                <div class="items__wrapper">
                    <div class="products__sorting">
                        <ul>
                            <li>
                                <span>Sorted by:</span>
                            </li>
                            <li>
                                <label>
                                    <input class="radio" type="radio" name="sortedBy" value="price" title="Price">
                                    <span class="radio-custom"></span>
                                    <span class="menu__line">price</span>
                                </label>
                            </li>
                            <li>
                                <label>
                                    <input class ="radio" type="radio" name="sortedBy" value="title" title="Title">
                                    <span class="radio-custom"></span>
                                    <span class="menu__line">title</span>
                                </label>
                            </li>
                        </ul>
                        <input type="submit"
                               class="knopka01"
                               value="Sort"
                               onclick="showProducts()">
                    </div>
                    <div id="products">
                        <script>
                            showAllProducts();
                            showAllPagesCount();
                        </script>
                    </div>
                </div>
                <div class="page__buttons">
                    <ul id="pageButtons">
                    </ul>
                </div>
            </main>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>