<%--
    Document   : jobScreen
    Created on : 27-Mar-2013, 10:19:29
    Author     : Andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newline", "\n"); %>
<div class="span-19">
            <h2>${job.title} - £${job.salary} - <a href="<c:url value='/applyScreen.htm?id=${job.id}'/>">Apply</a></h2>
            <p>
                ${fn:replace(job.description, newline, "<br/>")}
            </p>
            <h3>Company Information</h3>
            <p class="companyinfo">${job.department} department within <a href="<c:url value="/compScreen.htm?id=${job.companyId}"/>">${job.company.companyName}</a> at ${job.location}</p>
            <h3> Required Skills </h3>
            <ul>
                <c:forEach items="${job.requiredSkills}" var="reqSkills">
                    <li><c:out value="${reqSkills}" /></li>
                </c:forEach>
            </ul>

            <h3> Desired Skills </h3>
            <ul>
                <c:forEach items="${job.desiredSkills}" var="skills">
                    <li><c:out value="${skills}" /></li>
                </c:forEach>
            </ul>
</div>



