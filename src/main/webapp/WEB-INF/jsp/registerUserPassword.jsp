<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | アカウント作成</title>
	</head>
	<body>
		<form action="RegisterUserServlet" method="post">
			<label>
				パスワード : <input type="password" name="password">
			</label>
			<label>
				パスワード再確認 : <input type="password" name="confirmPassword">
			</label>
			
			<button type="submit" name="action" value="register">登録</button>
		</form>
		
		<a href="RegisterUserServlet">戻る</a>
		
		<c:if test="${not empty message}">
			<p style="color : red">${message}</p>
		</c:if>
	</body>
</html>