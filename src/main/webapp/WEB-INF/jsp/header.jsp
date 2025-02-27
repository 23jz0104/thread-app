<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<c:if test="${empty user}">
			<header class="header">
				<div class="header-inner">
					<h1 class="title-logo">thread-app</h1>
	
					<nav class="gnav">
						<ul class="gnav-list">
							<li><a href="#">TOP</a></li>
							<li><a href="#">HOME</a></li>
						</ul>
					</nav>
				</div>
			</header>
		</c:if>
		<c:if test="${not empty user}">
			<header class="header">
				<div class="header-inner">
					<h1 class="title-logo">thread-app</h1>
	
					<nav class="gnav">
						<ul class="gnav-list">
							<li>${user.email}でログインしています</li>
							<li><a href="UserProfileServlet">マイページ</a></li>
							<li><a href="LogoutServlet">ログアウト</a></li>
						</ul>
					</nav>
				</div>
			</header>
		</c:if>
	</body>
</html>