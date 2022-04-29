<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Document</title>
</head>

<body>
	<div class=" h-100 d-flex justify-content-center align-items-center">
		<div class="d-flex p-2">
			<form:form action="/newuser" method="post" modelAttribute="newUser">
			
			
			<h1>Welcome!</h1>
			<h4>Join our growing community.</h4>
				<h1>Register</h1>
				<div class="mb-3">
				
					<form:label path="userName" class="form-label">First name</form:label>
					<form:input type="text" path="userName" class="form-control"
						id="exampleInputEmail1" />
					
					<span style="color:red;"><form:errors path="userName" /></span>
					<div class="mb-3">
						<div class="mb-3">
							
							<form:label path="email" class="form-label">Email address</form:label>
							<form:input path="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp" />
							<span style="color:red;"><form:errors path="email" /></span>
							
						</div>
						
						<form:label path="password" class="form-label">Password</form:label>
						<form:input type="password" path="password" class="form-control"
							id="exampleInputPassword1" />
						<span style="color:red;"><form:errors path="password" /></span>
	
						
						<form:label path="confirm" class="form-label">Confirm Password</form:label>
						<form:input type="password" path="confirm" class="form-control"
							id="exampleInputPassword1" />
						<span style="color:red;"><form:errors path="confirm" /></span>
					</div>
					
					<button type="submit" class="btn btn-primary">Register</button>
				</div>
			
			</form:form> 
			</div>
				<!-- LOG IN LOG IN LOG IN LOG IN LOG IN LOG IN LOG IN LOG IN LOG IN LOG IN  -->
			<div class="d-flex p-5">
				<div class="mb-3">
				<h1>Log In</h1>
		
				<form:form action="/login" method="post" modelAttribute="newLogin">
					
					<form:label path="email" class="form-label">Email address</form:label>
					<form:input path="email" type="text" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp" />
					<span style="color:red;"><form:errors path="email" /></span>
					
					<form:label path="password" class="form-label">Password</form:label>
					<form:input type="password" path="password" class="form-control"
						id="exampleInputPassword1" /><br>
					<span style="color:red;"><form:errors path="password" /></span>
					
					<button type="submit" class="btn btn-primary">Log In</button>
				</form:form>
				</div>		
			</div>
	</div>
</body>
</html>