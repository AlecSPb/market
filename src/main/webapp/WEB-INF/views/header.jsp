<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<header class="header__wrapper">
    <div class="header__logo">
        <a href="${contextPath}/home"></a>
    </div>
    <div class="header__contacts">
        <div class="contacts__item">
            <a href="mailto:mail@mail.ru" class="link link_contact">mail@mail.ru</a>
        </div>
        <div class="contacts__item">
            <a href="tel:88005553535" class="link link_contact">88005553535</a>
        </div>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <ul class="header__authorization">
            <li>
                <a href="${contextPath}/user/login" class="link link_header">Войти</a>
            </li>
            <li>
                <a href="${contextPath}/user/registration" class="link link_header">Регистрация</a>
            </li>
        </ul>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <ul class="header__authorization">
            <li>
                <a href="${contextPath}/bucket" class="link link_header">Корзина</a>
            </li>
            <li>
                <a href="${contextPath}/user/account" class="link link_header">Аккаунт</a>
            </li>
            <li>
                <a class="link link_header" onclick="document.forms['logoutForm'].submit()">Выйти</a>
            </li>
        </ul>
    </c:if>
    <nav class="header__menu">
        <ul class="menu__wrapper">
            <li>
                <a href="${contextPath}/home" class="link link_header">Начальная страница</a>
            </li>
            <li>
                <a href="${contextPath}/products" class="link link_header">Продукция</a>
            </li>
            <li>
                <a href="${contextPath}/bucket" class="link link_header">Корзина</a>
            </li>
            <li>
                <a href="${contextPath}/order/history" class="link link_header">Заказы</a>
            </li>
        </ul>
    </nav>

</header>
