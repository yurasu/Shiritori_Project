/**
 *
 */
$(function() {
	var dir = location.pathname.split("/");
	var url = 'ws://' + location.host + '/Shiritori_Project/wsdemo/'
			+ dir[dir.length - 1];
	var ws = new WebSocket(url);
	var join = false;
	$(window).on('beforeunload', function() {

		if (join) {
			var str = "close," + $("#message").val();
			ws.send(str); // WebSocketを使いサーバにメッセージを送信
		}
	});
	ws.onmessage = function(receive) {
		if (receive.data == "参加しました。") {
			join = true;
		}
		$('#msgbox').append(receive.data);
	};

	ws.onopen = function() {
		$('#msgbox').append("しりとり");
	}

	$("#send").click(function(e) {
		var str = "remark," + $("#message").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

	$("#join").click(function(e) {
		var str = "join," + $("#message").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

});