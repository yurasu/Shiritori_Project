package system;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.Session;

import object_map.JudgeMap;
import object_map.SessionMap;
import abstract_class.Judge;
import abstract_class.MessageParse;
import abstract_class.SessionManager;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SocketSessionManager implements SessionManager {

	private static SocketSessionManager manager = new SocketSessionManager();

	private SocketSessionManager() {
	}


	public static SocketSessionManager getInstance() {
		return manager;
	}

	public static SocketSessionManager getInstance(MessageParse f) {
		manager.f = f;
		return manager;
	}

	private MessageParse f;
	private final ConcurrentMap<String, Lock> locks = new ConcurrentHashMap<>();
	private ConcurrentMap<String, List<Session>> sessionMap = new SessionMap();
	private ConcurrentMap<String, Judge> judgeMap = new JudgeMap();

	@Override
	public void arbitration(String room, Session session) {
		synchronized (this.getLock(room)) {
			this.sessionMap.get(room).add(session);
		}
	}

	@Override
	public void arbitration(String room, Session session, String msg) {
		synchronized (this.getLock(room)) {
			f.msgparse(this.judgeMap.get(room), msg, session, this.sessionMap.get(room));
		}
	}

	@Override
	public void getSessionSum(Session session) {
		Set<String> roomSet = sessionMap.keySet();
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		for (String room : roomSet) {
			map.put(room, judgeMap.get(room).getPlayers());
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(map);
			session.getBasicRemote().sendText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String room, String sendmsg) {
		for (Session s : sessionMap.get(room)) {
			try {
				s.getBasicRemote().sendText(sendmsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Lock getLock(final String room) {
		final Lock newLock = new Lock();
		final Lock alreadyLock = this.locks.putIfAbsent(room, newLock);
		return alreadyLock == null ? newLock : alreadyLock;
	}

	private static class Lock {
		// nodef
	}

	@Override
	public void removeSession(String room, Session session) {
		sessionMap.get(room).remove(session);
	}



}
