<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | メールアドレス変更完了</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="main-container">
			<h1>メールアドレス更新完了</h1>
			<p>下記アドレスに更新しました。</p>
			<p>新規メールアドレス : ${user.email}</p>
		
			<a href="UserProfileServlet">戻る</a>
		</div>
	</body>
</html>