<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板画面</title>
</head>
<body>
	<div class="header">
		<a href="usermanagement">ユーザー管理</a>
		<a href="newmessage">新規投稿</a>
		<a href="logout">ログアウト</a>
	</div>
	<p><p/>
	<div class="profile">
		現在、<span class="name"><c:out value="${loginUser.name}" />でログイン中です</span>
	</div>
	<p><font size="5">商売繁盛掲示板</font></p>
	<p><p/>
	<form action = "./" method = "get"><font size="4">絞込み検索</font>
	<p><p/>
【カテゴリー検索】:
	<select name = "category">
			<option value=""></option>
    	<c:forEach items="${categoryList}" var="category">
			<option value="${category.category}">${category.category}</option>
		</c:forEach>
	</select>
<br />

【日付検索】:
		<label for="startDate"></label>
		<input type="date" name="startDate" value="${startDate}" />～

		<label for="endDate"></label>
		<input type="date" name="endDate" value="${endDate}" /><br />

		<p><p/>

		<button type="submit" >検索</button>
	</form>

	<div class="messages">

		<c:forEach items = "${messages}" var = "message">

		【件名】：<span class="title"><c:out value="${message.title}" /></span><br/><p><p/>

		【カテゴリー】：<span class="category"><c:out value="${message.category}" /></span><br/><p><p/>

		【本文】:
				<span class="text"><c:out value="${message.text}" /></span><br/>

			<form action = "messagedelete" method = "post" >
	           	<button type="submit" name="id" value="${message.id}">削除</button>
				<input type = hidden name="id" value="${user.id}">
		 	</form>
		 	<p><p/>
		【投稿者】:<span class="name"><c:out value="${message.name}" /></span><br/><p><p/>

		【投稿日時】：<span class="createdAt"><fmt:formatDate value="${message.createdAt}" pattern="yyyy/MM/dd HH:mm:ss" /></span><br/><p><p/>

		【コメント一覧】
			<div class="form-area">
			<c:forEach items="${comments}" var="comment">
				<c:if test="${message.id == comment.messageId}">
					<span class ="text"> <c:out value ="${comment.text}"/></span><br/><p><p/>
					【投稿者】:<span class="id"><c:out value="${comment.name}" /></span><br/><p><p/>
					【投稿日時】：<span class="createdAt"><fmt:formatDate value="${comment.createdAt}" pattern="yyyy/MM/dd HH:mm:ss" /></span><br/><p><p/>
					<form action = "commentdelete" method = "post" >
	           			<button type="submit" name="id" value="${comment.id}">削除</button><p><p/>
						<input type = hidden name="id" value="${user.id}">
		 			</form>
				</c:if>
			</c:forEach>
		   	</div>
   	      	<br />
			<form action="newcomment" method="post">
				【コメント入力スペース】
         		<input type = hidden name="messageId" value="${message.id}">
         		<textarea name="text" cols="100" rows="5" class="tweet-box">${makeComment.text}</textarea>
        		<p></p>
        	 	<button name="userId" value="${loginUser.id}">コメント</button>
       		 </form>
       		 <p></p>
   		</c:forEach>
	</div>

<div class="copyright">Copyright(c)Junya Nakamura</div>

</body>
</html>
