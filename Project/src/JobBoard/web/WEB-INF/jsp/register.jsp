<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" action="initRegisterForm.htm" id="form">
    <form:errors path="*" cssClass="error" element="div" />
    <fieldset class="main">
        <legend> Register </legend>
        <p>
            <form:label path="username"> Username </form:label><br/>
            <form:input path="username" id="username" class="required text"/>
        </p>
        <p>
            <form:label path="password"> Password </form:label><br/>
            <form:input path="password" id="password" type="password" class="required text"/>
        </p>
        <input type="submit" id="submit" value="Register" />
    </fieldset>
</form:form>
