<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | マイページ</title>
	<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%@ include file="./header.jsp" %>

		<div class="user-profile-container">
			<div class="title-container">
				<h1>アカウント情報</h1>
			</div>

			<div class="mail-address-container">
				<p>メールアドレス : ${user.email} </p>
				<a href="ChangeUserEmailServlet" class="edit-btn btn">変更</a>
			</div>
			
			<div class="user-name-container">
				<c:if test="${empty user.name}">
					<p>名前が未登録です。</p>
					<a href="ChangeUserNameServlet" class="edit-btn btn">登録</a>
				</c:if>
				<c:if test="${not empty user.name}">
					<p>ユーザー名 : ${user.name} </p>
					<a href="ChangeUserNameServlet" class="edit-btn btn">変更</a>
				</c:if>
			</div>
			
			<div class="user-password-container">
				<p>パスワード : ********</p>
				<a href="ChangeUserPasswordServlet" class="edit-btn btn">変更</a>
			</div>

			<div class="prev-container">
				<a href="ThreadServlet" class="btn">戻る</a>
			</div>
		</div>
	</body>
</html>