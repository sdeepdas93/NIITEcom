<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="header.jsp"></jsp:include>
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
								<li><a href="${pageContext.request.contextPath}/userProductCategoryView">Home</a></li>
								</security:authorize>
								
								<security:authorize access="isAnonymous()">
								<li><a href="${pageContext.request.contextPath}/productCategoryView">Home</a></li>
								</security:authorize>
								<li class="dropdown"><a href="#" class="active">Categories<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                    <c:set var="context" value="${pageContext.request.contextPath}" />
                                    <c:forEach items="${productCategories}" var="productCategory">
                                        <li><a href="${context}/productSubCategoryView/${productCategory.productCategoryId}" >${productCategory.productCategoryName}</a></li>
								<!--  		<li><a href="product-details.html" class="active">Product Details</a></li> -->
										
                                        
                                      </c:forEach>
                                    </ul>
                                </li> 
							<!--	<li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                         <li><a href="blog.html">Blog List</a></li>
										<li><a href="blog-single.html">Blog Single</a></li>
                                    </ul>
                                </li> 
								<li><a href="404.html">404</a></li>
								<li><a href="contact-us.html">Contact</a></li> -->
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
				</div>
			</div>
	</header>
	
	<c:if test="${cartItems.isEmpty()}">
	<div class="container">
	<div class="alert alert-success alert-dismissable">
  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  <strong>No items present .</strong> 
  </div>
</div>
	</c:if>
	
	
	<section id="cart_items">
	<%-- <c:if test="${!cartItems.isEmpty()}"> --%>
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="userProductCategoryView">Home</a></li>
				  <li><a href="cartView">Shopping Cart</a></li>
				  <li class="active">Order Details</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${cartItems}" var="cartItem">
						<tr>
							<td class="cart_product">
								<a href=""><img src="${cartItem.product.productImage}" height="110" width="110" alt="${cartItem.product.productImage}"></a>
							</td>
							<td class="cart_description">
								<h4><a href="">${cartItem.product.productName}</a></h4>
								<p>${cartItem.product.productId}</p>
							</td>
							<td class="cart_price">
								<p>&#x20B9; ${cartItem.product.productPrice}</p>
							</td>
							<td class="cart_price"><p>${cartItem.cartItemQuantity}</p></td>
							<td class="cart_total">
								<p class="cart_total_price">&#x20B9; ${cartItem.cartItemSubtotal}</p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="${context}/deleteCartItem/${cartItem.cartItemId}"><i class="fa fa-times"></i></a>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
		<%-- </c:if> --%>
	</section> <!--/#cart_items-->
	<jsp:include page="footer.jsp"></jsp:include>
	