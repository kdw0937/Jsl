<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
table {border:1px solid blue;}
</style>
</head>
<body>
<h3 align="center">�Խñ� ���</h3>
<table>
	<tr><td width="40px">�۹�ȣ</td><td width="300px">
	������</td><td width="80px">�ۼ���</td>
	<td width="90px">�ۼ���</td>
	<c:forEach var="bbs" items="${BBS_LIST }">
	<tr><td>${bbs.seqno }</td>
	<td><a href="../read/readDetail.html?SEQNO=${bbs.seqno }">
	${bbs.title }</a></td>
	<td>${bbs.id }</td><td>${bbs.bbs_date }</td>
	</c:forEach>
</table>
<c:forEach var="page" begin="1" end="${COUNT }">
<a href="../read/read.html?pageNo=${page }">${page }</a>
</c:forEach>
</body>
</html>







