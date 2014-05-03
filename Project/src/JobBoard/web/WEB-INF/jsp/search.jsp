<%--
    Document   : jobList
    Created on : 16-Apr-2013, 15:59:01
    Author     : Andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>
    $(function() {
        $("#searchterm,#searchsalary").search({
            url: '<c:url value="/api/job/search/"/>',
            data: function() {
                return {term: $("#searchterm").val(),
                        salary: $("#searchsalary").val()};
            },
            map: $("#job-map-canvas").jobMap({zoom: 10}),
            map_template: "#googlemaptemplate",
            list_element: "#dynamic-jobs",
            list_template: "#jobTemplate"
        });
        $("#searchsalary").autocomplete({
            source: function( request, response ) {
            $.ajax({
                url: '<c:url value="/api/autocomplete/salary/"/>' + request.term,
                dataType: "json",
                success: function( data ) {
                  response( $.map( data.iterable, function( item ) {
                    return {
                      label: item.salary,
                      value: item.salary
                    };
                  }));
                }
              });
            },
            minLength: 2
        });
    });

function replace_newlines(str) {
    return str.replace(/\n/g, '<br/>');
}
</script>
<fieldset>
    <legend>Search</legend>
    <label for="searchterm">Search Term:</label><input name="searchterm" id="searchterm" class="text"/><br/>
    <label for="searchsalary">Minimum Salary:</label><input name="searchsalary" id="searchsalary" class="text"/>
</fieldset>
<h3>Job Search</h3>
<!-- Template for the popups on the Google map -->
<script id="googlemaptemplate" type="text/x-jquery-tmpl">
    <div>
        <b><a href="<c:url value='/jobScreen.htm?id=\${id}'/>">\${title}</a></b> at \${company.companyName}<br/>
        <ul>
            <li>£\${salary}</li>
            {{if requiredSkills.length > 0}}
                <li>Required Skills: \${requiredSkills}</li>
            {{/if}}
            {{if desiredSkills.length > 0}}
                <li>Desired Skills: \${desiredSkills}</li>
            {{/if}}
            {{if contract != null}}
                <li>\${contract}</li>
            {{/if}}
        </ul>
    </div>
</script>
<!-- Define a template to load the jobs into -->
<script id="jobTemplate" type="text/x-jquery-tmpl">
    <li><div class="jobtitle"><a href="<c:url value='/jobScreen.htm?id=\${id}'/>">\${title}</a> - £\${salary} - \${location}</div>
        <div class="companyinfo">\${department} department within \${company.companyName}</div>
    </li>
</script>
<div class="span-10">
    <ol id="dynamic-jobs"></ol>
</div>

<div class="span-8">
    <div id="job-map-canvas"></div>
</div>