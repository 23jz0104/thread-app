<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | アカウント作成完了</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="main-container">
			<%@ include file="./header.jsp" %>
			<h1>登録完了</h1>
			<p>${email}で登録しました。<a href="LoginServlet">ログイン</a>できます。</p>
			<a href="index.jsp">TOPへ</a>
		</div>
	</body>
</html>