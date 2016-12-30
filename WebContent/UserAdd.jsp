<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/userAdd.css">
<link rel="stylesheet" href="./css/checkform.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.json.js"></script>
<script type="text/javascript" src="./js/checkform.js"></script>
<title>ユーザー登録</title>
</head>
<body>
	<div id="container">
		<div id="title_container">
			<h1 id="title">かいいんとうろく</h1>
		</div>
		<div class="inner panel panel-default" align="center">
			<div id="panel-lapper">
				<form id="userAddform" action="/Shiritori_Project/UserAdd"
					method="post">
					<div class="input-group">
						ユーザー名<br /> <input type="text" name="name" class="form-control check_form"
							placeholder="太郎">
					</div>
					<div class="input-group">
						パスワード<br /> <input type="password" class="form-control  check_password"
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