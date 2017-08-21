<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    <title>ユーザー管理画面</title>
    <style type="text/css">
body {
  background: linear-gradient(to right, gold 0%, orange 100%);/*正規の指定*/
}
	</style>
</head>
<body>
	<div id="menu">
	<ul>
	<li>	<a href="./">ホーム</a></li>
	<li>	<a href="signup">ユーザー登録</a></li>
	</ul>
	<div class="header">
	<ul>
	<li>	<a href="logout">ログアウト</a></li>
	</ul>
	</div>
	</div>
<p></p>

	<div class="profile">
		現在、<span class="name"><c:out value="${loginUser.name}" /> でログイン中です</span>
	</div>


	<font size="5">ユーザー管理</font>
		<div class="main-contents">

	<table border=1>
		<tr>
		<th>【名前】</th>
		<th>【ログインID】</th>
		<th>【支店名】</th>
		<th>【部署・役職】</th>
		<th>【編集項目】</th>
		<th>【復活・停止】</th>
		</tr>
		<tr>
			<c:forEach items="${users}" var="user">

				<td><c:out value="${user.name}" />

				<td><c:out value="${user.loginId}" /></td>

        		<c:forEach items="${Branches}" var="branch">
					<c:if test="${user.branchId == branch.id}">
					<td><c:out value="${branch.name}" /></td>
					</c:if>
				</c:forEach>

            	<c:forEach items="${Positions}" var="position">
					<c:if test="${user.positionId == position.id}">
					<td><c:out value="${position.name}" /></td>
					</c:if>
				</c:forEach>
				<td>
					<form action = "settings" method = "get" >
						<button type="submit" name="id" value="${user.id}">編集</button>
					</form>
				</td>
				<td>
				<script>

					function check(){
						if(window.confirm('選択したアカウントを変更してもよろしいですか？')){ // 確認ダイアログを表示
							return true; // 「OK」時は送信を実行
						}
						else{ // 「キャンセル」時の処理
						window.alert('キャンセルされました'); // 警告ダイアログを表示
						return false; // 送信を中止
						}
					}

				</script>
				<c:if test="${loginUser.id!=user.id}">
				<form action = "isworking" method = "post" onSubmit="return check()" >
	             <c:if test="${user.is_working == 1}">
					<button type="submit" name=is_working value="${0}">停止</button>
					<input type = hidden name="id" value="${user.id}">
				 </c:if>

				 <c:if test="${user.is_working == 0}">
					<button type="submit" name="is_working" value="${1}">復活</button>
					<input type = hidden name="id" value="${user.id}">
				 </c:if>
                </form>
                </c:if>
                <c:if test="${loginUser.id==user.id}">
                	ログイン中
                 </c:if>
				</td>
					<tr></tr>
		 	 </c:forEach>

		</tr>

	</table>
	<p></p>
	</div>
</body>
</html>
