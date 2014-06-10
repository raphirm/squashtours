<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" /> 
	<div class="container">

		<div class="row">
			<div class="span12">
				<fieldset>
					<legend>This page is protected, only users can access it.</legend>
				</fieldset>
				<p>Logged in username: ${username} 
			</div>
		</div>

		<footer>
			<p>Built with Maven profile ${buildEnv}</p>
		</footer>
	</div> <!-- /container -->
<jsp:include page="../include/footer.jsp" /> 
