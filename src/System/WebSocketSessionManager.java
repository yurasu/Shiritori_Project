package system;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.Session;

public class WebSocketSessionManager {
	private final ConcurrentMap<String, Lock> locks = new ConcurrentHashMap<>();
	private final ConcurrentMap<String, List<Session>> sessions = new ConcurrentHashMap<String, List<Session>>() {
		public List<Session> get(final Object key) {
			List<Session> ret = super.get(key);
			if (ret == null) {
				ret = new CopyOnWriteArrayList<>();
				this.put((String) key, ret);
			}
			return ret;
		}
	};

	private final ConcurrentMap<String, Judge> map = new ConcurrentHashMap<String, Judge>() {
		public Judge get(final Object key) {
			Judge ret = super.get(key);
			if (ret == null) {
				ret = new Judge(4);
				this.put((String) key, ret);
			}
			return ret;
		}
	};

	public void addSession(final String pRoomDescriptor, final Session pSession) {
		synchronized (this.getLock(pRoomDescriptor)) {
			this.sessions.get(pRoomDescriptor).add(pSession);
		}
	}

	public Set<String> getKeys(){
		return sessions.keySet();
	}

	public List<Session> getSessions(final String pRoomDescriptor) {
		return this.sessions.get(pRoomDescriptor);
	}

	public void removeSession(final String pRoomDescriptor,
			final Session pSession) {
		synchronized (this.getLock(pRoomDescriptor)) {
			this.sessions.get(pRoomDescriptor).remove(pSession);
		}
	}

	private Lock getLock(final String pRoomDescriptor) {
		final Lock newLock = new Lock();
		final Lock alreadyLock = this.locks.putIfAbsent(pRoomDescriptor,
				newLock);
		return alreadyLock == null ? newLock : alreadyLock;
	}

	public String wordJudge(final String pRoomDescriptor, final String word, int session_id) {
		synchronized (this.getLock(pRoomDescriptor)) {
			return this.map.get(pRoomDescriptor).judgment(word,session_id);
		}
	}

	public void joinPlayer(int session_id, final String pRoomDescriptor){
		synchronized (this.getLock(pRoomDescriptor)) {
			map.get(pRoomDescriptor).player_add(session_id);
		}
	}

	//使ってない
	public boolean wordIn(final String pRoomDescriptor, final String word) {
		synchronized (this.getLock(pRoomDescriptor)) {
			return this.map.get(pRoomDescriptor).wordIn(word);
		}
	}



	private static class Lock {
		// nodef
	}
}
