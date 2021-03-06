<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Balance Your Diet</title>
</head>
<body>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="../resources/css/style.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Gochi+Hand:300,400" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>


</head>

<body>
<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand main-logo">
            <span>Balance Your Diet</span>
        </a>
        <ul class="nav nounderline text-uppercase">
            <li class="nav-item ml-4">
                <a style="font-size:25px;" class="nav-link color-header"
                   href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li class="nav-item ml-4">
                <a style="font-size:25px;" class="nav-link color-header" href="${pageContext.request.contextPath}/register">Register</a>
            </li>
            <li class="nav-item ml-4">
                <a style="font-size:25px;" class="nav-link" href="${pageContext.request.contextPath}/about">About </a>
            </li>

            <li class="nav-item ml-4">
                <a style="font-size:25px;" class="nav-link disabled" href="${pageContext.request.contextPath}/contact">Contact Us</a>
            </li>
        </ul>
    </nav>

</header>
</body>
</html>
