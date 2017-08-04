<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>ユーザー管理</title>
</head>
<body>
	<div class="header">
		<a href="./">ホーム</a>
		<a href="signup">新規ユーザー登録</a>
		<a href="logout">ログアウト</a>
	</div>
	<p></p>
	<div class="profile">
		現在、<span class="name"><c:out value="${loginUser.name}" /> でログイン中です</span>
	</div>
	<p></p>
	<font size="5">ユーザー管理画面</font>

	<table border=1>
		<tr>
		<th>【名前】</th>
		<th>【ログインID】</th>
		<th>【支店名】</th>
		<th>【部署・役職】</th>
		</tr>
		<tr>
			<c:forEach items="${users}" var="user">

				<td><c:out value="${user.name}" />

				<td><c:out value="${user.login_id}" /></td>

        		<c:forEach items="${Branches}" var="branch">
					<c:if test="${user.branch_id == branch.id}">
					<td><c:out value="${branch.name}" /></td>
					</c:if>
				</c:forEach>

            	<c:forEach items="${Positions}" var="position">
					<c:if test="${user.position_id == position.id}">
					<td><c:out value="${position.name}" /></td>
					</c:if>
				</c:forEach>
				<td>
					<form action = "settings" method = "get" >
						<button type="submit" name="id" value="${user.id}">編集</button>
					</form>
				</td>
					<form action = "settings" method = "get" >
					<td><input type="submit" value="復活" /> <br /></td>
					<tr></tr>
					</form>
		 	 </c:forEach>

		</tr>

	</table>

	<p></p>
	<div class="copyright">Copyright(c)Junya Nakamura</div>
</body>
</html>
