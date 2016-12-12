package system;

public class Player {
	public int life;
	public String sessionID;
	public boolean dead;
	public int turn;
	public Player(String sessionID, int turn){
		this.sessionID = sessionID;
		this.dead = false;
		this.life = 5;
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

}
