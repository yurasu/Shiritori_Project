package abstract_class;

import java.util.ArrayList;

import system.Player;

public abstract class Judge {
	protected ArrayList<String> log;
	protected ArrayList<Player> players;
	protected boolean start = false;

	public Judge() {
		log = new ArrayList<String>();
		players = new ArrayList<Player>();
	}

	public boolean isStart() {
		return this.start;
	}

	public void setStart() {
		start = true;
	}

	public void setStop() {
		start = false;
	}

	public abstract String judgment(String str, String session_id);

	public abstract String addPlayer(String session_id, String name);

	public abstract String removePlayer(String session_id);

	public int getPlayers_size() {
		return players.size();
	}

	public abstract String getPlayers();
}
