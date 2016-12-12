package abstract_class;

import javax.websocket.Session;

public interface SessionManager {
	public void arbitration(final String room,final Session session);
	public void arbitration(final String room,final Session ssession, final String msg);
	public void getSessionSum(Session session);
	public void removeSession(final String room,final Session session);
}
