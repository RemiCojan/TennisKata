package com.talanlabs.kata.tennis.manager;

import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;

public class PartyManager { // instancier dans app
	
	public static final int LIMIT_POINT_TO_WIN = 3;
	public static final int LIMIT_POINT_TO_WIN_WITH_DEUCE= 4;

	public String convertPointToScore(int i, int j) throws InputScoreException {
		String score;
		if(j>=0 && j<=LIMIT_POINT_TO_WIN_WITH_DEUCE && i+j<2*LIMIT_POINT_TO_WIN_WITH_DEUCE) {  // les points vont de 0 à 4, et les deux joueurs ne peuvent pas avoir en total 8 points
			switch(i) {
			case 0 : 
				score = "0";
				break;
			case 1 : 
				score = "15";
				break;
			case 2 : 
				score = "30";
				break;
			case 3 : 
				score = j==3 ? "DEUCE" : "40";
				break;
			case 4 : 
				score = "ADV";
				break;
			default : 
				throw new InputScoreException();
			}
		}else{
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
	
	public Player getOtherPlayer(Game game, Player player) throws NullInputException, UnknownPlayerException {
		Player otherPlayer = null;
		if(1 == getIndexOfPlayer(game, player)) {
			otherPlayer = game.getPlayer2();
		}else if(2 == getIndexOfPlayer(game, player)) {
			otherPlayer = game.getPlayer1();
		}else {
			throw new UnknownPlayerException();
		}
		return otherPlayer;
	}

	public void addPointToPlayerAndSetTheWinner(Game game, Player player) throws NullInputException, UnknownPlayerException {
		if(game!=null) {
			if(game.getWinner()==null) {
				if(game.getPointPlayer1() + game.getPointPlayer2() > 5) {
					addPointToPlayerAndSetTheWinnerWithDeuce(game, player);
				}else {
					addPointToPlayerAndSetTheWinnerClassic(game, player);
				}
			}
		}else {
			throw new NullInputException();
		}
	}
	
	private void addPointToPlayerAndSetTheWinnerClassic(Game game, Player player) throws NullInputException, UnknownPlayerException {
		if(getPointOfPlayer(game, player)==LIMIT_POINT_TO_WIN) {
			setPlayerWinnerOfGame(player, game);
			game.setPointPlayer1(0);
			game.setPointPlayer2(0);
		}else {
			addPointToPlayer(game, player);
		}
	}
	
	private void addPointToPlayerAndSetTheWinnerWithDeuce(Game game, Player player) throws NullInputException, UnknownPlayerException {
		if(getPointOfPlayer(game, player)==LIMIT_POINT_TO_WIN_WITH_DEUCE) {
			setPlayerWinnerOfGame(player, game);
		}else if(getPointOfPlayer(game, getOtherPlayer(game, player))==LIMIT_POINT_TO_WIN_WITH_DEUCE){
			game.setPointPlayer1(3);
			game.setPointPlayer2(3);
		}else {
			addPointToPlayer(game, player);
		}
	}
	
	private void setPlayerWinnerOfGame(Player player, Game game) {
		game.setWinner(player);
		game.setPointPlayer1(0);
		game.setPointPlayer2(0);
	}
	
	private void addPointToPlayer(Game game, Player player) throws NullInputException, UnknownPlayerException {
		if(1 == getIndexOfPlayer(game, player)) {
			game.setPointPlayer1(game.getPointPlayer1() + 1 );
		}else if(2 == getIndexOfPlayer(game, player)) {
			game.setPointPlayer2(game.getPointPlayer2() + 1 );
		}
	}
}
