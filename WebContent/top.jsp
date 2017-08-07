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
		<a href="newmessage">新規投稿画面</a>
		<a href="logout">ログアウト</a>
	</div>
	<p><p/>
	<div class="profile">
		現在、<span class="name"><c:out value="${loginUser.name}" />でログイン中です</span>
	</div>
	<p><font size="5">商売繁盛掲示板</font></p>

	<div class="messages">

		<c:forEach items = "${messages}" var = "message">

		【件名】：<span class="title"><c:out value="${message.title}" /></span><br/><p><p/>

		【カテゴリー】：<span class="category"><c:out value="${message.category}" /></span><br/><p><p/>

		【本文】:<div class="text"><c:out value="${message.text}" /></div><br/>

		【投稿者】:<span class="name"><c:out value="${message.name}" /></span><br/><p><p/>

		【投稿日時】：<span class="createdAt"><fmt:formatDate value="${message.createdAt}" pattern="yyyy/MM/dd HH:mm:ss" /></span><br/><p><p/>

		【コメント】<div class="text"><c:out value="${makeComment.text}" /></div><br/>

        <div class="form-area">
		<form action="newcomment" method="post">
			<c:forEach items="${comments}" var="comment">
				<c:if test="${message.id == comment.messageId}">
				<span class ="text"> <c:out value ="${comment.text}"/></span><br/><p><p/>
				</c:if>
			</c:forEach>

			<c:forEach items="${Branches}" var="branch">
				<c:if test="${user.branchId ==comments.branch.id}">
					<td>><span class="branch.id"><c:out value="${branch.id}"/></span></td>
				</c:if>
			</c:forEach>

			<c:forEach items="${Positions}" var="position">
				<c:if test="${user.positionId == comments.position.id}">
				<td><span class="position.id"><c:out value="${position.id}" /></span></td>
				</c:if>
			</c:forEach>
         <br />

【コメント入力スペース】
         <input type = hidden name="messageId" value="${message.id}">
         <textarea name="text" cols="100" rows="5" class="tweet-box">${makeComment.text}</textarea>
         <p></p>
         <button name="userId" value="${loginUser.id}">コメント</button>
         </form>
         <form action = "newcomment" method = "delete" onSubmit="return check()" >
	            <button type="submit" name="id" value="${comment.id}">削除</button>
					<input type = hidden name="id" value="${user.id}">
				 </form>
         </div>
			<p><p/>
   		</c:forEach>
	</div>

<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
