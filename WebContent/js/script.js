/**
 *
 */
$(function() {
	var dir = location.pathname.split("/");

	var url = 'ws://localhost:8080/Shiritori_Project/wsdemo/'+ dir[dir.length-1];
	var ws = new WebSocket(url);

	ws.onmessage = function(receive) {
		$('#msgbox').append(receive.data);
	};

	ws.onopen = function(){
		$('#msgbox').append("しりとり");
	}

	$("#send").click(function(e) {
		ws.send($("#message").val()); // WebSocketを使いサーバにメッセージを送信
	});

});