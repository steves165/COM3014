<%--
    Document   : applyScreen
    Created on : 08-May-2013, 13:39:09
    Author     : Andy
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" enctype="multipart/form-data" action="initFormApp.htm" id="form">
    <form:errors path="*" cssClass="error" element="div" />
    <form:hidden path="jobId" value="${job.id}"/>
    <fieldset class="main">
        <legend> Application Input </legend>
        <p>
            <label>Job Title</label><span>${job.title}</span>
        </p>
        <p>
            <label>Company</label><span>${job.company.companyName}</span>
        </p>
        <p>
            <label> Department </label><span>${job.department}</span>
        </p>
        <p>
            <form:label path="firstName"> First Name </form:label><br/>
            <form:input path="firstName" id="firstName" class="required text"/>
        </p>
        <p>
            <form:label path="lastName"> Last Name </form:label><br/>
            <form:input path="lastName" id="lastName" class="required text"/>
        </p>
        <p>
            <form:label path="phoneNumber"> Phone Number </form:label><br/>
            <form:input path="phoneNumber" id="phoneNumber" class="required text"/>
        </p>
        <p>
            <form:label path="email"> Email </form:label><br/>
            <form:input path="email" id="email" class="required text"/>
        </p>


        <input type="submit" id="submit" value="Submit" />
    </fieldset>
</form:form>
