<%--
    Document   : applicationReview
    Created on : May 13, 2013, 8:38:04 PM
    Author     : andyaltwasser
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h2>Applications</h2>
<ul>
    <c:forEach items="${apps}" var="app">
        <li>${app.firstName}  ${app.lastName} - ${app.phoneNumber} - ${app.email}
            <a href="<c:url value='/jobScreen.htm?id=${app.jobId}'/>">Job</a></li>
    </c:forEach>
</ul>
