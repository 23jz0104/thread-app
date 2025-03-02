<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | メールアドレス変更</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>メールアドレス変更</h1>
			<p>現在のメールアドレス : ${user.email}</p>
			
			<!-- 更新用フォーム -->
			<form action="ChangeUserEmailServlet" method="post" class="form">
				<input type="text" name="email" placeholder="新しいメールアドレスを入力">
				<button type="submit" class="btn">変更</button>
			</form>
			
			<a href="UserProfileServlet">戻る</a>
			
			<c:if test="${not empty message}">
				<p class="error-message">${message}</p>
			</c:if>
		</div>
	</body>
</html>