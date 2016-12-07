$(function() {

	var url = 'ws://localhost:8080/Shiritori_Project/wsdemo/appo';
	var ws = new WebSocket(url);

	ws.onmessage = function(receive) {
		console.log(receive.data);
		var array = $.parseJSON(receive.data);
		for(key in array){
			$("#"+key).append(":接続人数"+array[key])
			console.log(key+":"+array[key])
		}
	};

	ws.onopen = function() {
	}

});