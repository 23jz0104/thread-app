<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | 編集</title>
	</head>
	<body>
		<h1>投稿編集</h1>
		<form action="PostEditServlet" method="post">
			<label>
				タイトル : <input type="text" name="title" value="${post.title}">
			</label>
			<label>
				内容 : <textarea name="content">${post.content}</textarea>
			</label>
			
			<button type="submit" name="postId" value="${post.id}">更新</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<a href="ThreadServlet">戻る</a>
	</body>
</html>