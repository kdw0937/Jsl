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
	<h3 align="center">��ǰ�� �������� �α����� 
		�ؾ��մϴ�.</h3>
	</c:when>
</c:choose>
<form:form modelAttribute="user" method="post"
	action="../cart/login.html">
���̵� : <form:input path="id" size="12"/>
<font color="red"><form:errors path="id"/></font><br/>
�н����� : <form:password path="password" size="12"/>
<font color="red"><form:errors path="password"/></font>
<br/>
<input type="submit" value="�α���"/>
</form:form>
</body>
</html>











