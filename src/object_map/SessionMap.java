package object_map;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.Session;

public class SessionMap extends ConcurrentHashMap<String, List<Session>>{

	@Override
	public List<Session> get(final Object key) {
		List<Session> ret = super.get(key);
		if (ret == null) {
			ret = new CopyOnWriteArrayList<>();
			this.put((String) key, ret);
		}
		return ret;
	}
}
