<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	</head>
	<body>
		<c:if test="${empty user}">
			<header class="header">
				<div class="header-inner">
					<div class="logo-container">
						<img src="img/thread-app-logo.png">
					</div>
	
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
					<div class="logo-container">
						<img src="img/thread-app-logo.png">
					</div>
	
					<nav class="gnav">
						<ul class="gnav-list">
							<li><a href="UserProfileServlet">マイページ</a></li>
							<li><a href="LogoutServlet">ログアウト</a></li>
						</ul>
					</nav>
				</div>
			</header>
		</c:if>
	</body>
</html>