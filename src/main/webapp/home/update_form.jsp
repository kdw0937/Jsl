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
<script type="text/javascript">
function validate(frm){
	if(frm.title.value == ""){
		alert("제목을 입력하세요."); return false;
	}
	if(frm.writer_name.value == ""){
		alert("작성자를 입력하세요."); return false;
	}
	if(frm.email.value == ""){
		alert("이메일을 입력하세요."); return false;
	}
	if(frm.password.value == ""){
		alert("암호를 입력하세요."); return false;
	}
	if(frm.content.value==""){
		alert("내용을 입력하세요."); return false;
	}
	var r = confirm("정말로 변경하시겠습니까?");
	if(r == false) return false;
}
</script>
<form:form modelAttribute="writing" method="post"
	action="../write/updateDo.html" enctype="multipart/form-data"
	onSubmit="return validate(this)">
	<form:hidden path="writing_id" value="${writing.writing_id }"/>
	<table border="1" width="100%">
		<tr><td>글제목</td><td><form:input path="title" size="20"
			value="${writing.title }"/></td></tr>
		<tr><td>작성자</td><td><form:input path="writer_name"
			size="20" value="${writing.writer_name }"/></td></tr>
		<tr><td>이메일</td><td><form:input path="email" size="30"
			value="${writing.email }"/></td></tr>
		<tr><td>암호</td><td><form:password path="password"
			size="20"/></td></tr>
		<tr><td>이미지</td><td><input type="file" name="image"/><br/>
			<img alt="" width="350" border="0" src=
"${pageContext.request.contextPath }/upload/${writing.image_name}"/>
			</td></tr>
		<tr><td>글내용</td><td><form:textarea path="content" 
			rows="8" cols="40" value="${writing.content }"></form:textarea>
			</td></tr>
		<tr><td colspan="2"><input type="submit" value="수 정"/>
			<input type="button" value="취 소"
					onClick="javascript:history.go(-1)"/>
	</table>
</form:form>
</body>
</html>















