<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:if test="${ empty writing }">�������� �ʴ� ���Դϴ�.</c:if>
<c:if test="${ ! empty writing }">
<table width="100%" border="1">
	<tr><td>������</td><td>${writing.title }</td></tr>
	<tr><td>�ۼ���</td><td>${writing.writer_name }</td></tr>
	<tr><td colspan="2" align="center"><img alt="" width="350"
			src=
"${pageContext.request.contextPath }/upload/${writing.image_name}"
			height="300" border="0"></td></tr>
	<tr><td>�۳���</td><td>${writing.content }</td></tr>
	<tr><td colspan="2">
		<a href="javascript:goReply()">[���]</a>
		<a href="javascript:goModify()">[����]</a>
		<a href="javascript:goDelete()">[����]</a>
		<a href="../write/writeList.html">[���]</a></td></tr>
</table>
</c:if>
<form name="move">
<input type="hidden" name="id" value="${writing.writing_id }"/>
<input type="hidden" name="parent_id"
				value="${writing.writing_id }"/>
<input type="hidden" name="group_id"
				value="${writing.group_id }"/>
</form>
<script type="text/javascript">
function goReply(){
	document.move.action = "../write/writeReplyForm.html";
	document.move.submit();
}
function goDelete(){
	document.move.action = "../write/writeDelete.html";
	document.move.submit();
}
function goModify(){
	document.move.action = "../write/writeModify.html";
	document.move.submit();
}
</script>
</body>
</html>












