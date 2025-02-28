<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | 編集</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="form-container">
			<h1>投稿編集</h1>

			<!-- 編集用フォーム -->
			<form action="PostEditServlet" method="post" class="form">
				<input type="text" name="title" value="${post.title}" placeholder="タイトルを入力">
				<textarea name="content" placeholder="内容を入力">${post.content}</textarea>
				<button type="submit" name="postId" value="${post.id}" class="btn">更新</button>
			</form>
			
			<c:if test="${not empty message}">
				<p>${message}</p>
			</c:if>
			
			<a href="ThreadServlet">戻る</a>
		</div>
	</body>
</html>