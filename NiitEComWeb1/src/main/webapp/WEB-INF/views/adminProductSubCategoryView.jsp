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
      <li><a href="#">Order Management</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${context}/productCategoryView"><span class="glyphicon glyphicon-home"></span> Back To Front End</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <h3>Product Management</h3>
  <c:if test="${successMessage!=null}">
		<div class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  <strong>Success!</strong> ${successMessage}
</div>
		</c:if>
		<c:if test="${errorMessage!=null}">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<div class="alert alert-warning">
  <strong>Warning!</strong> ${errorMessage}.
</div>
</c:if>
<div class="container">
  <h4>Category List</h4>
  <p>List of ProductCategories:</p>            
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
    <c:forEach items="${productSubCategories}" var="productSubCategory">
      <tr>
        <td>${productSubCategory.productSubCategoryId}</td> 
        <td>${productSubCategory.productSubCategoryName}</td>
        <td>
        <c:if test="${productSubCategory.productSubCategoryStatus}">
        <a href="${context}/deactivateProductSubCategory/${productSubCategory.productSubCategoryId}" class="btn btn-danger"> Deactivate</a>
        </c:if>
        <c:if test="${! productSubCategory.productSubCategoryStatus}">
        <a href="${context}/activateProductSubCategory/${productSubCategory.productSubCategoryId}" class="btn btn-success"> Activate</a>
        </c:if>
        <a href="${context}/deleteProductSubCategory/${productSubCategory.productSubCategoryId}" class="btn btn-danger"> Delete</a> 
        <a href="${context}/editProductSubCategory/${productSubCategory.productSubCategoryId}" class="btn btn-success"> Edit</a> <a href="${context}/viewProductCategory/${productSubCategory.productSubCategoryId}" class="btn btn-info"> View</a> </td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>
		<h4> Add New ProductCategory</h4>
		
		
  <form:form method="post" action="${context}/addProductSubCategory">
    <div class="form-group">
      <label for="Name">Name</label>
      <form:input class="form-control" path="productSubCategoryName" type="text"/>
      <label for="Details">Details</label>
  		<form:textarea class="form-control" rows="5" path="productSubCategoryDetails"/>
        </br>
        
         <input type="submit" class="btn btn-success">
    </div>
    </form:form>
		
</div>

</body>
</html>
