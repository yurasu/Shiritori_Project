<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/login.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<form id="loginform" action="Login" method="post">
		<div Align="center">
			<h1>
				<font face="ＭＳ 明朝,平成明朝">ログイン画面</font>
			</h1>
			ユーザー名：<input type="text" id="name" name="name" placeholder="あなたの名前は？"><br />
			パスワード：<input type="text" id="password" name="password" placeholder="パスワードは？"><br />
			<input type="submit" value="送信" id="send"><br />
		</div>
	</form>
</body>
</html>