package System;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.Session;

public class WebSocketSessionManager {
	private final ConcurrentMap<String, Lock>          locks    = new ConcurrentHashMap<>();
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

	 public void addSession(final String pRoomDescriptor, final Session pSession) {
	        synchronized (this.getLock(pRoomDescriptor)) {
	            this.sessions.get(pRoomDescriptor).add(pSession);
	        }
	    }

	    public List<Session> getSessions(final String pRoomDescriptor) {
	        return this.sessions.get(pRoomDescriptor);
	    }

	    public void removeSession(final String pRoomDescriptor, final Session pSession) {
	        synchronized (this.getLock(pRoomDescriptor)) {
	            this.sessions.get(pRoomDescriptor).remove(pSession);
	        }
	    }

	    private Lock getLock(final String pRoomDescriptor) {
	        final Lock newLock = new Lock();
	        final Lock alreadyLock = this.locks.putIfAbsent(pRoomDescriptor, newLock);
	        return alreadyLock == null ? newLock : alreadyLock;
	    }

	    private static class Lock {
	        // nodef
	    }
}
