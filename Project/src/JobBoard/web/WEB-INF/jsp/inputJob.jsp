<%--
    Document   : inputJob
    Created on : 05-Mar-2013, 15:37:13
    Author     : Steves165
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $(function() {
        $("#company").autocomplete({
            source: function( request, response ) {
            $.ajax({
                url: '<c:url value="/api/autocomplete/company/"/>' + request.term,
                dataType: "json",
                success: function( data ) {
                  response( $.map( data.iterable, function( item ) {
                    return {
                      label: item.companyName,
                      value: item.companyName,
                      id: item.id
                    };
                  }));
                }
              });
            },
            minLength: 2,
            select: function( event, ui ) {
                $("#companyId").val(ui.item.id);
            }
        });

        $("#loc").autocomplete({
            source: function( request, response ) {
            $.ajax({
                url: '<c:url value="/api/autocomplete/location/"/>' + request.term,
                dataType: "json",
                success: function( data ) {
                  response( $.map( data.iterable, function( item ) {
                    return {
                      label: item.location,
                      value: item.location
                    };
                  }));
                }
              });
            },
            minLength: 5
        });
     });
</script>
<form:form method="post" action="initForm.htm" id="form">
    <form:errors path="*" cssClass="error" element="div" />
            <fieldset class="main">
                <legend> Job Specifications </legend>
                <p>
                    <form:label path="title">Title</form:label><br/>
                    <form:input path="title" id="title" class="title required"/>
                </p>
                <p>
                    <form:label path="companyId">Company</form:label><br/>
                    <form:hidden path="companyId" id="companyId"/>
                    <input id="company" class="text"/>
                </p>
                <p>
                    <form:label path="department">Department</form:label><br/>
                    <form:input path="department" id="dept" class="text"/>
                </p>
                <p>
                    <form:label path="description">Description</form:label><br/>
                    <form:textarea path="description" id="desc" class="required"/>
                </p>
                <p>
                    <form:label path="salary">Salary</form:label><br/>
                    <form:input path="salary" id="salary" class="text required"/>
                </p>
                <p>
                    <form:label path="location">Location: </form:label><br/>
                    <form:input path="location" id="loc" class="text required"/>
                </p>
                <p>
                    <form:label path="contract">Contract Type</form:label><br/>
                    <form:select path="contract">
                        <form:option value="-" label="--Please Select"/>
                        <form:option value="Full-Time"/>
                        <form:option value="Part-Time"/>
                    </form:select>
                </p>
                <fieldset id="requiredSkills">
                    <legend> Required Skills </legend>
                    <ul>
                        <c:if test="${!command.requiredSkills.isEmpty()}">
                            <c:forEach items="${command.requiredSkills}" var="required" varStatus="status">
                                <li><form:input class="text" path="requiredSkills[${status.index}]"/></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </fieldset>
                <fieldset id="desiredSkills">
                    <legend> Desired Skills </legend>
                    <ul>
                        <c:if test="${!command.desiredSkills.isEmpty()}">
                            <c:forEach items="${command.desiredSkills}" var="desired" varStatus="status">
                                <li><form:input class="text" path="desiredSkills[${status.index}]"/></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </fieldset>
                <input type="submit" id="submit" value="Submit" />
          </fieldset>
        </form:form>
