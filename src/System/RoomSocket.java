package system;

import java.io.IOException;
import java.util.List;

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

	SessionManager manager = SocketSessionManager.getInstance((j, msg, s, list) -> {
		String[] parseMsg = msg.split(",");
		String sendmsg;
		if (parseMsg[0].equals("join")) {
			sendmsg = j.addPlayer(s.getId());
			for (Session session : list) {
				try {
					if(session.getId().equals(s.getId())){
						session.getBasicRemote().sendText(sendmsg+",you");
						continue;
					}
					session.getBasicRemote().sendText(sendmsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		} else if (parseMsg[0].equals("close")) {
			sendmsg = j.removePlayer(s.getId());
			sendMsg(sendmsg,list);
			return;
		}
		sendmsg = j.judgment(parseMsg[1], s.getId());
		sendMsg(sendmsg,list);
	});

	public void sendMsg(String sendmsg, List<Session> sessions) {
		for (Session s : sessions) {
			try {
				s.getBasicRemote().sendText(sendmsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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