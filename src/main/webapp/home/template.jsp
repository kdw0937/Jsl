<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
			prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
		prefix="fmt" %>			
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
#login { width:80%; border:1px dashed blue; padding:10px;
		margin-top:10px; margin-left:10px;}
#menu {margin-top:10px; margin-left:20px; background-color:orange;}
#header {background-color:cyan;}
#footer {background-color:cyan;}
td#content { background-color:cyan;}
</style>
</head>
<body onload="startClock()">
<script type="text/javascript">
function workingClock(){
	 var days=["��","��","ȭ","��","��","��","��"];
	 var today = new Date();
	 var year = today.getFullYear();
	 var month = today.getMonth() + 1;
	 if(month < 10) month="0"+month;
	 var date = today.getDate();
	 if(date < 10) date="0"+date;
	 var index = today.getDay();
	 var day = days[index];
	 var hour = today.getHours();
	 var min = today.getMinutes();
	 if(min < 10) min="0"+min;
	 var sec = today.getSeconds();
	 if(sec < 10) sec ="0"+sec;
	 var str=year+"/"+month+"/"+date+" "+hour+":"+min+":"+sec;
	 document.getElementById("clock").innerHTML = str;
}
function startClock(){
	 setInterval(workingClock, 10);//1���ĸ��� workingClock�Լ� ȣ��
}
</script>
<fmt:setBundle basename="main"/>
<div id="header">
<table align="center">
	<tr><td><h2 align="center">��� ������~
	</h2></td></tr>
</table>
</div>
<table align="center" border="1">
<tr>
	<td width="200">
		<div id="login">
		<c:choose>
			<c:when test="${sessionScope.loginUser != null }">
				<jsp:include page="logout.jsp"/>
			</c:when>
			<c:when test="${HEADER != null }">
				<jsp:include page="${HEADER }"/>
			</c:when>
			<c:otherwise>
				<jsp:include page="../login/login.html"/>
			</c:otherwise>
		</c:choose>
		</div>
		<div id="clock" align="center"></div>
		<div id="menu">
		<ul>
			<li><a href="../home/intro.html?BODY=intro.jsp">�Ұ���</a>	</li>
			<li><a href="../home/bbs.html">�Խñ� ���</a></li>
			<li><a href="../read/read.html">�Խñ� ���</a></li>
			<li><a href="template.jsp?BODY=noticeInput.jsp">
				������ ���</a></li>
			<li><a href="noticeList">
				������ ���</a></li>
			<li><a href="template.jsp?BODY=input.jsp">
				���� ���</a></li>
			<li><a href="matjipList">���� ���</a></li>
			<li><a href="../product/open.html">��ǰ ���</a>
			</li>
			<li><a href="../read/product.html">��ǰ ���</a></li>
			<li><a href="../cart/show.html">��ٱ��� ����</a></li>
			<li><a href="../write/writeForm.html">��� �� �̹��� �Խ���</a></li>
			<li><a href="../write/writeList.html">�̹��� �Խ��� ���(��켱)</a></li>
			<li><a href="">�̹��� �Խ��� ���(���켱)</a></li>
		</ul>
		</div>
	</td>
	<td width="600" id="content">
		<c:choose>
			<c:when test="${BODY != null }">
				<jsp:include page="${BODY }"/>
			</c:when>
			<c:otherwise>
				<jsp:include page="intro.jsp"/>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
</table>
</body>
</html>










