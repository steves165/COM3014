<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="head">
			<div id ="headContent">
				<h1 id="homeLink"> Job Application Board</h1>
	
				<div class="clear"> 
					<ul>
						<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<li><a href="${logoutUrl}">Log Out</a></li>
						
						
						
					</ul>
				</div>
				</div>
			</div>

