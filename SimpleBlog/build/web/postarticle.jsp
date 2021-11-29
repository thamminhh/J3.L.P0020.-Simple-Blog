<%-- 
    Document   : postarticle
    Created on : Oct 5, 2021, 4:18:07 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Page</title>
    </head>
    <body>
        <h1>Post New Article</h1>
        
        <c:if test="${sessionScope.USERID != null}">
            <font>
            Welcome, ${sessionScope.FULLNAME}
            </font>
        </c:if>

        <c:if test="${sessionScope.USERID == null}">
            <a href="loginPage">Click here to Login </a>
        </c:if> 
        <c:if test="${sessionScope.USERID != null}">
            <form action="logOutAction">
                <input type="submit" value="Log Out" name="btAction" />
            </form>
        </c:if> 
        
        <form action="postArticleAction"> 
            Title : <input type="text" name="txtTitle" value="${param.txtTitle}" /><br/>
            <c:set var="errors" value="${requestScope.CREATEERR}" />
            <c:if test="${not empty errors.titleLengErr}">
                <font color="red"> ${errors.titleLengErr} </font><br/>
            </c:if>
            Short Description : <input type="text" name="txtShortDescription" value="${param.txtShortDescription}" /><br/>
            <c:set var="errors" value="${requestScope.CREATEERR}" />
            <c:if test="${not empty errors.shortDescriptionLengErr}">
                <font color="red"> ${errors.shortDescriptionLengErr} </font><br/>
            </c:if>
            Content: <input type="text" name="txtContent" value="${param.txtContent}" /><br/>
            <c:set var="errors" value="${requestScope.CREATEERR}" />
            <c:if test="${not empty errors.contentLengErr}">
                <font color="red"> ${errors.contentLengErr} </font><br/>
            </c:if>
                
            <input type="submit" value="POST" name="btAction"/><br/>
            <input type="submit" value="Reset" /><br/>
        </form>
        
    </body>
</html>
