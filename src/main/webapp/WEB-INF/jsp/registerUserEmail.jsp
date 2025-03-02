<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | アカウント作成</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>アカウント作成</h1>
			<form action="RegisterUserServlet" method="post" class="form">
				<input type="text" name="email" placeholder="メールアドレスを入力">
				<button type="submit" name="action" value="continue" class="btn">続ける</button>
			</form>
			
			<a href="index.jsp">戻る</a>
			
			<c:if test="${not empty message}">
				<p class="error-message">${message}</p>
			</c:if>
		</div>
	</body>
</html>