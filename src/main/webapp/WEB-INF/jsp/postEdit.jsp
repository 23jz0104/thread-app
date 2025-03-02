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
				<input type="text" name="title" value="${not empty post.title ? post.title : title}" placeholder="タイトルを入力">
				<textarea name="content" placeholder="内容を入力">${not empty post.content ? post.content : content}</textarea>
				<button type="submit" name="postId" value="${not empty post.id ? post.id : postId}" class="btn">更新</button>
			</form>
			
			<a href="ThreadServlet">戻る</a>
			<c:if test="${not empty message}">
				<p class="error-message">${message}</p>
			</c:if>
		</div>
	</body>
</html>