<%-- 
    Document   : viewdetail
    Created on : Oct 4, 2021, 10:26:45 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Detail Page</title>
    </head>
    <body>
        <h1>Detail Page</h1>

            <c:if test="${sessionScope.USER != null}">
                <font>
                Welcome, ${sessionScope.FULLNAME}
                </font>
            </c:if>

            <c:if test="${sessionScope.USER == null}">
                <a href="loginPage">Click here to Login </a>
            </c:if> 
            <c:if test="${sessionScope.USER != null}">
                <form action="logOutAction">
                    <input type="submit" value="Log Out" name="btAction" />
                </form>
            </c:if> 

        <c:set var ="dto" value="${requestScope.ARTICLE}"/>
        <c:set var ="cmt" value="${requestScope.COMMENT}"/>

        <c:if test="${dto != null}">

            <h1> ${dto.title} </h1> 
            Short Description </br>
            ${dto.shortDescription} </br>
            Content </br>
            ${dto.contents} </br>
            Writen by : ${dto.author} </br>
            Date: ${dto.date} </br>

            Comment: </br>

            <form action="postCommentAction" method="POST">
                <input type="text" name="txtCmt" value="" />
                <input type="hidden" name="txtArticleID" value="${dto.articleID}" />
                <input type="submit" value="Post" name="btAction" />
            </form>

            <c:forEach var="cmt" items="${cmt}" >
                <c:if test="${cmt != null}">
                    ${cmt.email} : ${cmt.detail} </br>
                </c:if>
            </c:forEach>

        </c:if> 
        <c:if test="${dto == null}">
            No record found !!!
        </c:if>

    </body>
</html>
