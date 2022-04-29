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
	<h1>${book.title}</h1>
	<a href="/dashboard">back to the shelves</a>
	<h2>${book.user.userName} read <span style="color:purple;">${book.title}</span> by <span style="color:green;">${book.author}</span></h2>
	<h4>Here are ${book.user.userName}'s thoughts:</h4>
	<p> ${book.thoughts}
	</p>
	
	<c:if test="${sessionScope.user_id==book.user.id}">
		<a href="/${book.id}/edit">Edit</a>
	</c:if>

	
	
</body>
</html>