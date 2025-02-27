<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | パスワード更新完了</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="main-container">
			<h1>パスワードを更新しました。</h1>
			<p>ログイン中のアカウント : ${user.email}</p>
			<a href="UserProfileServlet">戻る</a>
		</div>
	</body>
</html>