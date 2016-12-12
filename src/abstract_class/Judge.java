package abstract_class;

import java.util.ArrayList;

import system.Player;

public abstract class Judge {
	protected ArrayList<String> log;
	protected ArrayList<Player> players;
	public Judge(){
		log = new ArrayList<String>();
		players = new ArrayList<Player>();
	}
	public abstract String judgment(String str, String session_id);

	public abstract String addPlayer(String session_id);

	public abstract String removePlayer(String session_id);

	public int getPlayers(){
		return players.size();
	}
}
