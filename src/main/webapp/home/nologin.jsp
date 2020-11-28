<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"
	prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${RESULT == 'nobody' }">
	<h3 align="center">글을 올리리면 로그인 해야합니다.</h3>
	</c:when>
	<c:when test="${ITEM == 'nobody' }">
	<h3 align="center">상품정보를 올리려면, 
	로그인 해야 합니다.</h3>
	</c:when>
</c:choose>
<form:form modelAttribute="user" method="post"
	action="../login/template.html">
아이디 : <form:input path="id" size="12"/>
<font color="red"><form:errors path="id"/></font><br/>
패스워드 : <form:password path="password" size="12"/>
<font color="red"><form:errors path="password"/></font>
<br/>
<input type="submit" value="로그인"/>
</form:form>
<div align="right"><a href="../home/userentry.html">
가입하기</a></div>
</body>
</html>










