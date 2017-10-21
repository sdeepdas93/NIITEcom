<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="adminHeader.jsp"></jsp:include>
  
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
  <h4>Product Subcategory</h4>
  <p>List of ProductSubCategories:</p>            
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
        <td><a href="${context}/adminProductView/${productSubCategory.productSubCategoryId}">${productSubCategory.productSubCategoryName} </a> </td>
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
