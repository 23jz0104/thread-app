<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | ユーザー名変更</title>
	</head>
	<body>
		<h1>ユーザー名変更</h1>
		<p>現在のメールアドレス : ${user.name}</p>
		
		<!-- 更新用フォーム -->
		<form action="ChangeUserNameServlet" method="post">
			<label>
				新しいユーザー名 : <input type="text" name="name">
			</label>
			
			<button type="submit">変更</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<a href="UserProfileServlet">戻る</a>
	</body>
</html>