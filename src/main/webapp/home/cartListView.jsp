<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
	prefix="fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h4 align="center">��ٱ��� ����</h4>
<c:choose>
	<c:when test="${CART_LIST == null || SIZE == 'NO' }">
	��ٱ��ϰ� ������ϴ�.
	</c:when>
	<c:otherwise>
	<table border="1">
		<tr><td width="70">��ǰ�ڵ�</td>
			<td width="250">��ǰ�̸�</td>
			<td width="80">��ǰ����</td>
			<td width="50">��ǰ����</td>
			<td width="100">�ݾ�</td>
			<td width="80">����/����</td></tr>
		<c:forEach var="cnt" items="${CART_LIST }">
		<form action="../cart/modify.html" method="post">
			<input type="hidden" name="CODE"
				value="${cnt.code }"/>
			<tr><td>${cnt.code }</td><td>${cnt.name }</td>
				<td><fmt:formatNumber groupingUsed="true">
				${cnt.price }</fmt:formatNumber></td>
				<td><input type="text" size="3"
					name="NUMBER" value="${cnt.num }"/></td>
				<td><fmt:formatNumber groupingUsed="true">
					${cnt.price * cnt.num }</fmt:formatNumber>
				</td>
				<td><input type="submit" name="BTN" 
					value="����"/><input type="submit"
					name="BTN" value="����"/></td></tr>
		</form>
		</c:forEach>
	</table>
	</c:otherwise>
</c:choose>
<form action="../checkout/checkout.html" method="post">
�� �� : <fmt:formatNumber groupingUsed="true">
	${totalAmount }</fmt:formatNumber>��<br/>
	<input type="hidden" name="TOTAL" value=""/>
	<input type="submit" value="�����ϱ�"/>
</form>
</body>
</html>












