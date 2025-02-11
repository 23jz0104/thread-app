<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>掲示板アプリ | ログイン</title>
	</head>
	<body>
		<form action="LoginServlet" method="post">
			<label>
				メールアドレス : <input type="text" name="email">
			</label>
			<label>
				パスワード : <input type="password" name="password">
			</label>
			
			<button type="submit">ログイン</button>
		</form>
		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
		
		<a href="index.jsp">戻る</a>
	</body>
</html>