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
<script type="text/javascript">
function codeCheck(){
	if(document.frm.code.value == ''){
		alert("�ڵ带 �Է��ϼ���.");
		document.frm.code.focus();	return;
	}
	var url="../idcheck/codecheck.html?CODE="+
			document.frm.code.value;
	window.open(url,"_blank","width=450,height=200");
}
</script>
</head>
<body>
<h2 align="center">��ǰ���� ���</h2>
<form:form action="../product/entry.html" method="post"
	modelAttribute="item" name="frm">
��ǰ�ڵ� : <form:input path="code" maxlength="20"/>
<input type="button" value="�ߺ��˻�" onClick="codeCheck()"/>
<font color="red"><form:errors path="code"/></font><br/>
��ǰ�̸� : <form:input path="name" maxlength="20"/>
<font color="red"><form:errors path="name"/></font><br/>
��ǰ���� : <form:input path="price" maxlength="7"/>
<font color="red"><form:errors path="price"/></font><br/>
��ǰ���� : <br/>
<form:textarea path="info" rows="5" cols="20"></form:textarea>
<br/>
������ : <form:input path="origin" maxlength="20"/><br/>
<input type="submit" value="�� ��"/>
<input type="reset" value="�� ��"/>
</form:form>
</body>
</html>












