<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function codeOk(){
	opener.document.frm.code.value=document.frm.CODE.value;
	self.close();
}
</script>
</head>
<body>
<h2 align="center">��ǰ�ڵ� �ߺ� Ȯ��</h2>
<form action="../idcheck/codecheck.html" method="get"
	name="frm">
��ǰ�ڵ� : <input type="text" name="CODE" value="${CODE }"/>
<input type="submit" value="�ߺ� �˻�"/><br/>
<c:if test="${DUP == 'YES' }">
	<script type="text/javascript">
		opener.document.frm.code.value="";
	</script>
	${CODE }�� �̹� ������Դϴ�.
</c:if>
<c:if test="${DUP != 'YES' }">
	${CODE }�� ��� �����մϴ�.
	<input type="button" value="���" onClick="codeOk()"/>
</c:if>
</form>
</body>
</html>











