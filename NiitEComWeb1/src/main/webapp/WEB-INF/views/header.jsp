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
    <title>NiitEcom</title>
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
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6 ">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href=""><i class="fa fa-phone"></i> +91 7797336889</a></li>
								<li><a href=""><i class="fa fa-envelope"></i> sdeepdas93@gmail.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href=""><i class="fa fa-facebook"></i></a></li>
								<li><a href=""><i class="fa fa-twitter"></i></a></li>
								<li><a href=""><i class="fa fa-linkedin"></i></a></li>
								<li><a href=""><i class="fa fa-dribbble"></i></a></li>
								<li><a href=""><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
							<a href="${pageContext.request.contextPath}/userProductCategoryView"><img src="${context}/resources/images/home/logo.png" alt="" /></a>
							</security:authorize>
							<security:authorize access="isAnonymous()">
							<a href="${pageContext.request.contextPath}/productCategoryView"><img src="${context}/resources/images/home/logo.png" alt="" /></a>
							</security:authorize>
							
						</div>
						<!-- <div class="btn-group pull-right">
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									USA
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="">Canada</a></li>
									<li><a href="">UK</a></li>
								</ul>
							</div>
							
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									DOLLAR
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="">Canadian Dollar</a></li>
									<li><a href="">Pound</a></li>
								</ul>
							</div>
						</div> -->
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<c:if test="${pageContext.request.userPrincipal.name!=null}">
								<li><a href=""><i class="fa fa-user" aria-hidden="true"></i>Hello ${user.userName}</li>
								</c:if>
								<security:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="${context}/adminProductCategoryView"><i class="fa fa-cogs"></i> Admin Control</a></li>
								</security:authorize>
								<!-- <li><a href=""><i class="fa fa-user"></i> Account</a></li>
								<li><a href=""><i class="fa fa-star"></i> Wishlist</a></li> -->
								<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
								<li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								</security:authorize>
								<li><a href="${context}/cartView"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								
								<security:authorize access="isAnonymous()">
								<li><a href="${context}/userRegistrationView"><i class="fa fa-user" aria-hidden="true"></i> SignUp</a></li>
								<li><a href="${context}/loginPage"><i class="fa fa-sign-in"></i> Login</a></li>
								</security:authorize>
								<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
								<li><a href="${context}/logout"><i class="fa fa-sign-out"></i> Logout</a></li>
								</security:authorize>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
