package abstract_class;

import java.util.List;

import javax.websocket.Session;

@FunctionalInterface
public interface MessageParse {
	public void msgparse(Judge j, String msg, Session s, List<Session> sessions);
}
