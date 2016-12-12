$(function() {

	var url = 'ws://'+location.host+'/Shiritori_Project/appo';
	var ws = new WebSocket(url);

	ws.onmessage = function(receive) {
		console.log(receive.data);
		var array = $.parseJSON(receive.data);
		for(key in array){
			$("#"+key).append(":参加人数"+array[key])
			console.log(key+":"+array[key])
		}
	};

	ws.onopen = function() {
	}

});