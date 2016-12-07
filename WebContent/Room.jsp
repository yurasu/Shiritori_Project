<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WebSocketDemo Client</title>
        <script type="text/javascript" src = "../js/jquery.min.js"></script>
        <script type="text/javascript" src = "../js/room_script.js"></script>
        <meta charset="UTF-8">
    </head>

    <body>

		<input type="text" id="message" /><br/>
		<input type="button" value="Send" id="send"><br/>
		<input type="button" value="Join" id="join"><br/>
        <span id="msgbox"></span>
    </body>
</html>