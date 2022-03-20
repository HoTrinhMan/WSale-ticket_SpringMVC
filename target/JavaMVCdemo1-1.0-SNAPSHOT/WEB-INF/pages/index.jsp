<%-- 
    Document   : index.jsp
    Created on : Mar 16, 2022, 4:11:21 PM
    Author     : Manax
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
        <link href="<c:url value="/css/style.css"/> " rel="stylesheet">
    </head>
    <body>
        <h1>
            <spring:message code="page.title"/>
        </h1>
        <h3>
            <spring:message code="page.welcome" />
        </h3>
        
            <img src="<c:url value="/img/h1.png"/>" alt="">
        <h1>Hello ${name}</h1>
       
        <ul>
            <c:forEach items="${categories}" var="cat">
                <li>${cat}</li>
            </c:forEach>
        </ul>
        
        <c:url value="/upload" var="action"/>
        <form:form method="post"
              enctype="multipart/form-data"
              action="${action}"
              modelAttribute="user">
            <form:input type="file" path="avatar" />
            <input type="submit" value="Uploat"/>

        </form:form>
    </body>
</html>
