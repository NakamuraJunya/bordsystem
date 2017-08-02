<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集画面</title>
</head>
<body>
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

	<label for="name">名前</label>
	<input name="name" value="${name}" /><br />

	<label for="login_id">アカウント名</label>
	<input name="login_id" value="${login_id}" /><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="branch_id">支店名</label>
	<input name="branch_id" value="${branch_id}" id="branch_id"/> <br />

	<label for="position_id">役職・部署</label>
	<input name="position_id" value="${position_id}" id="position_id"/> <br />

	<input type="submit" value="登録" /> <br />
	<a href="./">戻る</a>

<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
