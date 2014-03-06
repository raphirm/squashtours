
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
        <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <link rel="stylesheet" href="<c:url value="/css/bootstrap-responsive.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

        <script src="<c:url value="/js/vendor/jquery-1.8.0.min.js"/>"></script>
        <script src="<c:url value="/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"/>"></script>
        <script src="<c:url value="/js/vendor/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/js/main.js"/>"></script>
        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="<c:url value="/index.html"/>">My Project</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                          <c:set var="page" value="/protected.html" />
                          <c:set var="actual" value="" /> 
                         
                          <li <c:if test="<%= request.getServletPath().equals("/WEB-INF/views/home/protected.jsp") %>">class="active"</c:if>><a href="<c:url value="/protected.html"/>">Protected</a></li>
                          <li <c:if test="<%= request.getServletPath().equals("/WEB-INF/views/validation/address.jsp") %>">class="active"</c:if>><a href="<c:url value="/validation/address.html"/>">Form validation</a></li>
                      		
                        </ul>
                        <sec:authorize access="isAuthenticated()">
	                        <form action=<c:url value="/j_spring_security_logout"/> class="navbar-form pull-right" method="POST">
	                            <button type="submit" class="btn">Sign out</button>
	                        </form>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                        
	                        <form action="<c:url value="/j_spring_security_check"/>" class="navbar-form pull-right" method="POST">
	                            <input class="span2" type="text" name="j_username" placeholder="Username">
	                            <input class="span2" type="password" name="j_password" placeholder="Password">
	                            <button type="submit" class="btn">Sign in</button>
	                        </form>
	                     </sec:authorize>
	                        
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>