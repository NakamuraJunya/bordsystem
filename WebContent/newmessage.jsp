<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿画面</title>
</head>
<body>
<a href="./">ホーム</a>

<p><font size="5">新規投稿</font></p>

<div>
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
</div>
	<script>

		function check(){
			if(window.confirm('投稿してもよろしいですか？')){ // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
			}
			else{ // 「キャンセル」時の処理
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}
	}

	</script>

<div class="form-area">
<form action="newmessage" method="post" onSubmit="return check()">

<p class="form-item">
<label for="title">件名</label>
<input maxlength='30' name="title"value="${makeMessage.title}" id="title"/>（30文字以内で入力してください)<br />
<p></p>
既存カテゴリー:
	<select name = "category" size="1">
    	<c:forEach items="${categoryList}" var="category">
			<option value="${category.category}"<c:if test="${ category.category == category.category }"> selected </c:if> >>${category.category}</option>
		</c:forEach>
	</select>
<p></p>
<label for="category">新規カテゴリー</label>
<input maxlength='10' name="category"value="${makeMessage.category}" id="category"/>（10文字以内で入力してください）<br />
<p></p>
<label for="text">本文</label>
<br />
<textarea maxlength='1000'  name="text" cols="100" rows="5" class="tweet-box">${makeMessage.text}</textarea>
<br />
<p></p>
<input type="submit" value="投稿">（1000文字以内で入力してください）
</form>
</div>
<p></p>
<div class="copyright">Copyright(c)Junya Nakamura</div>
</body>
</html>
