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
			var msg = "close," + $("#message").val();
			ws.send(msg); // WebSocketを使いサーバにメッセージを送信
			ws.close();
		}
	});

	ws.onmessage = function(receive) {

		var array_data = receive.data.split(",");

		if (array_data[0] == "join") {
			players[array_data[1]] = array_data[2];
			var str = "<div class=\"player_info\">" + array_data[2] + "</div>";

			if (array_data[array_data.length - 1] == "you") {
				join = true;
				str = "<div class=\"player_info bg-success\">" + array_data[2]
						+ "</div>";
			}
			$("#players").append(str);

		} else if (array_data[0] == "remark") {

			var str = "<div class=\"player_msg\">"
					+ "<img src=\"../image/icon_h.jpg\" class=\"img-rounded img_player\">"
					+ "<div class=\"balloon\">" + array_data[2] + "</div>"
					+ "</div>";

			if (array_data[array_data.length - 1] == "you") {
				join = true;
				str = "<div class=\"playe_msg\">"
						+ "<img src=\"../image/icon_h.jpg\" class=\"img-rounded img_player\">"
						+ "<div class=\"balloon bg-success\">" + array_data[2]+ "</div>"
						+ "</div>";
			}
			$("#msgarea").append(str);

		} else if (array_data[0] == "close") {
			$('#message').val(players[array_data[1]] + "の" + array_data[2]);
		} else if (array_data[0] == "system") {
			var str = "<div class=\"system_msg\">" + array_data[1] + "</div>";
			$("#msgarea").append(str);
		}

	};

	ws.onopen = function() {
		$('#message').val("しりとり");
	}

	$("#send").click(function(e) {
		var msg = "remark," + $("#input-remark").val();
		ws.send(msg); // WebSocketを使いサーバにメッセージを送信
	});

	if ($("#modal-overlay")[0])
		$("#modal-overlay").remove();
	$("body").append('<div id="modal-overlay"></div>');
	$("#modal-overlay").fadeIn("slow");
	$("#modal").fadeIn("slow");

	$("#join").click(function(e) {
		$("#modal,#modal-overlay").fadeOut("slow", function() {
			// フェードアウト後、[#modal-overlay]をHTML(DOM)上から削除
			$("#modal-overlay").remove();
		});
		if (join) {
			return;
		}
		var msg = "join," + $("#message").val();
		ws.send(msg); // WebSocketを使いサーバにメッセージを送信
	});

	$("#start-btn").click(function(e) {
		var msg = "start,"
		ws.send(msg); // WebSocketを使いサーバにメッセージを送信
	});

});