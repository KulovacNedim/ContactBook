<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<%@ include file="headContent.jsp"%>
	</head>

	<body>

		<div class="container">
		  
		  <div class="row">
		    <div class="col-sm-12" style="">
		    	
		    	<h1>Welcome to Contact Book Application</h1>
		  
		  		<p>Making your contact easy to rich.</p>

		    </div>
		  </div>


		  <div class="row" style="padding-top: 5%;">
		    <div class="col-sm-6" style="">

		    	<div class="alert alert-warning ${wrongCredentials==null ? 'hidden' : 'show'}" >
				    <strong>Warning!</strong> <c:out value = "${wrongCredentials}"/>
				</div>
				<div class="alert alert-warning ${inactiveUser==null ? 'hidden' : 'show'}" >
				    <strong>Alert!</strong> <c:out value = "${inactiveUser}"/>
				</div>
		    	
		    	<br>

		    	<h3>Login section</h3>

		    	<br>

				<form  action="login" method="post">

				    <div class="form-group">
				      <label for="nickname">Enter nickname:</label>
				      <input type="text" name="nickname" class="form-control" id="nickname" placeholder="Enter nickname" required autofocus>
				    </div>

				    <div class="form-group">
				      <label for="password">Password:</label>
				      <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" required>
				    </div>

				    <button type="submit" class="btn btn-default">Login</button>
				  </form>
				
		    </div>

		    <div class="col-sm-6" style="">

		    	<div class="alert alert-warning  ${existingNickname==null ? 'hidden' : 'show'}"">
				    <strong>Warning!</strong> <c:out value = "${existingNickname}"/>
				</div>

				<div class="alert alert-warning  ${inactiveUserReg==null ? 'hidden' : 'show'}"">
				    <strong>Warning!</strong> <c:out value = "${inactiveUserReg}"/>
				</div>

		    	<br>

				<h3>Register section</h3>
		    	
		    	<br>

		    	<form  action="register" method="post">

				    <div class="form-group">
				      <label for="newNickname">Enter nickname:</label>
				      <input type="newNickname" name="newNickname" class="form-control" id="newNickname" placeholder="Enter nickname" required>
				    </div>

				    <div class="form-group">
				      <label for="password">Password:</label>
				      <input type="password" name="password" class="form-control" id="passReg" placeholder="Enter password" title="Must contain at least 8 or more characters" pattern=".{8,}" required>
				    </div>

				    <div class="form-group hiddenElement">
				        <input type="text" value="editRegContact" name="flag"/>
				    </div>
				    
				    <button type="submit" class="btn btn-default">Register</button>

			  	</form>

			  	<br>

			  	<div class="alert alert-info" id="message">
				    <p class="text-primary" id="length"><strong>Info:</strong> Minimum <b>8 characters</b></p>
				</div>

		    </div>
		  </div>
		</div>

		<script>
			function functionHide() {
			    document.getElementById("message").style.display = "none";
			}
		    window.onload = functionHide;


			var myInput = document.getElementById("passReg");
			var length = document.getElementById("length");

			// When the user starts to type something inside the password field
			myInput.onkeyup = function() {
			  // Validate length
			  if(myInput.value.length >= 8 || myInput.value.length == 0) {
			    document.getElementById("message").style.display = "none";
			  } else {
			    document.getElementById("message").style.display = "block";
			  }
			}
		</script>
	</body>
</html>