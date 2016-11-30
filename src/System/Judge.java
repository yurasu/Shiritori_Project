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

	public boolean judgment(String str, int id){
		char c = str.charAt(str.length()-1);
		if(log.indexOf(str) != -1 ){
			return false;
		}else if('ん' == c){
			return false;
		}else if(remark.charAt(remark.length()-1) != str.charAt(0)){
			return false;
		}else if(turn % 4 != id){
			return false;
		}
		remark = str;
		turn++;
		return true;
	}

	public void logAdd(){
		log.add(remark);
	}


}

