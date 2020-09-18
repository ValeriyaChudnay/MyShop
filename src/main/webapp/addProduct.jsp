<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<%@ page isELIgnored="false" %>
<head>
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">
    <meta charset="utf-8">
    <title>MyShop</title>
    <link href="css/product.css" rel="stylesheet">
</head>
<body>
<tagFile:header/>
<div class="panel-body">
    <form action="addProduct" class="form-signin" enctype="multipart/form-data" id="signUpForm" method="post">
        <div class="text-center mb-4">
            <img alt="" class="mb-4" height="72" src="https://cdn.onlinewebfonts.com/svg/img_524742.png" width="72">
            <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
        </div>

        <div class="form-label-group">
            <input autofocus="" class="form-control" id="name" name="name" placeholder="Name"
                   type="text">
        </div>
        <div class="form-label-group">
            <input autofocus="" class="form-control" id="description" name="description" placeholder="Description"
                   type="text">
        </div>
        <div class="form-label-group">
            <input autofocus="" class="form-control" id="category" name="category" placeholder="Category number"
                   type="text">
        </div>
        <div class="form-label-group">
            <input autofocus="" class="form-control" id="price" name="price" placeholder="Price "
                   type="text">
        </div>


        <div class="input-group mb-3">
            <div class="custom-file">
                <input class="custom-file-input" id="file1" name="file1" type="file">
                <label aria-describedby="inputGroupFileAddon02" class="custom-file-label" for="file1">Choose
                    file</label>
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="custom-file">
                <input class="custom-file-input" id="file2" name="file2" type="file">
                <label aria-describedby="inputGroupFileAddon02" class="custom-file-label" for="file2">Choose
                    file</label>
            </div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="submit" value="Submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted text-center">© 2017-2019</p>
    </form>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</body>
</html>