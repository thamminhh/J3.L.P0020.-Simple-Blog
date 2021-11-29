<%-- 
    Document   : adminpage
    Created on : Oct 3, 2021, 8:31:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Admin Page</h1>

        <font>
        Welcome, ${sessionScope.FULLNAME}
        </font>

        <form action="logOutAction">
            <input type="submit" value="Log Out" name="btAction" />
        </form>

        <form action="searchAction">                          
            Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/><br/>

            Status Option <select name="statusOption" value =${param.statusOption}>
                <option>--Chose--Option--</option>
                <option>New</option>
                <option>Active</option>
                <option>Delete</option>
            </select> </br>

            <input type="submit" value="Search" name="btAction"/>
        </form>  </br>

        <c:set var ="option" value="${param.statusOption}"/>

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
                   

                    <c:url var="urlRewritingDelete" value="deleteArticleAction">
                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/> 
                        <c:param name="option" value="${param.statusOption}"/> 
                        <c:param name="txtArticleID" value="${dto.articleID}"/> 
                    </c:url>

                    <a href="${urlRewritingDelete}"> 
                        <h2> <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="return confirm('Do you want to Delete this Article?')" name="btAction" value="Delete">
                                Delete
                            </button></h2> 
                    </a>
            
                <c:if test="${option == 'New'}">
                    <c:url var="urlRewritingActive" value="activeArticleAction">
                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/> 
                        <c:param name="option" value="${param.statusOption}"/> 
                        <c:param name="txtArticleID" value="${dto.articleID}"/> 
                    </c:url>

                    <a href="${urlRewritingActive}"> 
                        <h2> <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="return confirm('Do you want to Delete this Article?')" name="btAction" value="Delete">
                                Active
                            </button></h2> 
                    </a>
                </c:if>

            </c:forEach>
        </c:if> 
        <c:if test="${result == null}">
            No record found
        </c:if>




    </body>
</html>
