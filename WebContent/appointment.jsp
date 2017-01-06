<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/appointment.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/appointment_script.js"></script>
<title>ルーム一覧</title>
</head>
<body>
	<div id="container">
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbarEexample1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">しりとりプロジェクト</a>
			</div>

			<div class="collapse navbar-collapse" id="navbarEexample1">
				<ul class="nav navbar-nav">
					<li><a href="./Syoukai.jsp">しりとりプロジェクトとは</a></li>
					<li class="active"><a href="#">ルーム一覧</a></li>
					<li><a href="./Rule.jsp">ちょっとしたルール説明</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<ul class="list-group" id="room_list">
					<%
						for (int i = 0; i < 9; i++) {
							out.print("<a class=\"list-group-item\" href='/Shiritori_Project/TestBattle/"+i+"'>");
							out.print("Room"+ i);
							out.print("</a>");
						}
					%>
		</ul>
	</div>
</body>
</html>