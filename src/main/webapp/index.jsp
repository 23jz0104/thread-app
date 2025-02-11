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
		<header class="header-container">
			<div class="logo-container">
				<a href="index.jsp">
					<img src="img/ThreadApp_logo.png">
				</a>
			</div>
			<nav>
				<ul class="navigation-container">
					<li><a href="#">TOP</a></li>
					<li><a href="#">HOME</a></li>
				</ul>
			</nav>
		</header>
		
		<main>
			<h1 class="title">掲示板アプリ</h1>
			<p>井手 海斗</p>
			
			<div class="login-container">
				<a href="LoginServlet" class="btn">ログイン</a>
				<a href="RegisterUserServlet" class="btn">アカウント作成</a>
			</div>
		</main>
	</body>
</html>