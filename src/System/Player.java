package system;

public class Player {
	public int life;
	public int sessionID;
	public boolean dead;
	public int turn;
	public Player(int sessionID, int turn){
		this.sessionID = sessionID;
		this.dead = false;
		this.life = 5;
		this.turn = turn;
	}

	public boolean isDead(){
		return dead;
	}

	public int getSessionID(){
		return sessionID;
	}

	public int getTurn(){
		return turn;
	}

}
