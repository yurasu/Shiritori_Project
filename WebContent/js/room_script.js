/**
 *
 */
$(function() {
	var dir = location.pathname.split("/");
	var url = 'ws://' + location.host + '/Shiritori_Project/wsdemo/'
			+ dir[dir.length - 1];
	var ws = new WebSocket(url);
	var join = false;

	var players = {};
	$(window).on('beforeunload', function() {

		if (join) {
			var str = "close," + $("#message").val();
			ws.send(str); // WebSocketを使いサーバにメッセージを送信
		}
	});

	ws.onmessage = function(receive) {

		var str = receive.data.split(",");

		if (str[0] == "join") {
			players[str[1]] = str[2];
			$('#' + str[2]).text(str[2]);
			$('#message').val(str[2] + "が" + str[3]);
			if (str[str.length-1] == "you") {
				join = true;
			}
		} else if (str[0] == "remark") {
			$('#' + players[str[1]]).balloon({
				contents:str[2]
			});
		} else if (str[0] == "close") {
			$('#message').val(players[str[1]] + "の" + str[2]);
		}

	};

	ws.onopen = function() {
		$('#message').val("しりとり");
	}

	$("#send").click(function(e) {
		var str = "remark," + $("#text").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

	$("#join").click(function(e) {
		if (join) {
			return;
		}
		var str = "join," + $("#message").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

});