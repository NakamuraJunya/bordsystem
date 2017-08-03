<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
</head>
<body>
<p></p>
	<a href="./">ユーザー管理画面</a>
	<p></p>
<font size="5">ユーザー編集画面</font>
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
<form action = settings  method = "post" enctype ="multipart/form-date"><br/>
	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" /><br />

	<label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.login_id}" /><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="password">パスワード確認</label>
	<input name="password" type="password" id="password"/> <br />

    <label for="branch_id">支店名:</label>
	  <input name="branch_id"value="${editUser.branch_id}"id="branch_id"/> <br />

    <label for="position_id">部署・役職:</label>
 	<input name="position_id"value="${editUser.position_id}"id="position_id"/> <br />

	<button type="submit" name="id" value="${editUser.id}">変更</button>

</form>
<p></p>
<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
