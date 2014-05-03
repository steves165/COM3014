<%--
    Document   : compView
    Created on : 24-Apr-2013, 13:28:56
    Author     : Steves165
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fieldset>
    <legend>Company search</legend>
    <label for="company">Company Name:</label><input name="company" id="company" class="text"/>
</fieldset>
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
                      entity: item
                    };
                  }));
                }
              });
            },
            minLength: 2,
            select: function( event, ui ) {
                $("#companyDetails").empty();
                $("#detailsTmpl").tmpl(ui.item.entity).appendTo("#companyDetails");
            }
        });
     });
</script>
<script id="detailsTmpl" type="text/x-jquery-tmpl">
    <h2><a href="<c:url value='/compScreen.htm?id=\${id}'/>">\${companyName}</a></h2>
    {{if hasImage}}
    <img src="<c:url value="/companyImage.htm?id=\${id}"/>"/>
    {{/if}}
    <ul>
    <li><b>Address:</b> {{html contactAddress.replace(/\n/g, "<br/>")}}</li>
    <li><b>Website:</b> \${website}</li>
    <li><b>Email:</b> \${contactEmail}</li>
    <li><b>Tel:</b> \${contactTel}</li>
    </ul>
</script>
<div id="companyDetails">
</div>