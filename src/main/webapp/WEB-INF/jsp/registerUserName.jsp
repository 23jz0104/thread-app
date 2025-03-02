<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | 名前登録</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>ユーザー名登録</h1>

			<!-- 登録用フォーム -->
			<form action="RegisterUserServlet" method="post" class="form">
				<input type="text" name="name" placeholder="ユーザー名入力">
				<button type="submit" name="action" value="registerUserName" class="btn">登録</button>
			</form>
			
			<c:if test="${not empty message}">
				<p class="error-message">${message}</p>
			</c:if>
			
			<a href="ThreadServlet">戻る</a>
		</div>
	</body>
</html>