<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"
	prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="form" %>	    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form:form modelAttribute="user" method="post"
	action="../login/writeTemplate.html">
���̵� : <form:input path="id" size="12"/>
<font color="red"><form:errors path="id"/></font><br/>
�� ȣ : <form:password path="password" size="12"/>
<font color="red"><form:errors path="password"/></font>
<br/><input type="submit" value="�α���"/>
<input type="reset" value="���"/>
</form:form>
<div align="right">
<a href="../home/userentry.html">�����ϱ�</a>
</div>
</div>
</body>
</html>









