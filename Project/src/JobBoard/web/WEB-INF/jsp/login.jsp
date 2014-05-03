<%--
    Document   : login
    Created on : Apr 16, 2013, 10:56:41 AM
    Author     : andyaltwasser
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="span-19">
		<div class="top_block block_1">
			<div class="content">
				<div class="background block_4">
				</div>
				<div class="left_block block_4">
					<div class="content">
                                              <div class="img"><img src=<c:url value='/static/images/img1.jpg'/> alt="" height="101"
   width="157"></div>
					</div>
				</div>
				<div class="background block_5">
				</div>
				<div class="right_block block_5">
					<div class="content">
                                               <h1 class="title">Welcome to the <span>Job Application Board</span></h1>
                                               <p> Please login to view and apply for jobs. </p>

                                               <form action="<c:url value='/j_spring_security_check'/>" method="post">
<fieldset>
    <legend>Login</legend>
    <label for="j_username">Username</label>:
    <input id="j_username" name="j_username" size="20" maxlength="50" type="text" class="text"/>
    <br />
    <label for="j_password">Password</label>:
    <input id="j_password" name="j_password" size="20" maxlength="50" type="password" class="text"/> <br />
    <input type="submit" value="Login"/>
</fieldset>
</form>
					</div>
				</div>
			</div>
		</div>
	</div>
