<%--
    Document   : Company
    Created on : 27-Mar-2013, 14:44:51
    Author     : Steves165
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" enctype="multipart/form-data" action="initFormComp.htm" id="form">
    <form:errors path="*" cssClass="error" element="div" />
    <fieldset class="main">
        <legend> Company Input </legend>
        <p>
            <form:label path="companyName"> Company Name </form:label><br/>
            <form:input path="companyName" id="companyName" class="required text"/>
        </p>
        <p>
            <form:label path="industry"> Industry </form:label><br/>
            <form:input path="industry" id="industry" class="required text"/>
        </p>
        <p>
            <form:label path="scale"> Scale </form:label><br/>
            <form:input path="scale" id="scale" class="required text"/>
        </p>
        <p>
            <form:label path="logo"> Logo </form:label><br/>
            <form:input type="file" name="file" path="logo" id="scale"/>
        </p>
        <p>
            <form:label path="website"> Website Address </form:label><br/>
            <form:input path="website" id="website" class="required text"/>
        </p>
        <p>
            <form:label path="contactEmail"> Contact Email </form:label><br/>
            <form:input path="contactEmail" id="contactEmail" class="required text"/>
        </p>
        <p>
            <form:label path="contactTel"> Contact Telephone </form:label><br/>
            <form:input path="contactTel" id="contactTel" class="required text"/>
        </p>
        <p>
            <form:label path="contactAddress"> Contact Address </form:label><br/>
            <form:input path="contactAddress" id="contactAddress" class="required text"/>
        </p>
        <fieldset id="locations">
            <legend> Locations </legend>
            <ul>
                <c:if test="${!command.locations.isEmpty()}">
                    <c:forEach items="${command.locations}" var="required" varStatus="status">
                        <li><form:input class="text" path="locations[${status.index}]"/></li>
                    </c:forEach>
                </c:if>
            </ul>
        </fieldset>
        <input type="submit" id="submit" value="Submit" />
    </fieldset>
</form:form>
