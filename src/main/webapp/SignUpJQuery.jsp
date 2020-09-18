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
    <form action="registration" class="form-signin" enctype="multipart/form-data" id="signUpForm" method="post">
        <div class="text-center mb-4">
            <img alt="" class="mb-4" height="72" src="https://cdn.onlinewebfonts.com/svg/img_524742.png" width="72">
            <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
        </div>
        <c:choose>
            <c:when test="${user!=null}">
                <div class="form-label-group">
                    <input autofocus="" class="form-control" id="firstName" name="firstName" type="text"
                           value="${user.firstName}">
                </div>
                <div class="form-label-group">
                    <input autofocus="" class="form-control" id="secondName" name="secondName"
                           value="${user.secondName}" type="text">
                </div>
                <div class="form-label-group">
                    <input autofocus="" class="form-control" id="email" name="email" value="${user.email}" type="text">
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-label-group">
                     <input autofocus="" class="form-control" id="firstName" name="firstName" placeholder="FirstName" type="text">
                  </div>
                  <div class="form-label-group">
                     <input autofocus="" class="form-control" id="secondName" name="secondName" placeholder="SecondName" type="text" >
                  </div>
                  <div class="form-label-group">
                     <input autofocus="" class="form-control" id="email" name="email" placeholder="Email address" type="text">
                  </div>
               </c:otherwise>
            </c:choose>
            <div class="form-label-group">
               <input class="form-control" id="password1" placeholder="Password" name="psw1" required type="password">
            </div>
            <div class="form-label-group">
               <input class="form-control" id="password2"  placeholder="Password repeat" name="psw2" required type="password">
            </div>

            <label>
            <input type="checkbox" name="emailAccept" value="true"> Remember me
            </label>


            <h4>
               ${alertFromServer}
             </h4>
             <div class="input-group mb-3">
                       <div class="custom-file">
                         <input type="file" name="file" id="file" class="custom-file-input" id="inputGroupFile02">
                         <label class="custom-file-label" for="inputGroupFile02" aria-describedby="inputGroupFileAddon02">Choose file</label>
                       </div>

                     </div>
             <tagFile:custom captchaValue="${captcha.captchaValue}" captchaCodeCookie="${cookie.captchaCookie.value}" captchaCodeHidden="${requestScope.captchaHidden}"/>

            <input class="form-control" id="captcha" placeholder="captcha" name="captcha">
            <button class="btn btn-lg btn-primary btn-block" id="submit" value="Submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted text-center">© 2017-2019</p>
         </form>
      </div>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      <script src="/js/jQuery.js"></script>
   </body>
</html>