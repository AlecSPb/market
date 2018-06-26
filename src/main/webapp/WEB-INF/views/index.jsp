<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SignIn</title>
</head>

<body>

<style type="text/css">
    .style1 {
        color: blue;
        font-size: 36px;
        font-family: "Baskerville Old Face";
        text-align-all: center;
        display: block;
    }
</style>

<style type="text/css">
    .style2 {
        color: red;
        font-size: 24px;
        font-family: Consolas;
        text-align-all: center;
        display: block;
    }
</style>

<div class="style1" align="center">
    <h1>Sign in</h1>
</div>


<div class="style2" align="center">


    ${user}
    <form action="/" id="auth" method="post"></form>

    <p><input name="uname" form="auth">
        <input name="lastname" form="auth">
        <input type="password" name="unumber" form="auth"></p>
    <p><input type="submit" form="auth"></p>
</div>

<div align="center">
    <h2>${message}</h2>
</div>
</body>
</html>