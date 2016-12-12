<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/login.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<p class="sample">
	<form id="loginform" action="/Shiritori_Project/Login" method="post">
		<div Align="center">
			<h1>
				<font face="ＭＳ 明朝,平成明朝">ログイン画面</font>
			</h1>
			ユーザー名：<input type="text" id="name" placeholder="あなたの名前は？"><br />
			パスワード：<input type="text" id="password" placeholder="パスワードは？"><br />
			<input type="button" value="送信" id="send"><br />
		</div>
	</form>
	</p>
</body>
</html>