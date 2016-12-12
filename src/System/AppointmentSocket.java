package system;

import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import abstract_class.SessionManager;

@ServerEndpoint("/appo")
public class AppointmentSocket {
	SessionManager manager = SocketSessionManager.getInstance();

	@OnOpen
	public void onOpen(final Session session) {
		manager.getSessionSum(session);
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println("error : " + session.getId() + ", "
				+ cause.getMessage());
		cause.getStackTrace();
	}
}
