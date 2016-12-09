/**
 *
 */
$(function() {
	var dir = location.pathname.split("/");

	var url = 'ws://'+location.host+'/Shiritori_Project/wsdemo/'+ dir[dir.length-1];
	var ws = new WebSocket(url);

	ws.onmessage = function(receive) {
		$('#msgbox').append(receive.data);
	};

	ws.onopen = function(){
		$('#msgbox').append("しりとり");
	}

	$("#send").click(function(e) {
		var str = "remark,"+$("#message").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

	$("#join").click(function(e) {
		var str = "join,"+$("#message").val();
		ws.send(str); // WebSocketを使いサーバにメッセージを送信
	});

});