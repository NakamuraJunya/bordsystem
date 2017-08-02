<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
</head>
<body>
<div class="header">
		<a href="usermanagement">ユーザー管理画面</a>
		<a href="newmessage">新規投稿</a>
		<a href="logout">ログアウト</a>

</div>
<p><p/>
<div class="profile">
ようこそ<span class="name"><c:out value="${loginUser.name}" />さん</span>

</div>

<p><font size="5">商売繁盛掲示板</font></p>

<div class="messages">
		<c:forEach items = "${messages}" var = "message">

				【件名】：<span class="title"><c:out value="${message.title}" /></span><br/><p><p/>

				【カテゴリー】：<span class="category"><c:out value="${message.category}" /></span><br/><p><p/>

				【本文】:<div class="text"><c:out value="${message.text}" /></div><br/>

				【投稿者】:<span class="name"><c:out value="${message.name}" /></span><br/><p><p/>

				【投稿日時】：<span class="created_at"><fmt:formatDate value="${message.created_at}" pattern="yyyy/MM/dd HH:mm:ss" /></span><br/>
	<p><p/>
	<div class="form-area">
	<c:if test="${ isShowMessageForm }">
		<form action="newMessage" method="post">
			コメントしてみよう！<br />
			<textarea name="message" cols="100" rows="5" class="tweet-box"></textarea>
			<br />
			<input type="submit" value="コメント">（500文字まで）
		</form>
	</c:if>
	<p><p/>
</div>

	</c:forEach>
	</div>

<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
