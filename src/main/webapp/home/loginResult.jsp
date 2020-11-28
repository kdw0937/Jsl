<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope.loginUser != null }">
	로그인 되었습니다.<br/>
	환영합니다, ${sessionScope.loginUser }님~
	</c:when>
	<c:otherwise>
	로그인 되지 않았습니다.<br/>
	계정과 암호를 확인하세요.
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${cartLogin == 'SUCCESS' || 
			imageLogin == 'SUCCESS'}">
		<script type="text/javascript">
		self.close();
		opener.window.location.reload();/*새로고침*/
		</script>
	</c:when>
</c:choose>
</body>
</html>



















