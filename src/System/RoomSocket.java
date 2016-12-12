package system;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import abstract_class.SessionManager;

@ServerEndpoint(value = "/wsdemo/{room-descriptor}")
public class RoomSocket {

	SessionManager manager = SocketSessionManager.getInstance((j, msg, s) -> {
		String[] parseMsg = msg.split(",");
		String sendmsg;
		if (parseMsg[0].equals("join")) {
			sendmsg = j.addPlayer(s.getId());
			return sendmsg;
		} else if (parseMsg[0].equals("close")) {
			sendmsg = j.removePlayer(s.getId());
			return sendmsg;
		}
		sendmsg = j.judgment(parseMsg[1], s.getId());
		return sendmsg;
	});

	@OnOpen
	public void onOpen(@PathParam("room-descriptor") final String room,
			final Session session) {
		manager.arbitration(room, session);
	}

	@OnClose
	public void onClose(@PathParam("room-descriptor") final String room,
			final Session session) {
		manager.removeSession(room, session);
	}

	@OnMessage
	public void onMessage(@PathParam("room-descriptor") final String room,
			Session session, final String msg) {
		manager.arbitration(room, session, msg);
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println("error : " + session.getId() + ", "
				+ cause.getMessage());
		cause.getStackTrace();
	}
}