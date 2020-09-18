<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<%@ page isELIgnored="false" %>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" id="bootstrap-css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css"
          rel="stylesheet">
    <title>MyShop</title>
    <link href="css/product.css" rel="stylesheet">
</head>
<body>
<tagFile:header/>

<div class="row">
    <div class="col-lg-3">
        <div class="panel-body-catalog">
            <form action="catalog" id="form" method="get">
                <div class="left-sidebar">
                    <div class="custom-sideBar">
                        <div class="input-group mb-3">
                            <h3 id="header-and-footer">
                                <span class="bd-content-title">Sort
                                    <a aria-label="Anchor" class="anchorjs-link " data-anchorjs-icon="#"
                                       href="#header-and-footer" style="padding-left: 0.375em;"></a></span></h3>
                        </div>
                    </div>

                    <div class="custom-sideBar">
                        <div class="input-group mb-3">
                            <div class="input-group-append">
                                <span class="input-group-text">Category</span>
                            </div>
                            <select class="selectpicker  form-control" data-selected-text-format="count > 3"
                                    data-width="auto" id="category"
                                    multiple name="idCategory">
                                <option
                                <c:if test="${fn:contains(userRequestParameter.category,'1')}">selected</c:if>
                                value="1">Snickers</option>
                                <option
                                <c:if test="${fn:contains(userRequestParameter.category,'2')}">selected</c:if>
                                value="2">Converse</option>
                                <option
                                <c:if test="${fn:contains(userRequestParameter.category,'3')}">selected</c:if>
                                value="3">Heels</option>
                            </select>
                        </div>
                    </div>
                    <div class="custom-sideBar">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">$</span>
                            </div>
                            <div class="input-group-append">
                                <span class="input-group-text">From</span>
                            </div>
                            <input aria-label="Amount (to the nearest dollar)" class="form-control" id="priceFrom"
                                   name="priceFrom"
                                   type="text"
                                   value="${userRequestParameter.priceFrom}">
                            <div class="input-group-append">
                                <span class="input-group-text">To</span>
                            </div>
                            <input aria-label="Amount (to the nearest dollar)" class="form-control" id="priceTo"
                                   name="priceTo"
                                   type="text" value="${userRequestParameter.priceTo}">
                        </div>
                    </div>
                    <div class="custom-sideBar">
                        <div class="input-group mb-3">
                            <div class="input-group-append">
                                <span class="input-group-text">Product name</span>
                            </div>
                            <input aria-label="Amount (to the nearest dollar)" class="form-control" id="name"
                                   name="productName"
                                   type="text" value="${userRequestParameter.name}">
                        </div>
                    </div>
                    <div class="custom-sideBar">
                        <div class="input-group mb-3">
                            <div class="input-group-append">
                                <span class="input-group-text">Brand</span>
                            </div>
                            <select class="selectpicker  form-control" data-selected-text-format="count > 3"
                                    data-width="auto" id="brand"
                                    multiple name="idBrand">
                                <option
                                <c:if test="${fn:contains(userRequestParameter.brand,'1')}">selected</c:if>
                                value="1">Koko</option>
                                <option
                                <c:if test="${fn:contains(userRequestParameter.brand,'2')}">selected</c:if>
                                value="2">Lolo</option>
                                <option
                                <c:if test="${fn:contains(userRequestParameter.brand,'3')}">selected</c:if>
                                value="3">Pepe</option>
                            </select>
                        </div>
                    </div>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="submit" value="Submit">Sign in</button>
            </form>
        </div>

    </div>



    <div class="col-lg-9">
        <div class="panel-body-catalog">
            <div class="left-sidebar">
                <div class="custom-sideBar">
                    <div class="input-group mb-3">
                        <form>
                            <select class="custom-select" id="sortType">
                                <option
                                <c:if test="${userRequestParameter.sortType==null}">selected</c:if>
                                value="1" >Sort</option>
                                <option
                                <c:if test="${userRequestParameter.sortType==1}">selected</c:if>
                                value="1">Price возрастание</option>
                                <option
                                <c:if test="${userRequestParameter.sortType==2}">selected</c:if>
                                value="2">Price down</option>
                                <option
                                <c:if test="${userRequestParameter.sortType==3}">selected</c:if>
                                value="3">Name(A-Z)</option>
                                <option
                                <c:if test="${userRequestParameter.sortType==4}">selected</c:if>
                                value="4">Name(Z-a)</option>
                            </select>
                        </form>
                        <select class="custom-select" id="amountOnPage">
                            <option
                            <c:if test="${userRequestParameter.amountOnPage==10}">selected</c:if>
                            value="10" >10</option>
                            <option
                            <c:if test="${userRequestParameter.amountOnPage==5}">selected</c:if>
                            value="5" >5</option>
                            <option
                            <c:if test="${userRequestParameter.amountOnPage==2}">selected</c:if>
                            value="2" >2</option>
                        </select>
                        <c:set scope="session" value="3" var="countInLine"/>
                        <tagFile:pagination/>

                    </div>
                </div>
            </div>

<c:choose>
    <c:when test="${empty productList}">
        <div class="card-deck">
            <div class="card">
                <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
                    <div background class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light"
                         style="background:url(https://prilukidnz25.ucoz.ua/_si/0/48291323.gif);">
                        <h1>Nothing for you ^-^</h1>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>


        <c:forEach items="${productList}" var="shoes" varStatus="loop">
            <c:if test="${loop.index % countInLine == 0}">
                <div class="card-deck">
            </c:if>
            <div class="card"  amount="1" shoesId="${shoes.id}" >
                <img alt="" class="card-img-top" onmouseout="this.src='${shoes.image1}'"
                     onmouseover="this.src='${shoes.image2}'"
                     src="${shoes.image1}"/>
                <div class="card-body" amount="1" shoesId="${shoes.id}">
                    <h5 class="card-title">${shoes.name}</h5>
                    <p class="card-text">${shoes.description}</p>
                    <h4>${shoes.price}</h4>
                </div>
                <a class="btn btn-primary"  amount="1" shoesId="${shoes.id}" >Add</a>
            </div>
            <c:if test="${loop.index % countInLine == countInLine-1 }">
        </div>
        </c:if>
        </c:forEach>
        </c:otherwise>
        </c:choose>

        </div>

    </div>
    <tagFile:pagination/>
</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="/js/parametr.js"></script>
<script src="/js/cartUpdate.js"></script>
</body>
</html>