<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | ホーム</title>
	</head>
	<body>
		<header>
			<ul>
				<li>${user.email}でログインしています</li>
				<li><a href="UserProfileServlet">マイページ</a></li>
				<li><a href="LogoutServlet">ログアウト</a></li>
			</ul>
		</header>
		<h1>ようこそ</h1>
		
		<a href="PostCreateServlet">投稿する</a>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<!-- 掲示板リスト -->
		<ul>
			<c:forEach var="postDTO" items="${postDTOList}">
				<li style="border : solid 1px; border-radius :6px; margin-bottom : 10px;">
					<div style="display : flex"> <!-- タイトルと投稿者 -->
						<h2>${postDTO.post.title}</h2>
						<p>${postDTO.post.createdDate}</p>
						<p>投稿者 : ${postDTO.user.name}</p>
					</div>
					
					<p>${postDTO.post.content}</p>
					
					<!-- 投稿されたユーザーIDとログイン中のユーザーIDが一致していれば -->
					<c:if test="${postDTO.post.userId eq user.id}">
						<form action="PostEditServlet" method="get">
							<button type="submit" name="postId" value="${postDTO.post.id}">編集</button>
						</form>
						<form action="PostDeleteServlet" method="post">
							<button type="submit" class="delete-button" name="postId" value="${postDTO.post.id}">削除</button>
						</form>
					</c:if>
				</li>
			</c:forEach>
		</ul>
		
		<script src="js/deleteConfirm.js"></script>
	</body>
</html>