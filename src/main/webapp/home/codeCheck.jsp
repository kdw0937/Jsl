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
<h2 align="center">상품코드 중복 확인</h2>
<form action="../idcheck/codecheck.html" method="get"
	name="frm">
상품코드 : <input type="text" name="CODE" value="${CODE }"/>
<input type="submit" value="중복 검사"/><br/>
<c:if test="${DUP == 'YES' }">
	<script type="text/javascript">
		opener.document.frm.code.value="";
	</script>
	${CODE }는 이미 사용중입니다.
</c:if>
<c:if test="${DUP != 'YES' }">
	${CODE }는 사용 가능합니다.
	<input type="button" value="사용" onClick="codeOk()"/>
</c:if>
</form>
</body>
</html>











