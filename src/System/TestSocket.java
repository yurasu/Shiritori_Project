package System;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wsdemo/{room-descriptor}")
public class TestSocket {
	public static WebSocketSessionManager sessionManager = new WebSocketSessionManager();

	@OnOpen
	public void onOpen(
			@PathParam("room-descriptor") final String pRoomDescriptor,
			final Session pSession) {
		sessionManager.addSession(pRoomDescriptor, pSession);
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
			final String pText) {
		String str =sessionManager.wordJudge(pRoomDescriptor, pText);
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
	}

}
