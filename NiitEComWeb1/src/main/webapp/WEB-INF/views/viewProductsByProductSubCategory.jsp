<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
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
								<li><a href="index.html">Home</a></li>
								<li class="dropdown"><a href="#" class="active">Categories<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                    <c:set var="context" value="${pageContext.request.contextPath}" />
                                    <c:forEach items="${productCategories}" var="productCategory">
                                        <li><a href="${context}/productSubCategoryView/${productCategory.productCategoryId}" >${productCategory.productCategoryName}</a></li>
								<!--  		<li><a href="product-details.html" class="active">Product Details</a></li> -->
										
                                        
                                      </c:forEach>
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="blog.html">Blog List</a></li>
										<li><a href="blog-single.html">Blog Single</a></li>
                                    </ul>
                                </li> 
								<li><a href="404.html">404</a></li>
								<li><a href="contact-us.html">Contact</a></li>
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
	
	<section id="advertisement">
		<div class="container">
			<img src="${context}/resources/images/shop/advertisement.jpg" alt="" />
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Sub Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							
							<c:set var="context" value="${pageContext.request.contextPath}" />
                            <c:forEach items="${productSubCategories}" var="productSubCategory">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${context}/viewProductsByProductSubCategory/${productSubCategory.productSubCategoryId}">${productSubCategory.productSubCategoryName}</a></h4>
								</div>
							</div>
							</c:forEach>
							
						</div><!--/category-productsr-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Brands</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
								<c:set var="context" value="${pageContext.request.contextPath}" />
                           		 <c:forEach items="${productBrands}" var="productBrand">
									<li><a href="${context}/viewProductsByProductBrand/${productBrand.productBrandId}"> <span class="pull-right">(50)</span>${productBrand.productBrandName}</a></li>
									<!-- <li><a href=""> <span class="pull-right">(56)</span>Grüne Erde</a></li> -->
									</c:forEach>
								</ul>
							</div>
						</div><!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
								 <b>$ 0</b> <b class="pull-right">$ 600</b>
							</div>
						</div><!--/price-range-->
						
						<div class="shipping text-center"><!--shipping-->
							<img src="${context}/resources/images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center"><c:out value="${productSubCategory.productSubCategoryName}"/></h2>
						
						<c:forEach items="${products}" var="product">
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
                                    	<a href="${context}/viewProductDetails/${product.productId}">
										<img src="${context}/${product.productImage}" alt="${product.productImage}" />
										<h2>&#x20B9;${product.productPrice}</h2>
										<p>${product.productName}</p></a>
                                        
										<a href="${context}/addToCart/${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        <a href="${context}/viewProductDetails/${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-eye"></i>View Product</a>
									</div>
									<img src="${context}/resources/images/home/new.png" class="new" alt="" />
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href=""><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href=""><i class="fa fa-plus-square"></i>Add to compare</a></li>
									</ul>
								</div>
							</div>
						</div>
						</c:forEach>
											<ul class="pagination">
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">&raquo;</a></li>
						</ul>
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>
	
<jsp:include page="footer.jsp"></jsp:include>