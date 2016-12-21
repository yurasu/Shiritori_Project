<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/appointment_script.js"></script>
<title>Insert title here</title>
</head>
<body>
	○○さん

	<br />
	<a href="/Shiritori_Project/TestBattle/tes" id="appo">test</a>
	<br />

	<%
		for (int i = 0; i < 10; i++) {
			out.print("<br/>" + "<a href='/Shiritori_Project/TestBattle/"
					+ i + "' id ="+i +">" + "room" + i + "</a>");
		}
	%>
</body>
</html>