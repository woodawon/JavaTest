<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버와 클라이언트간의 소켓 통신 문자 내용 출력</title>
</head>
<body>
	<%
	for(String result : SimpleSocketServer.log) {
	%>
	<li><%=result %></li>
	<%
	}
	%>
</body>
</html>