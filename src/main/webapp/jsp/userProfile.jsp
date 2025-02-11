<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | マイページ</title>
	</head>
	<body>
		<h1>アカウント情報</h1>
		<p>メールアドレス : ${user.email} <a href="ChangeUserEmailServlet">変更</a></p>
		<p>名前 : 
			<c:if test="${empty user.name}">
				名前が未登録です。<a href="ChangeUserNameServlet">こちら</a>から登録できます。
			</c:if>
			<c:if test="${not empty user.name}">
				${user.name} <a href="ChangeUserNameServlet">変更</a>
			</c:if>
		</p> 
		<p>パスワード : ******** <a href="ChangeUserPasswordServlet">変更</a></p>
		<a href="ThreadServlet">戻る</a>
		<a href="LogoutServlet">ログアウト</a>
	</body>
</html>