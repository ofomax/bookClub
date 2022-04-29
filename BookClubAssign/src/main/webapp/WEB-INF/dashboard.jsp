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
	<h1>Welcome, ${user_name}</h1>
	<h6>Books from everyone's Shelves:</h6>

	<!-- TABLE ALL BOOKS -->
	<table class="table table-striped table-dark">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col">Author Name</th>
            <th scope="col">Posted By:</th>
          </tr>
        </thead>
        <tbody>
          	<c:forEach items="${all_books}" var="i">
          <tr>
          		<td>${i.id}</td>
	            <th scope="row"><a href="/${i.id}/show">${i.title}</a></th>
	            <td>${i.author}</td>
	            <td>${i.user.userName}</td>
	            
          </tr>
          	</c:forEach>
          	
        </tbody>
	</table>
	
</body>
</html>