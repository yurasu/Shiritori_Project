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

	SessionManager manager = SocketSessionManager
			.getInstance((j, msg, s, list) -> {
				String[] parseMsg = msg.split(",");
				String sendmsg;
				if (parseMsg[0].equals("join")) {
					sendmsg = j.addPlayer(s.getId(), parseMsg[1]);
					sendMsg(sendmsg, list, s);
					return;
				} else if (parseMsg[0].equals("close")) {
					sendmsg = j.removePlayer(s.getId());
					sendMsg(sendmsg, list);
					return;
				} else if (parseMsg[0].equals("start")) {
					j.setStart();
					sendmsg = "system,しりとりを開始します";
					sendMsg(sendmsg, list);
					return;
				} else if (parseMsg[0].equals("open")) {
					sendmsg = j.getPlayers();
					sendMsg(sendmsg, s);
					return;
				}

				sendmsg = j.judgment(parseMsg[1], s.getId());
				if (sendmsg.split(",")[0].equals("system")) {
					sendMsg(sendmsg, s);
					return;
				}
				sendMsg(sendmsg, list);
			});

	public void sendMsg(String sendMsg, Session s) {
		try {
			s.getBasicRemote().sendText(sendMsg);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void sendMsg(String sendmsg, List<Session> sessions) {
		for (Session s : sessions) {
			try {
				s.getBasicRemote().sendText(sendmsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String sendmsg, List<Session> sessions, Session s) {
		for (Session session : sessions) {
			try {
				if (session.getId().equals(s.getId())) {
					session.getBasicRemote().sendText(sendmsg + ",you");
					continue;
				}
				session.getBasicRemote().sendText(sendmsg);
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
	public void onError(@PathParam("room-descriptor") final String room,Session session, Throwable cause) {
		System.out.println("error : " + session.getId() + ", "
				+ cause.getMessage());
		manager.removeSession(room, session);
		cause.getStackTrace();
	}
}