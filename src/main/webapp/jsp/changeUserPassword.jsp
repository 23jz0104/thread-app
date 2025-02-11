<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | パスワード変更</title>
	</head>
	<body>
		<h1>パスワード変更</h1>
		<p>現在ログイン中のアカウント : ${user.email}</p>
		
		<!-- パスワード更新用フォーム -->
		<form action="ChangeUserPassword" method="post">
			<label>
				新しいパスワード : <input type="password" name="password">
			</label>
			<label>
				パスワード再入力 : <input type="password" name="confirmPassword">
			</label>
			
			<button type="submit" name="action" value="changePassword">確定</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<a href="ChangeUserPasswordServlet">戻る</a>
	</body>
</html>