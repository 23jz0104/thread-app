<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | 名前登録</title>
	</head>
	<body>
		<form action="RegisterUserServlet" method="post">
			<label>
				名前 : <input type="text" name="name"> 
			</label>
			<button type="submit" name="action" value="registerUserName">登録</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
	</body>
</html>