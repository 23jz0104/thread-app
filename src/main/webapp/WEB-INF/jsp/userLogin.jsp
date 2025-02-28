<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | ログイン</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>ログイン</h1>
			<form action="LoginServlet" method="post" class="form">
				<input type="text" name="email" placeholder="メールアドレスを入力">
				<input type="password" name="password" placeholder="パスワードを入力">
				<button type="submit" class="btn">ログイン</button>
			</form>
			
			<c:if test="${not empty message}">
				<p>${message}</p>
			</c:if>
			
			<a href="index.jsp">戻る</a>
		</div>
	</body>
</html>