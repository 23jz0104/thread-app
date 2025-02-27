<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
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
			
			<c:if test="${not empty message}">
				<p>${message}</p>
			</c:if>
			
			<a href="UserProfileServlet">戻る</a>
		</div>
	</body>
</html>