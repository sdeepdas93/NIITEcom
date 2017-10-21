<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Niit Ecom Admin1</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
 <c:set var="context" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Niit Ecom Admin</a>
    </div>
    <ul class="nav navbar-nav">
    	<li class="dropdown active"><a class="dropdown-toggle" data-toggle="dropdown" href="${context}/adminProductCategoryView">Product Management <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <li><a href="${context}/adminProductCategoryView">ProductCategoryView</a></li>
          <li><a href="${context}/adminProductView">ProductView</a></li>
          <li><a href="#">Order Management</a></li> </ul>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User Management <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <!-- <li><a href="#">Product Management</a></li>
          <li><a href="#">User Management</a></li>
          <li><a href="#">Order Management</a></li>-->
        </ul>
      </li>
      <li><a href="${context}/productCategoryView">Order Management</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${context}/userProductCategoryView"><span class="glyphicon glyphicon-home"></span> Back To Front End</a></li>
      <li><a href="${context}/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>