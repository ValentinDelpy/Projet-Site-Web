<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" href="resources/css/style_log.css">
        <!--<meta name="viewport" content="width=device-width, initial-scale=1">
        -->
        <meta charset="utf-8">
        <link rel="shortcut icon" type="icon" href="../logo.png"/>
        <title>connexion</title>
    </head>
    <body>

        <section class="connexion container  z-depth-4">

        <form class="col s12 login-form" action="<c:url value="LoginController" />" method="POST">
            <h2>Login</h2>
            <div class="imgcontainer">
                <img src="avatar.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="uname" required>
                <br>
                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>

                <button type="submit" name="action" value="login">Login</button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
            </div>

            <!--<div class="container" style="background-color:#f1f1f1">
            </div>-->
        </form>
            </section>
    </body>
</html>
