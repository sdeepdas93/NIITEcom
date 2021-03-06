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
  <h4>${productSubCategory.productSubCategoryName}</h4>
  <p>List of Products:</p>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th class="col-sm-1">Id</th>
        <th class="col-sm-2">Name</th>
        <th class="col-sm-1">Price</th>
        <th class="col-sm-4">Info</th>
        <th class="col-sm-4"> </th>
      </tr>
    </thead>
    <tbody>
    <c:set var="context" value="${pageContext.request.contextPath}"></c:set>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>${product.productId}</td> 
        <td>${product.productName}</td>
        <td>${product.productPrice}</td>
        <td>${product.productInfo}</td>
        <td>
        <c:if test="${product.productStatus}">
        <a href="${context}/deactivateProduct/${product.productId}" class="btn btn-danger"> Deactivate</a>
        </c:if>
        <c:if test="${! product.productStatus}">
        <a href="${context}/activateProduct/${product.productId}" class="btn btn-success"> Activate</a>
        </c:if>
        <a href="${context}/deleteProduct/${product.productId}" class="btn btn-danger"> Delete</a> 
        <a href="${context}/adminProductUpdateView/${product.productId}" class="btn btn-success"> Edit</a> 
        <a href="${context}/viewProduct/${product.productId}" class="btn btn-info"> View</a> </td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>
		<h4> Add New ProductCategory</h4>
		
		
  <form:form method="post" action="${context}/addProduct" enctype="multipart/form-data">
    <div class="form-group">
      <label for="Name">Name</label>
      <form:input class="form-control" path="productName" type="text"/>
      <label for="Name">Price</label>
      <form:input class="form-control" path="productPrice" type="text"/>
      <label for="Details">Info</label>
  		<form:textarea class="form-control" rows="5" path="productInfo"/>
        <br>
        <form:select class="form-control" path="productBrand.productBrandId">
        <c:forEach items="${productBrands}" var="productBrand">
        <form:option value="${productBrand.productBrandId}">
        ${productBrand.productBrandName}
        </form:option>
        </c:forEach>
        </form:select>
        <br>
       <form:hidden path="productImage"  /> <input type="file" class="form-control" name="productImageFile"/>
		
		
        
         <input type="submit" class="btn btn-success">
    </div>
    </form:form>
		
</div>

</body>
</html>
