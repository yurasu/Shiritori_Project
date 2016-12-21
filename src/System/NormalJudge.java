package system;

import abstract_class.Judge;

public class NormalJudge extends Judge {

	private int order;
	private int turn = 0;

	public NormalJudge() {
		super();
		log.add("しりとり");
		order = 0;
	}

	@Override
	public String judgment(String str, String session_id) {
		// TODO 自動生成されたメソッド・スタブ
		char c = str.charAt(str.length() - 1);
		Player p = get_player(session_id);
		String word = log.get(log.size() - 1);
		if (log.indexOf(str) != -1) {
			return "その単語は発言しました";
		} else if ('ん' == c) {
			return "末尾が「ん」です";
		} else if (word.charAt(word.length() - 1) != str.charAt(0)) {
			return "前の単語の末尾と違います";
		} else if (p == null || order % players.size() != p.getTurn()) {
			return "そもそもお前の順番じゃない";
		}
		logAdd(str);
		order++;
		return "remark,"+session_id+","+str;
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

	private void logAdd(String str) {
		log.add(str);
	}

	@Override
	public String addPlayer(String session_id) {
		if(turn >= 4){
			return "現在4人参加しているので参加できません";
		}
		players.add(new Player(session_id, turn));
		turn++;
		return "join"+","+session_id+",player"+turn+",参加しました。";
	}

	@Override
	public String removePlayer(String session_id) {
		players.remove(get_player_index(session_id));
		return "close,"+session_id+",接続が切れました";
	}

}
