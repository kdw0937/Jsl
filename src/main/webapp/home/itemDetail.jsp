<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 
	prefix="fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2 align="center">상품 상세 정보</h2>
[상품번호] : ${ITEM.code }<br/>
[상품이름] : ${ITEM.name }<br/>
[상품가격] : <fmt:formatNumber groupingUsed="true">
${ITEM.price }</fmt:formatNumber>
[원산지] : ${ITEM.origin }<br/>
<textarea rows="5" cols="20">${ITEM.info }</textarea>
</body>
</html>












