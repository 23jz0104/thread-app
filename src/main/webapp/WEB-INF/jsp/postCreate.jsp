<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | 投稿</title>
	</head>
	<body>
		<h1>新規投稿</h1>
		
		<!-- 投稿用フォーム -->
		<form action="PostCreateServlet" method="post">
			<label>
				タイトル : <input type="text" name="title" value="${title}">
			</label>
			<label>
				内容 : <textarea name="content">${content}</textarea>
			</label>
			
			<button type="submit" name="action" value="postCreate">登録</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>	
		</c:if>
		
		<a href="ThreadServlet">戻る</a>
	</body>
</html>