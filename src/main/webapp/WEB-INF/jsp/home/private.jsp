
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jsp:include page="../include/header.jsp" /> 

	<div class="container">
	<c:if test="<%= request.getParameter(\"authfail\") != null %>">
			<div class="row">
				<div class="span12">
				<div class="alert alert-error">
						Login failed
					</div>
				</div>
			</div>
			</c:if>
		<c:if test="<%= request.getParameter(\"accessdenied\") != null %>"> 
		<div class="row">
				<div class="span12">
					<div class="alert alert-error">
						Access denied
					</div></div></div>
		</c:if> 
		<c:if test="<%= request.getParameter(\"login\") != null %>"> 
			<div class="row">
				<div class="span12">
					<div class="alert alert-warning">
						You are not signed in, please sign in first.
					</div>
				</div>
			</div>
		</c:if> 
		<c:if test="<%= request.getParameter(\"loggedout\") != null %>"> 
			<div class="row">
				<div class="span12">
					<div class="alert alert-success">
						You have been logged out
					</div>
				</div>
			</div>
		</c:if> 
			
		<div class="row">
			<div class="span12">
				<fieldset>
					<legend>You are allowed to see your own user: ${user.username}</legend>
				</fieldset>
			</div>
		</div>

		<footer>
			<p>Organize your own squash league, challenge your friends and find squash partners</p>
		</footer>
	</div>
<jsp:include page="../include/footer.jsp" /> 
