package system;

import java.util.ArrayList;


public class Judge {
	private ArrayList<String> log;
	private ArrayList<Player> players;
	private int order;
	private int turn= 0;

	public Judge(int num){
		log = new ArrayList<String>();
		log.add("しりとり");
		players = new ArrayList<Player>();
		order = 0;
	}

	public String judgment(String str, int session_id){
		char c = str.charAt(str.length()-1);
		Player p = get_player(session_id);
		String word = log.get(log.size()-1);
		if(log.indexOf(str) != -1 ){
			return "その単語は発言しました";
		}else if('ん' == c){
			return "末尾が「ん」です";
		}else if(word.charAt(word.length()-1) != str.charAt(0)){
			return "前の単語の末尾と違います";
		}else if(p == null || order%players.size() != p.getTurn()){
			return "そもそもお前の順番じゃない";
		}
		logAdd(str);
		order++;
		return str;
	}

	public Player get_player(int session_id){
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getSessionID()==session_id){
				return players.get(i);
			}
		}
		return null;
	}

	public void player_add(int session_id){
		players.add(new Player(session_id, turn));
		turn++;
	}

	public void logAdd(String str){
		log.add(str);
	}

	public boolean wordIn(String word){
		if(log.indexOf(word) != -1){
			return false;
		}
		return true;
	}

}

