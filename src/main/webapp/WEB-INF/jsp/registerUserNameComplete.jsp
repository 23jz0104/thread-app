<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | 名前登録完了</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="main-container">
			<h1>登録完了</h1>
			<p>ユーザー名を${name}で登録しました。</p>
			<a href="ThreadServlet">TOPへ</a>
		</div>
	</body>
</html>