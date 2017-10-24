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
								<!-- <li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
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
	
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-11 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form name='f' method="post" action="${pageContext.request.contextPath}/j_spring_security_check" >
							<input type="text" placeholder="Username" name='username'/>
							<input type="password" placeholder="Password" name='password' />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					
				</div>
				
			</div>
		</div>
	</section><!--/form-->


<jsp:include page="footer.jsp"></jsp:include>
	