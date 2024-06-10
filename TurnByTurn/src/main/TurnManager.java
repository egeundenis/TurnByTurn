package main;

public class TurnManager {

	int turn;
	
	TurnManager(){
		turn = 0;
	}
	
	void increaseTurn() {
		turn++;
	}
	
	void resetTurn() {
		turn = 0;
	}
	
	int getTurn() {
		return turn;
	}
	

}
