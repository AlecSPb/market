<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Market</title>
    <link rel="stylesheet" href="${contextPath}css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <hr>
    <main class="products__wrapper">
        <div class="items__wrapper">
            <form method='GET' action="${contextPath}/product">
                <div class="products_item">
                    <div style="background-image: url(${contextPath}/img/products/${product.id}.jpg)"
                         class="items_img"></div>
                    <div class="products__description">
                        <dl>
                            <dt>
                                <a class="link link_product" href="#">${product.title}</a>
                            </dt>
                            <dd>${product.title}, ${product.productParameterDTO.weight}</dd>
                            <dd>Category: ${product.category}</dd>
                            <dd>Brand: ${product.productParameterDTO.brand}</dd>
                            <dd>Color: ${product.productParameterDTO.color}</dd>
                        </dl>
                        <div class="products__description_text">
                            <p>
                                    ${product.description}
                            </p>
                        </div>
                        <h4>Price: ${product.price} $ <a href="${contextPath}/products?added=${product.title}"
                                                             class="link link_header">Add to cart</a></h4>
                    </div>
                </div>
            </form>
        </div>
    </main>
    <footer></footer>
</div>
</body>

</html>