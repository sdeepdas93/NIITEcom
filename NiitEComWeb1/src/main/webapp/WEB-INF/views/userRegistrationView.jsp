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
	
	<c:if test="${successMessage!=null}">
	<div class="alert alert-success alert-dismissable container">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
 	  ${successMessage}
	</div>
	</c:if>
	
	<c:if test="${errorMessage!=null}">
	<div class="alert alert-danger alert-dismissable container">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
 	 <strong>Upps!</strong> ${errorMessage}
	</div>
	</c:if>
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-11 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Please SignUp</h2>
						<form:form  method="post" action="${pageContext.request.contextPath}/registerUser" >
							<form:input type="text" class="form-control" placeholder="Username" path='userName'/>
							<form:input type="text" class="form-control" placeholder="UserId" path='userId'/>
							<form:input type="password" class="form-control" placeholder="Password" path='password' />
							<form:input type="email" class="form-control" placeholder="EmailId" path='userEmailId'/>
							<form:textarea rows="5"  placeholder="Address" path="userAddress"/>
							<br>
							<br>
							<form:input type="number" class="form-control" placeholder="Contact No 1" path="userContactNo1"/>
							<form:input type="number" class="form-control" placeholder="Contact No 2" path="userContactNo2"/>
							
							
							<button type="submit" class="btn btn-default">SignUp</button>
						</form:form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					
				</div>
				
			</div>
		</div>
	</section><!--/form-->


<jsp:include page="footer.jsp"></jsp:include>
	