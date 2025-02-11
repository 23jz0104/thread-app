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
		<p>ログイン中のアカウント : ${user.email}</p>
		<p>現在のアカウントで使用しているパスワードを入力してください。</p>
		
		<!-- 現在使用中のパスワード確認フォーム -->
		<form action="ChangeUserPasswordServlet" method="post">
			<label>
				パスワード入力 : <input type="password" name="password">
			</label>
			
			<button type="submit" name="action" value="passwordConfirm">確認</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<a href="UserProfileServlet">戻る</a>
	</body>
</html>