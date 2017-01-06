<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/Syoukai.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/appointment_script.js"></script>
<title>ルール説明</title>
</head>
<body>
	<div id="container">
		<nav class="navbar navbar-inverse">
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
				<li><a href="./appointment.jsp">ルーム一覧</a></li>
				<li class="active"><a href="#">ちょっとしたルール説明</a></li>
			</ul>
		</div>
		</nav>

		<div id="text">
			<h1>ルール説明</h1>
			<br>
			<p>ルールは単純です。前の単語の最後の文字と同じ文字から始まる単語を入力してください。単語は全てひらがなで入力し、「ん」で終わる単語は使えません。</p>
		</div>
</body>
</html>