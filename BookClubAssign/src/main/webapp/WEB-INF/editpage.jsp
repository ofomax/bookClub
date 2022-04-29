<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<div class="d-flex justify-content-around">
		<a href="/logout">Logout</a>
		<a href="/newBook">+Add to My Shelf</a>
	</div>
	<div>
		<h1>Edit a Book to Your Shelf!</h1>
		<a href="/dashboard">back to the shelves</a>
	</div>
	<form:form action="/update/${book.id}" method="post" modelAttribute="book">
	  <span style="color:red;"><form:errors path="title" /></span>
	  <form:label path="title" for="title">Title:</form:label>
	  <form:input type="text" path="title" /><br><br>
	  
	  
	  <span style="color:red;"><form:errors path="author" /></span>
	  <form:label path="author" for="author">Author:</form:label>
	  <form:input type="text" path="author" /><br><br>
	  
	  
	  <span style="color:red;"><form:errors path="thoughts" /></span>
	  <form:label path="thoughts" for="thoughts">My thoughts:</form:label>
	  <form:input type="text" path="thoughts" /><br><br>
	  
	  <button type="submit" class="btn btn-primary"
	  >Submit</button>
	</form:form>

	
	
</body>
</html>