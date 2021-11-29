<%-- 
    Document   : login
    Created on : Oct 3, 2021, 7:27:49 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="loginAction" method="POST">
            Email: <input type="text" name="txtEmail" value="" /> </br>
            Password: <input type="password" name="txtPassword" value="" /> </br>
            <input type="submit" value="Login" name="btAction" /> </br>
            <input type="reset" value="Reset" /> </br>
        </form>
        
        <a href="registerPage">Click here to create new account</a> </br>
        <a href="loadArticleAction">Home Page</a>
    </body>
</html>
