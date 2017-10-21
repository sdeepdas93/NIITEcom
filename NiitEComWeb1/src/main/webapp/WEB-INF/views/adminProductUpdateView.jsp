<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="adminHeader.jsp"></jsp:include>
  <c:set var="context" value="${pageContext.request.contextPath}" scope="session"></c:set>

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

		<h4> Update Product</h4>
		
		
  <form:form method="post" action="${context}/updateProduct" enctype="multipart/form-data">
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
		<form:hidden path="productId"/>
		<form:hidden path="productSubCategory.productSubCategoryId"/>
        
         <input type="submit" value="update"class="btn btn-success">
    </div>
    </form:form>
		
</div>
<!--  context=  ${context}-->
</body>
</html>
