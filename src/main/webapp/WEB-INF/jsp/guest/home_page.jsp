<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 19.07.2021
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <!-- <li class="nav-item active">
                <a class="nav-link" href="controller?command=home">
                    ${TEXT[PAGE_HOME]}
                    </a>
            </li>-->
            <li class="nav-item">
                <a class="nav-link"
                   href="controller?command=open-shop-page">
                    ${TEXT[SHOP]}</a>
            </li>
            <!--  <li class="nav-item">
                <a class="nav-link"
                   href="controller?command=open-sign-up-page">
                    ${TEXT[PAGE_SIGN_UP]}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="controller?command=open-home-guest-page">
                    ${TEXT[PAGE_CONTINUE_AS_GUEST]}</a>
            </li>
-->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle"
                   id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    ${TEXT[LANGUAGE]}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="controller?command=change-language&language=Russian">
                        ${TEXT[LANGUAGE_RUSSIAN]}</a>
                    <input type="hidden" name="language" value="English"/>
                    <a class="dropdown-item"
                       href="controller?command=change-language&language=English">
                        ${TEXT[LANGUAGE_ENGLISH]}</a>
                    <div class="dropdown-divider"></div>
                </div>
            </li>
        </ul>


        <!-- <form class="form-inline" >
             <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
             <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
         </form>-->
    </div>
</nav>
</body>
</html>
