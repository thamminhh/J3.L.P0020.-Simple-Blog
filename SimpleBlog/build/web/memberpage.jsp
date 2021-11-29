<%-- 
    Document   : memberpage
    Created on : Oct 3, 2021, 8:28:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Member page</h1>
        
         <font>
        Welcome, ${sessionScope.FULLNAME}
        </font>
        
        <form action="logOutAction">
            <input type="submit" value="Log Out" name="btAction" />
        </form>
        
        <form action="searchAction">                          
            Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form>  </br>
        
        <a href="postArticlePage">Click here to post a new article</a>

        <c:set var ="result" value="${requestScope.RESULT}"/>

        <c:if test="${result != null}">

             <c:forEach var="dto" items="${result}" varStatus="counter">
                <input type="hidden" name="txtArticleID" value="${dto.articleID}" />
                
                <c:url var="urlRewriting" value="viewDetailAction">
                    <c:param name="txtArticleID" value="${dto.articleID}"/> 
                </c:url>
              
                <a href="${urlRewriting}"> <h2> ${dto.title} </h2> </a>
                    
                    Short Description : </br>
                    ${dto.shortDescription} </br>
                    Writen by : ${dto.author} </br>
                    Date : ${dto.date} </br>
                
            </c:forEach>
        </c:if> 
        <c:if test="${result == null}">
            No record found
        </c:if>

        
    </body>
</html>
