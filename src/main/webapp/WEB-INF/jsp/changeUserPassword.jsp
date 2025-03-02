<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | パスワード変更</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>パスワード変更</h1>
			<p>ログイン中のアカウント : ${user.email}</p>
			
			<!-- パスワード更新用フォーム -->
			<form action="ChangeUserPasswordServlet" method="post" class="form">
				<input type="password" name="password" placeholder="新しいパスワードを入力">
				<input type="password" name="confirmPassword" placeholder="確認用のパスワードを入力">
			
				<button type="submit" name="action" value="changePassword" class="btn">確定</button>
			</form>
			
			<a href="ChangeUserPasswordServlet">戻る</a>
			
			<c:if test="${not empty message}">
				<p class="error-message">${message}</p>
			</c:if>
		</div>
	</body>
</html>