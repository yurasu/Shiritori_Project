<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>WebSocketDemo Client</title>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script src="../js/jquery.balloon.min.js"></script>
<script type="text/javascript" src="../js/room_script.js"></script>
<link rel="stylesheet" href="../css/room.css">
<meta charset="UTF-8">
</head>

<body>
	<div id="container">
		<div id="player1"></div>
		<div id="player2"></div>
		<div id="player3"></div>
		<div id="player4"></div>
		<div id="system">
			<input type="text" id="text" /> <br /> <input type="button"
				value="Send" id="send"> <br /> <input type="button"
				value="Join" id="join"> <br />
			<textarea id="message"></textarea>
		</div>
	</div>
</body>
</html>