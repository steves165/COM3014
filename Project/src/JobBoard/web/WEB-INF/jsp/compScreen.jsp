<%--
    Document   : compScreen
    Created on : 24-Apr-2013, 13:34:20
    Author     : Steves165
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="span-19">
    <h2>${comp.companyName}</h2>
    <address>${comp.contactAddress}</address>
    <div><b><a href="${comp.website}">${comp.website}</a> - ${comp.contactEmail} -  ${comp.contactTel}</b></div>

    <c:choose>
        <c:when test="${comp.getHasImage()}">
            <img src="<c:url value="/companyImage.htm?id=${comp.id}"/>"/>
        </c:when>
    </c:choose>
    <div id="companylocations">
        <h3> Locations: </h3>
        <ul>
            <c:forEach items="${comp.locations}" var="comps">
                <li><c:out value="${comps}" /></li>
            </c:forEach>
        </ul>
    </div>
</div>

