<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="registration__wrapper">
            <h2>Add new product</h2>
            <form:form  enctype="multipart/form-data"
                        method="POST"
                        action="${contextPath}/admin/add_new_product"
                        modelAttribute="product">
                <dl class="dl_class">
                    <dt>
                        Title
                    </dt>
                    <dd>
                        <form:input type="text" path="title"
                                    placeholder="Title"/>
                        <form:errors path="title"/>
                        <p>Add title of product</p>
                    </dd>
                    <dt>
                        Category
                    </dt>
                    <dd>
                        <form:input type="text" path="category"
                                    placeholder="Category"/>
                        <form:errors path="category"/>
                        <p>Add Category</p>
                    </dd>
                    <dt>
                        Brand
                    </dt>
                    <dd>
                        <form:input type="text" path="productParameterDTO.brand"
                                    placeholder="Brand"/>
                        <form:errors path="productParameterDTO.brand"/>
                        <p>Add brand.</p>
                    </dd>
                    <dt>
                        Price
                    </dt>
                    <dd>
                        <form:input type="text" path="price"
                                    placeholder="Price"/>
                        <form:errors path="price"/>
                        <p>Add price</p>
                    </dd>
                    <dt>
                        Count
                    </dt>
                    <dd>
                        <form:input type="text" path="count"
                                    placeholder="Count"/>
                        <form:errors path="count"/>
                        <p>Add count.</p>
                    </dd>
                    <dt>
                        Description
                    </dt>
                    <dd>
                        <form:textarea type="text" path="description"
                                       placeholder="Description"/>
                        <form:errors path="description"/>
                        <p>Add description.</p>
                    </dd>
                    <dt>
                        Color
                    </dt>
                    <dd>
                        <form:input type="text" path="productParameterDTO.color"
                                    placeholder="Color"/>
                        <form:errors path="productParameterDTO.color"/>
                        <p>Add color.</p>
                    </dd>
                    <dt>
                       Weight
                    </dt>
                    <dd>
                        <form:input type="text" path="productParameterDTO.weight"
                                    placeholder="Weight"/>
                        <form:errors path="productParameterDTO.weight"/>
                        <p>Add weight.</p>
                    </dd>
                </dl>
                File to upload: <input type="file" name="picture">
                <input type="submit" value="Add product">
            </form:form>
        </div>
    </main>
</div>
</body>
</html>
