<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | ユーザー名変更</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>ユーザー名変更</h1>
			<p>現在のユーザー名 : 
				<c:if test="${not empty user.name}">${user.name}</c:if>
				<c:if test="${empty user.name }">未登録</c:if>
			</p>
			
			<!-- 更新用フォーム -->
			<form action="ChangeUserNameServlet" method="post" class="form">
				<input type="text" name="name" placeholder="新しいユーザー名を入力">
				<button type="submit" class="btn">変更</button>
			</form>
			
			<c:if test="${not empty message}">
				<p>${message}</p>
			</c:if>
			
			<a href="UserProfileServlet">戻る</a>
		</div>
	</body>
</html>