package system;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/wsdemo/{room-descriptor}")
public class TestSocket {
	public static WebSocketSessionManager sessionManager = new WebSocketSessionManager();

	@OnOpen
	public void onOpen(
			@PathParam("room-descriptor") final String pRoomDescriptor,
			final Session pSession) throws IOException, EncodeException {
		sessionManager.addSession(pRoomDescriptor, pSession);
		if (pRoomDescriptor.equals("appo")) {
			sendConnectnum(pSession);
		}
	}

	public void sendConnectnum(Session pSession) throws IOException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String s : sessionManager.getKeys()) {
			int connect = sessionManager.getSessions(s).size();
			map.put(s, connect);
		}
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(map);
		pSession.getBasicRemote().sendText(str);
	}

	@OnClose
	public void onClose(
			@PathParam("room-descriptor") final String pRoomDescriptor,
			final Session pSession) {
		sessionManager.removeSession(pRoomDescriptor, pSession);
	}

	@OnMessage
	public void onMessage(
			@PathParam("room-descriptor") final String pRoomDescriptor,
			final String pText, Session pSession) {
		String[] msg = pText.split(",");
		if(msg[0].equals("join")){
			sessionManager.joinPlayer(Integer.parseInt(pSession.getId()), pRoomDescriptor);
			return;
		}

		String str = sessionManager.wordJudge(pRoomDescriptor, msg[1],
				Integer.parseInt(pSession.getId()));
		for (final Session session : sessionManager
				.getSessions(pRoomDescriptor)) {
			try {
				session.getBasicRemote().sendText(str);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println("error : " + session.getId() + ", "
				+ cause.getMessage());
		cause.getStackTrace();
	}

}
