<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="WEB-INF/jsp/header.jsp" %>
		
		<main class="form-container">
			<h1>掲示板アプリ</h1>
			<p>井手 海斗</p>
			
			<div class="auth">
				<a href="LoginServlet">ログイン</a>
				<a href="RegisterUserServlet">アカウント作成</a>
			</div>
		</main>
	</body>
</html>