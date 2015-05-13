<%--
  Created by IntelliJ IDEA.
  User: Donghyun Seo (egaoneko@naver.com)
  Last Editor: Jisung jeon
  Date: 2015-03-22
  Time: 오후 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<!-- CSS Files -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.css">
<!-- Javascript -->
<script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>

<head>
    <title>Main</title>
</head>

<body>
<h1>Main page</h1> 

<ul>
	<li><a href="<%=request.getContextPath()%>/user/userAdd.do">Sign Up</a></li>
	<c:choose>
		<c:when test="${vo != null }">
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<li><a href="${logoutUrl}">Log Out</a></li>
			<li><a href="<%=request.getContextPath()%>/user/userEdit.do">Your Account</a></li>
			<li><a href="<%=request.getContextPath()%>/user/wishlist.do">Wishlist</a></li>
			<li><a href="<%=request.getContextPath()%>/user/orders.do">Your Orders</a></li>
			<li><a href="<%=request.getContextPath()%>/goods/cart.do">Cart</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="<%=request.getContextPath()%>/main/login.do">Sign In</a></li>
		</c:otherwise>
	</c:choose>
	<li><a href="<%=request.getContextPath()%>/user/userList.do">User List</a></li>
	<li><a href="<%=request.getContextPath()%>/board/list.do?s=default">Board List</a></li>
    <li><a href="<%=request.getContextPath()%>/board/list.do?s=notice">Notice Board List</a></li>
    <li><a href="<%=request.getContextPath()%>/board/list.do?s=qna">QnA Board List</a></li>
    <li><a href="<%=request.getContextPath()%>/upload.do">upload</a></li>
    <li><a href="<%=request.getContextPath()%>/download.do">download</a></li>
    <li><a href="<%=request.getContextPath()%>/goods/list.do">Goods List</a></li>
    <li><a href="<%=request.getContextPath()%>/admin/search.do">Search users</a></li>
    <li><a href="<%=request.getContextPath()%>/email.do">Email</a></li>    
    <!-- <li><a href="test.jsp">Test</a></li>  --> 
    <li><a href="<%=request.getContextPath()%>/send.do">Send email</a></li>
	<li><a href="<%=request.getContextPath()%>/goods/listByBoard.do?boardNumber=13">Goods List By Board</a></li>
	<li><a href="<%=request.getContextPath()%>/board/list.do?s=product">Product Board List</a></li>
</ul>
</body>

</html>