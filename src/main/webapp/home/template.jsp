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
	 var days=["일","월","화","수","목","금","토"];
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
	 setInterval(workingClock, 10);//1초후마다 workingClock함수 호출
}
</script>
<fmt:setBundle basename="main"/>
<div id="header">
<table align="center">
	<tr><td><h2 align="center">놀다 가세요~
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
			<li><a href="../home/intro.html?BODY=intro.jsp">소개글</a>	</li>
			<li><a href="../home/bbs.html">게시글 등록</a></li>
			<li><a href="../read/read.html">게시글 목록</a></li>
			<li><a href="template.jsp?BODY=noticeInput.jsp">
				공지글 등록</a></li>
			<li><a href="noticeList">
				공지글 목록</a></li>
			<li><a href="template.jsp?BODY=input.jsp">
				맛집 등록</a></li>
			<li><a href="matjipList">맛집 목록</a></li>
			<li><a href="../product/open.html">상품 등록</a>
			</li>
			<li><a href="../read/product.html">상품 목록</a></li>
			<li><a href="../cart/show.html">장바구니 보기</a></li>
			<li><a href="../write/writeForm.html">답글 및 이미지 게시판</a></li>
			<li><a href="../write/writeList.html">이미지 게시판 목록(행우선)</a></li>
			<li><a href="">이미지 게시판 목록(열우선)</a></li>
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










