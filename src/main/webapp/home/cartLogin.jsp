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
	<c:when test="${RESULT == 'nocart' }">
	<h3 align="center">상품을 담으려면 로그인을 
		해야합니다.</h3>
	</c:when>
</c:choose>
<form:form modelAttribute="user" method="post"
	action="../cart/login.html">
아이디 : <form:input path="id" size="12"/>
<font color="red"><form:errors path="id"/></font><br/>
패스워드 : <form:password path="password" size="12"/>
<font color="red"><form:errors path="password"/></font>
<br/>
<input type="submit" value="로그인"/>
</form:form>
</body>
</html>











