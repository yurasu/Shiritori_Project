package abstract_class;

import javax.websocket.Session;

@FunctionalInterface
public interface MessageParse {
	public String msgparse(Judge j, String msg, Session s);
}
