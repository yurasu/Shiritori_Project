package System;

import java.util.ArrayList;


public class Judge {
	private ArrayList<String> log;
	private String remark;
	private int turn;

	public Judge(){
		log = new ArrayList<String>();
		log.add("しりとり");
		remark = "しりとり";
		turn = 0;
	}

	public String judgment(String str){
		char c = str.charAt(str.length()-1);
		if(log.indexOf(str) != -1 ){
			return "その単語は発言しました";
		}else if('ん' == c){
			return "末尾が「ん」です";
		}else if(remark.charAt(remark.length()-1) != str.charAt(0)){
			return "前の単語の末尾と違います";
		}
		logAdd();
		remark = str;
		turn++;
		return str;
	}

	public void logAdd(){
		log.add(remark);
	}

	public boolean wordIn(String word){
		if(log.indexOf(word) != -1){
			return false;
		}
		return true;
	}

}

