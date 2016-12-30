package system;

public class Player {
	public int life;
	public String sessionID;
	public boolean dead;
	public int turn;
	public String name;
	public Player(String sessionID, int turn, String name){
		this.sessionID = sessionID;
		this.dead = false;
		this.life = 5;
		this.turn = turn;
		this.name = name;
	}

	public void setTurn(int turn){
		this.turn = turn;
	}

	public boolean isDead(){
		return dead;
	}

	public String getSessionID(){
		return sessionID;
	}

	public int getTurn(){
		return turn;
	}

	public String getName(){
		return this.name;
	}

}
