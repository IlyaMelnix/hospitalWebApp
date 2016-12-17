<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>

    <link type="text/css" rel="stylesheet" href="../../css/style.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Система "Больница"</title>
</head>
<body class="grey lighten-4">

    <nav>
        <div class="nav-wrapper container">
            <a href="controller?command=login" class="brand-logo light"><i class="large material-icons">local_hospital</i>Больница</a>


            <c:if test="${currentUser != null}">

                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>

                <ul class="right hide-on-med-and-down">
                    <li><a href="controller?command=UpdateUser&id=${currentUser.iduser}"><i class="material-icons left">person</i>${currentUser.status} ${currentUser.name}</a></li>
                    <li><a href="controller?command=Logout" class="waves-effect waves-light btn">Выйти из системы</a></li>
                </ul>

                <ul class="side-nav" id="mobile-demo">
                    <li class="black-text center-align"><a href="controller?command=UpdateUser&id=${currentUser.iduser}"><i class="material-icons">person</i>${currentUser.status} ${currentUser.name}</a></li>
                    <li><a href="controller?command=Logout" class="waves-effect waves-light btn">Выйти из системы</a></li>
                </ul>

            </c:if>

        </div>
    </nav>