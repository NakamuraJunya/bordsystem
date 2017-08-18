<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録画面</title>
</head>
<body>

<font size="5">ユーザー登録</font>
<div class="main-contents">
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
<script>

function check(){
	if(window.confirm('入力した内容で登録してもよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理
		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}

</script>
<form action="signup" method="post" onSubmit="return check()"><br />
	<label for="name">名前:</label>
	<input maxlength='10' name="name" value="${user.name}" id="name"/>（10文字以内で入力してください）<br />
<p></p>
	<label for="loginId">ログインID:</label>
	<input maxlength='20' name="loginId"value="${user.loginId}" id="loginId"/>（半角英数字6文字以上20文字以下で入力してください）<br />
<p></p>
	<label for="password">パスワード:</label>

	<input maxlength='20' name="password"value="${password}"  type= "password" id="password"/>（記号を含む全ての半角文字6文字以上20文字以下で入力してください）<br />
<p></p>
	<label for="nextpassword">確認用パスワード:</label>

	<input maxlength='20' name="nextpassword"value="${nextpassword}"  type= "password" id="password"/> <br />
<p></p>
支店名:
<select name = "selectBranch">
		<option value="0">支店名を選択してください</option>
    	<c:forEach items="${branchList}" var="branch">
					<option value="${branch.id}" <c:if test="${ branch.id == user.branchId }"> selected </c:if> >${branch.name}</option>
			</c:forEach>
</select>
<p></p>
部署・役職:
<select name = "selectPosition">
		<option value="0">部署・役職を選択してください</option>
    	<c:forEach items="${positionList}" var="position">
					<option value="${position.id}"<c:if test="${ position.id == user.positionId }"> selected </c:if>>${position.name}</option>

			</c:forEach>
</select>

<p></p>
	<input type="submit" value="登録" /> <br />
<p></p>
</form>
<a href="usermanagement">ユーザー管理に戻る</a>
<p></p>
<div class="copyright">Copyright(c)Junya Nakamura</div>
</div>
</body>
</html>
