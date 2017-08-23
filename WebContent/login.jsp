<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./css/login.css" rel="stylesheet" type="text/css">
	<title>ログイン画面</title>
</head>
<body>
<form action="login" method="post"><br />
  <h1>商売繁盛掲示板</h1>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

	<label for="login_id">ログインID</label>
	<input name="login_id"value="${login_id}" id="login_id"/> <br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />
	<p></p>
	<button>ログイン</button>
</form>
<p></p>

<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
