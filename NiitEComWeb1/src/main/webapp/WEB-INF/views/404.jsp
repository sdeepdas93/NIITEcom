<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | NiitEcom</title>
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="${context}/resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="${context}/resources/css/price-range.css" rel="stylesheet">
    <link href="${context}/resources/css/animate.css" rel="stylesheet">
	<link href="${context}/resources/css/main.css" rel="stylesheet">
	<link href="${context}/resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${context}/resources/js/html5shiv.js"></script>
    <script src="${context}/resources/js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="${context}/resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${context}/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${context}/resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${context}/resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${context}/resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<div class="container text-center">
		<div class="logo-404">
			<a href="index.html"><img src="${context}/resources/images/home/logo.png" alt="" /></a>
		</div>
		<div class="content-404">
			<img src="${context}/resources/images/404/404.png" class="img-responsive" alt="" />
			<h1><b>OPPS!</b> We Couldn’t Find this Page</h1>
			<p>Uh... So it looks like you brock something. The page you are looking for has up and Vanished.</p>
			<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
			<h2><a href="${pageContext.request.contextPath}/userProductCategoryView">Bring me back Home</a></h2>
			</security:authorize>
			<security:authorize access="isAnonymous()">
			<h2><a href="${pageContext.request.contextPath}/productCategoryView">Bring me back Home</a></h2>
			</security:authorize>
			
		</div>
	</div>

  
    <script src="${context}/resources/js/jquery.js"></script>
	<script src="${context}/resources/js/price-range.js"></script>
    <script src="${context}/resources/js/jquery.scrollUp.min.js"></script>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
    <script src="${context}/resources/js/jquery.prettyPhoto.js"></script>
    <script src="${context}/resources/js/main.js"></script>
</body>
</html>