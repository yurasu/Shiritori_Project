package system;

import java.util.ArrayList;

import abstract_class.Judge;

public class NormalJudge extends Judge {

	private int order;
	private int turn = 0;
	private final static String remark_msg = "remark,";
	private final static String system_msg = "system,";
	private final static String player_msg = "players";
	public NormalJudge() {
		super();
		log.add("しりとり");
		order = 0;
	}

	@Override
	public String judgment(String str, String session_id) {
		if(!isStart()){
			return system_msg + "まだ始まっていません";
		}

		// TODO 自動生成されたメソッド・スタブ
		char c = str.charAt(str.length() - 1);
		Player p = get_player(session_id);
		String word = log.get(log.size() - 1);
		if (log.indexOf(str) != -1) {
			return system_msg+"その単語は発言しました";
		}else if(!isHIRAGANA(str)){
			return system_msg+"文字はひらがなのみです";
		} else if ('ん' == c) {
			return system_msg+"末尾が「ん」です";
		} else if (word.charAt(word.length() - 1) != str.charAt(0)) {
			return system_msg+"前の単語の末尾と違います。前の単語は、"+word+"です";
		} else if (p == null || order % players.size() != p.getTurn()) {
			return "そもそもお前の順番じゃない";
		}else if(str.length() > 15){
			return system_msg+"文字数は15文字までです";
		}
		logAdd(str);
		order++;
		return remark_msg+session_id+","+str+","+p.getName();
	}

	public Player get_player(String session_id) {
		int i = get_player_index(session_id);
		if(i == -1){
			return null;
		}
		return players.get(i);
	}

	public int get_player_index(String session_id) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getSessionID().equals(session_id)) {
				return i;
			}
		}
		return -1;
	}

	private boolean isHIRAGANA(String str){
		return str.matches("^[\\u3040-\\u309F]+$");
	}

	private void logAdd(String str) {
		log.add(str);
	}

	@Override
	public String addPlayer(String session_id, String name) {
		if(turn >= 4){
			return "現在4人参加しているので参加できません";
		}
		players.add(new Player(session_id, turn,name));
		turn++;
		return "join"+","+session_id+","+name+",参加しました。";
	}

	@Override
	public String removePlayer(String session_id) {
		Player p = get_player(session_id);
		if(p.getTurn() != turn){
			for(int i = get_player_index(session_id); i<players.size(); i++){
				players.get(i).setTurn(players.get(i).getTurn() -1);
			}

		}
		players.remove(get_player_index(session_id));
		turn--;
		if(turn == 0){
			log = new ArrayList<String>();
			log.add("しりとり");
			setStop();
		}
		return "close,"+session_id+",接続が切れました";
	}

	@Override
	public String getPlayers() {
		StringBuffer buff = new StringBuffer();
		if(getPlayers_size() == 0){
			return "";
		}
		for (Player p : players) {
			buff.append(","+p.name );
		}

		return player_msg+new String(buff);
	}

}
