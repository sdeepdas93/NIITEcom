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

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Niit Ecom Admin</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Product Management</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User Management <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <!-- <li><a href="#">Product Management</a></li>
          <li><a href="#">User Management</a></li>
          <li><a href="#">Order Management</a></li>-->
        </ul>
      </li>
      <li><a href="#">Order Management</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-home"></span> Back To Front End</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <h3>Product Management</h3>
<div class="container">
  <h4>Category List</h4>
  <p>The .table-hover class enables a hover state on table rows:</p>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th class="col-sm-2">Id</th>
        <th class="col-sm-4">Name</th>
        <th class="col-sm-6"> </th>
      </tr>
    </thead>
    <tbody>
    <c:set var="context" value="${pageContext.request.contextPath}"></c:set>
    <c:forEach items="${productCategories}" var="productCategory">
      <tr>
        <td>${productCategory.productCategoryId}</td> 
        <td>${productCategory.productCategoryName}</td>
        <td><a href="${context}/deleteProductCategory/${productCategory.productCategoryId}" class="btn btn-danger"> Delete</a> <a href="${context}/editProductCategory/${productCategory.productCategoryId}" class="btn btn-success"> Edit</a> <a href="${context}/viewProductCategory/${productCategory.productCategoryId}" class="btn btn-info"> View</a> </td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>
		<h4> Add New ProductCategory</h4>
  <form:form method="post" action="addProductCategory">
    <div class="form-group">
      <label for="Name">Name</label>
      <form:input class="form-control" path="productCategoryName" type="text"/>
      <label for="Details">Details</label>
  		<form:textarea class="form-control" rows="5" path="productCategoryDetails"/>
        </br>
         <button type="submit" class="btn btn-success">Submit</button>
    </div>
    </form:form>
		
</div>

</body>
</html>
