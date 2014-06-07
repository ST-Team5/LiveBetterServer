<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login</title>
<link href="<c:url value="css/bootstrap.css"/>" rel="stylesheet">

<style type="text/css">
/* Override some defaults */
html,body {
	/* background: #333333 url('img/content-rep.jpg') 0 100%;  */
 	background: #333333 url('img/content-rep.jpg') 0 100% no-repeat;
	background-size:100%; 
	min-height:700px;
	min-width: 800px;
	height: 100%;
}

body {
/* 	padding-top: 40px; */
}

.wrapper {
	min-height: 100%;
	height: auto;
	height: 100%;
	margin: 0 auto -62px;
}

.container {
	padding-top: 100px; 
	width: 300px;
/* 	height: 100%; */
}

.container>.content {
	/* background: #616161 url('img/content-rep.jpg') 0 100%;  */
 	background: #262626; 
	padding: 20px;
	margin: 0 -20px;
	-webkit-border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	border-radius: 10px 10px 10px 10px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	color:white;
	border: 2px solid #333333;
}

.login-form {
	margin-left: 38px;
}

legend {
	margin-right: -50px;
	font-weight: bold;
	color: #404040;
}

#authentication_error{
	color: red;
	border-radius: 4px 4px 4px 4px;
	border: 1px solid red;
	margin-bottom: 10px;
	padding: 10px;
}
#header-row{
	width: 220px;
	text-align: center;
	color:#fafafa;
}

#footer, .push {
	height: 57px; /* .push must be the same height as .footer */
}

#footer{
 	position: relative;
	color: white;
	bottom: 0px;
	background: url('img/footer-back.png') 0 0 no-repeat;
	background-color: #303030;
	border-top: 5px solid #333333;
	width:100%;
	height: 57px;
}

.separator-line {
	border-top: 5px solid black;
}

</style>


</head>

<body onload="document.f.j_username.focus();">
<div class="wrapper">
	<div class="container">
		<!-- <h3> <a href="http://www.google.com" title="Live Better" target="_blank">Live Better</a> </h3> -->
	
		<c:if test="${not empty param.authentication_error}">
			<div id="authentication_error">
				The username or password you entered is incorrect.<br /> Please try
				again.
			</div>
		</c:if>
		<div class="content">
			<div class="row-fluid">
				<div class="login-form">
					<h4 id="header-row">
						Login to live better
					</h4>
					<form id="loginForm" name="f" action="<c:url value="/login.do"/>" method="post">
						<fieldset>
							<div class="clearfix">
								<input type="text" placeholder="Username" name="j_username" required>
							</div>
							<div class="clearfix">
								<input type="password" placeholder="Password" name="j_password" required>
							</div>
 							<div class="row-fluid" style="width: 220px;">
								<div class="span7">
								</div>
								<div class="span5" align="right">
									<button class="btn btn-small btn-danger" type="submit" onclick="f.submit()" >Log in</button>
								</div>
							</div>
							<div>
								Forgot <a href="http://www.google.com">Password?</a>
							</div>
							<div>
								Need a Username? <a href="http://www.google.com">Register today</a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="push"></div>
	</div>
		<div id="footer">
 			<div class="separator-line"></div>
				 <div align="center" style="margin-left: 150px;"> 
					<span style="padding-top: 10px;"> &#169 2014 Team Five | <a class="bottom-menu-item-link" href="http://www.google.com" target="_blank">Powered by Team Five</a></span>
					<!-- <img src="img/logo-footer.jpg" width="150" height="52" align="right"> -->
			 	 </div>
		</div>		
	</div>
</body>
</html>
