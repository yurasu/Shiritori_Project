<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>バトルルーム</title>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/room_script.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/room.css">
<link rel="stylesheet" href="../css/modal.css">
<meta charset="UTF-8">
</head>

<body>
	<div id="container">
		<div id="modal">
			<div id="modal-header">
				<h4>しりとりに参加しますか？</h4>
			</div>
			<div id="modal-body">
				<div id="modal-btn" class="input-group">
					<input type="hidden" id="username" value=<%out.print(session.getAttribute("name")); %> >
					<button type="button" id="join" class="btn btn-success modal-btn">参加!</button>
					<button type="button" class="btn btn-danger modal-btn">退出...</button>
				</div>
			</div>
		</div>

		<div id="players">
			<h3 class="header_title bg-primary">参加者一覧</h3>

			<div id="start-div">
				<button class="btn btn-success " type="button" id="start-btn">START</button>
			</div>

		</div>
		<div id="system">
			<h3 class="header_title bg-primary">Room</h3>
			<div id="msgarea"></div>
			<div id="player-form" class="row">
				<div>
					<input id="input-remark" type="text" />
					<button class="btn btn-primary " type="button" id="send">
						Send</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>