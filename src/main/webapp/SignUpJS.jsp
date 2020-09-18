<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/1999/XSL/Transform">
<head>
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">
    <meta charset="utf-8">
    <title>MyShop</title>
    <link href="/css/shoes.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jspf" ></jsp:include>
<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
<form action="/index.html" class="form-signin" id="signUpForm" method="post" onsubmit="return checkForm(this);">
    <div class="text-center mb-4">
        <img alt="" class="mb-4" height="72" src="https://cdn.onlinewebfonts.com/svg/img_524742.png" width="72">
        <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
    </div>
    <div class="form-label-group">
        <input autofocus="" class="form-control" id="emailField" name="email" placeholder="Email address" type="text">
    </div>

    <div class="form-label-group">
        <input class="form-control" id="passwordField" name="pwd1" placeholder="Password" required type="password">
    </div>
    <div class="form-label-group">
        <input class="form-control" id="passwordField2" name="pwd2" placeholder="Repeat Password" required
               type="password">
    </div>
    <button class="btn btn-lg btn-primary btn-block" id="okButton" type="submit" value="Submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted text-center">© 2017-2019</p>
</form>
</div>
<script src="js/jsPlain.js"></script>
</body>
</html>