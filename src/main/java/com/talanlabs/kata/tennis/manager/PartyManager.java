package com.talanlabs.kata.tennis.manager;

import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;

public class PartyManager { // instancier dans app
	
	public static final int LIMIT_POINT_TO_WIN = 3;

	public int convertPointToScore(int i) throws InputScoreException {
		int score;
		switch(i) {
			case 0 : 
				score = 0;
				break;
			case 1 : 
				score = 15;
				break;
			case 2 : 
				score = 30;
				break;
			case 3 : 
				score = 40;
				break;
			default : 
				throw new InputScoreException();
		}
		return score;
	}
	
	public int getIndexOfPlayer(Game game, Player player) throws NullInputException, UnknownPlayerException {
		int index;
		if(game!=null && player!=null) {
			if(player== game.getPlayer1()) {
				index = 1;
			}else if(player== game.getPlayer2()) {
				index = 2;
			}else {
				throw new UnknownPlayerException();
			}
		}else {
			throw new NullInputException();
		}
		return index;
	}
	
	public int getPointOfPlayer(Game game, Player player) throws NullInputException, UnknownPlayerException {
		int point = 0;
		if(1 == getIndexOfPlayer(game, player)) {
			point = game.getPointPlayer1();
		}else if(2 == getIndexOfPlayer(game, player)) {
			point = game.getPointPlayer2();
		}
		return point;
	}

	public void addPointToPlayer(Game game, Player player) throws NullInputException, UnknownPlayerException {
		if(game!=null) {
			if(game.getWinner()==null) {
				if(getPointOfPlayer(game, player)==LIMIT_POINT_TO_WIN) {
					game.setWinner(player);
					game.setPointPlayer1(0);
					game.setPointPlayer2(0);
				}else {
					if(1 == getIndexOfPlayer(game, player)) {
						game.setPointPlayer1(game.getPointPlayer1() + 1 );
					}else if(2 == getIndexOfPlayer(game, player)) {
						game.setPointPlayer2(game.getPointPlayer2() + 1 );
					}
				}
			}
		}else {
			throw new NullInputException();
		}
	}

}
