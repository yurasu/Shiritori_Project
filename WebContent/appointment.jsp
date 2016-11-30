<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	○○さん
	<a href="">部屋をつくる</a>
	<a href="">部屋を探す</a>
	<br/>
	<a href="/Shiritori_Project/TestBattle/tes">test</a><br/>

	<%
		for(int i = 0; i<10; i++){
			out.print("<br/>"+"<a href='/Shiritori_Project/TestBattle/ " +i+ "'>"+"room"+i+"</a>");
		}
	%>
</body>
</html>