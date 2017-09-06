<jsp:include page="header2.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="jumbotron text-left" style="padding-left:10px;">
  <h3>Login</h3>
  <p><button type="button" class="btn btn-primary">Continue Shopping</button></p> 
</div>

<div class="container">
  <h2>Please Login</h2>
  <hr>
  <form class="form-horizontal" action="#">
    <div class="form-group">
      <label class="control-label col-sm-2" for="userId">UserId:</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="userId" placeholder="Enter UserId" name="userId">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox" name="remember"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form>
</div>

<jsp:include page="footer.jsp"></jsp:include>
