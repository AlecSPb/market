<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/app.css">

    <%--<link rel="script" href="../../../js/productsScript.js"/>--%>
    <script src="${contextPath}/js/productsScript.js"></script>
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
                    <dd>
                        <form:input type="text" class="vvod" path="title"
                                    placeholder="Title"/>
                        <form:errors path="title"/>
                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="category"
                                    placeholder="Category"/>
                        <form:errors path="category"/>
                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="productParameterDTO.brand"
                                    placeholder="Brand"/>
                        <form:errors path="productParameterDTO.brand"/>

                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="price"
                                    placeholder="Price"/>
                        <form:errors path="price"/>

                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="count"
                                    placeholder="Count"/>
                        <form:errors path="count"/>

                    </dd>

                    <dd>
                        <form:textarea type="text"  path="description" class="vvod"
                                       placeholder="Description"/>
                        <form:errors path="description"/>

                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="productParameterDTO.color"
                                    placeholder="Color"/>
                        <form:errors path="productParameterDTO.color"/>

                    </dd>

                    <dd>
                        <form:input type="text" class="vvod" path="productParameterDTO.weight"
                                    placeholder="Weight"/>
                        <form:errors path="productParameterDTO.weight"/>

                    </dd>
                </dl>
                <div class="example-2">
                    <div class="form-group">
                        <input type="file" name="picture" id="file" class="input-file">
                        <label for="file" class="btn btn-tertiary js-labelFile">
                            <i class="icon fa fa-check"></i>
                            <span class="js-fileName">Upload file</span>
                        </label>
                    </div>
                </div>
                <input class="knopka01" type="submit" value="Add product">
            </form:form>
        </div>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
