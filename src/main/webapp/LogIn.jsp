<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
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

    <tagFile:logIn/>

</div>

</body>
</html>