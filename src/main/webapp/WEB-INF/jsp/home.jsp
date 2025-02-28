<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>掲示板アプリ | ホーム</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>
		<div class="main-container">
			<div class="welcome-container">
				<a href="PostCreateServlet" class="post-create-btn">投稿する</a>
				
				<c:if test="${not empty message}">
					<p>${message}</p>
				</c:if>
			</div>
			
			<!-- 掲示板リスト -->
			<ul>
				<c:forEach var="postDTO" items="${postDTOList}">
					<li>
						<div class="post"> <!-- タイトルと投稿者 -->
							<div class="post-info">
								<p>${postDTO.createdDateTime}</p>
								<p>投稿者 : <span class="${postDTO.post.userId eq user.id ? 'owner-content' : ''}">${postDTO.user.name}</span></p>
							</div>
							
							<div class="post-content-container"> 
								<div class="post-content">
									<div class="post-tilte-container">
										<h3 class="post-title">${postDTO.post.title}</h3>
									</div>

									<div class="post-edit">
										<c:if test="${postDTO.post.userId eq user.id}">
											<form action="PostEditServlet" method="get">
												<button type="submit" name="postId" value="${postDTO.post.id}" class="btn">編集</button>
											</form>
											<form action="PostDeleteServlet" method="post">
												<button type="submit" name="postId" value="${postDTO.post.id}" class="btn delete-button">削除</button>
											</form>
										</c:if>
									</div>
								</div>
								<div class="post-text">
									<p><c:out value="${postDTO.post.content}" /></p>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<script src="js/deleteConfirm.js"></script>
	</body>
</html>