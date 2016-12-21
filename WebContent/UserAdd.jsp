<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/login.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<div id="container">
		<div id="title_container">
			<h1 id="title">しりとりプロジェクト</h1>
		</div>
		<div class="inner panel panel-default" align="center">
			<div id="panel-lapper">
				<form id="loginform" action="/Shiritori_Project/SignUp"
					method="post">
					<div class="input-group">
						ユーザー名<br /> <input type="text" name="name" class="form-control"
							placeholder="太郎">
					</div>
					<div class="input-group">
						パスワード<br /> <input type="password" class="form-control"
							name="password">
					</div>
					<div class="input-group">
						<button type="submit" class="btn btn-primary">SingUP</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>