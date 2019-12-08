<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Balance Your Diet</title>
</head>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/css/style.css" />

    <script src="https://kit.fontawesome.com/b4b5996e12.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
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
    <nav class="navbar navbar-expand-lg justify-content-between">
        <a href="${pageContext.request.contextPath}/app/user/dashboard" class="navbar-brand main-logo main-logo-smaller">
            <span>Balance Your Diet</span>
        </a>
        <div class="d-flex justify-content-around">
            <h4 class="text-dark pt-2 mr-3">${userDto.firstName}</h4>
            <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
        </div>
        <a class="btn btn-dark rounded-0 text-light m-1 color-header" href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>
</header>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="/WEB-INF/views/appMenu.jsp" %>

</body>
