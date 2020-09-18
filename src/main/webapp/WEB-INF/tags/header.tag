<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<meta charset="utf-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ tag description="Category Options" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <a class="navbar-brand" href="/catalog?sortType=1&amountOnPage=10&pageNumber=1">Catalog</a>
    <button class="navbar-toggler" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="index.jsp">MainPage</a>
    <button class="navbar-toggler" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="addProduct.jsp">Add shoes</a>
    <button class="navbar-toggler" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>
    <c:choose>
        <c:when test="${empty currentUser}">
            <a class="navbar-brand" href="LogIn.jsp">SignIn</a>
            <button class="navbar-toggler" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="registration">SignUp</a>
            <button class="navbar-toggler" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>
            <h8 style="font-size: larger;margin-inline-start: auto;"></h8>
            <a href="cart.jsp">
                <c:choose>
                <c:when test="${empty cart}">
                    <i id="cartCount" data-count="0" style="
    padding-top: 0px;
    padding-bottom: 0px;" class="fa fa-shopping-cart fa-3x icon-grey badge">
                    </i>
                </c:when>
                    <c:otherwise>
                        <i id="cartCount" data-count="${cart.getAmount()}" style="
    padding-top: 0px;
    padding-bottom: 0px;" class="fa fa-shopping-cart fa-3x icon-grey badge">
                        </i>
                    </c:otherwise>
                </c:choose>

            </a>
        </c:when>

        <c:when test="${currentUser!=null}">
            <a class="navbar-brand" href="LogIn.jsp">LogOut </a>
            <button class="navbar-toggler" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>
            <h8 style="font-size: larger;margin-inline-start: auto;">Hi!${currentUser.firstName}</h8>
            <img alt="Avatar" class="avatar" src="${currentUser.imgPath}">
            <a href="cart.jsp">
                <c:choose>
                    <c:when test="${empty cart}">
                        <i id="cartCount" data-count="0" style="
    padding-top: 0px;
    padding-bottom: 0px;" class="fa fa-shopping-cart fa-3x icon-grey badge">
                        </i>
                    </c:when>
                    <c:otherwise>
                        <i id="cartCount" data-count="${cart.getAmount()}" style="
    padding-top: 0px;
    padding-bottom: 0px;" class="fa fa-shopping-cart fa-3x icon-grey badge">
                        </i>
                    </c:otherwise>
                </c:choose>
            </a>
        </c:when>
    </c:choose>


     </nav>