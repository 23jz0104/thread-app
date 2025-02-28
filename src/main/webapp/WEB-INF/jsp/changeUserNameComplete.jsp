<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | ユーザー名変更完了</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="main-container">
			<h1>ユーザー名更新完了</h1>
			<p>下記ユーザ名に更新しました。</p>
			<p>新規ユーザー名 : ${user.name}</p>
			
			<a href="UserProfileServlet">戻る</a>	
		</div>
		</body>
</html>