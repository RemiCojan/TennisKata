package com.talanlabs.kata.tennis.manager;

import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;
import com.talanlabs.kata.tennis.model.Set;

public class PartyManager { // instancier dans app
	
	public static final int LIMIT_GAME_POINT_TO_WIN = 3;
	public static final int LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE= 4;
	
	public static final int LIMIT_SET_POINT_TO_WIN = 5;
	public static final int LIMIT_SET_POINT_TO_WIN_IN_TIGHT_CASE= 6;
	
	public String getGameScorePlayer(Set set, Player player) throws InputScoreException, NullInputException, UnknownPlayerException{
		return convertGamePointToGameScore(getGamePointOfPlayer(set, player), getGamePointOfPlayer(set, getOtherPlayer(set, player)));
	}

	private String convertGamePointToGameScore(int i, int j) throws InputScoreException {
		String score;
		if(j>=0 && j<=LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE && i+j<2*LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE) {  // les points vont de 0 à 4, et les deux joueurs ne peuvent pas avoir en total 8 points
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
	
	private boolean isFirstPlayer(Set set, Player player) throws NullInputException, UnknownPlayerException {
		boolean isFirstPlayer;
		if(set!=null && player!=null) {
			if(player== set.getPlayer1()) {
				isFirstPlayer = true;
			}else if(player== set.getPlayer2()) {
				isFirstPlayer = false;
			}else {
				throw new UnknownPlayerException();
			}
		}else {
			throw new NullInputException();
		}
		return isFirstPlayer;
	}
	
	public int getGamePointOfPlayer(Set set, Player player) throws NullInputException, UnknownPlayerException {
		int gamePoint = 0;
		if(set!=null && player!=null) {
			gamePoint = getGamePointOfPlayer(set.getGame(), isFirstPlayer(set, player));
		}else {
			throw new NullInputException();
		}
		return gamePoint;
	}
	
	private int getGamePointOfPlayer(Game game, boolean isFirstPlayer){
		return isFirstPlayer? game.getPointPlayer1() : game.getPointPlayer2();
	}
	
	public int getSetPointOfPlayer(Set set, Player player) throws NullInputException, UnknownPlayerException {
		return getSetPointOfPlayer(set, isFirstPlayer(set, player));
	}
	
	private int getSetPointOfPlayer(Set set, boolean isFirstPlayer){
		return isFirstPlayer? set.getPointPlayer1() : set.getPointPlayer2();
	}
	
	public Player getOtherPlayer(Set set, Player player) throws NullInputException, UnknownPlayerException {
		return getOtherPlayer(set, isFirstPlayer(set, player));
	}
	
	private Player getOtherPlayer(Set set, boolean isFirstPlayer){
		return isFirstPlayer? set.getPlayer2() : set.getPlayer1();
	}
	
	public void addPointToPlayerAndSetTheWinner(Set set, Player player) throws NullInputException, UnknownPlayerException {
		if(set!=null) {
			if(set.getWinner()==null) {
				if(addGamePointToPlayerAndReturnGameVictory(set.getGame(), isFirstPlayer(set, player))) {
					addSetPointToPlayerAndSetTheSetWinner(set, player, set.getPointPlayer1() + set.getPointPlayer2() > 9);
				}
			}
		}else {
			throw new NullInputException();
		}
	}

	private void addSetPointToPlayerAndSetTheSetWinner(Set set, Player player, boolean isTightSet) throws NullInputException, UnknownPlayerException {
		if((isTightSet && getSetPointOfPlayer(set, player)==LIMIT_SET_POINT_TO_WIN_IN_TIGHT_CASE) //
				|| (!isTightSet && getSetPointOfPlayer(set, player)==LIMIT_SET_POINT_TO_WIN)) {
			setSetVinner(player, set);
		}else {
			addSetPointToPlayer(set, isFirstPlayer(set,player));
		}
	}

	private boolean addGamePointToPlayerAndReturnGameVictory(Game game, boolean isFirstPlayer) throws NullInputException, UnknownPlayerException{
		boolean isWinningPoint;
		if(game.getPointPlayer1() + game.getPointPlayer2() > 5) {
			isWinningPoint = addPointToPlayerAndReturnGameVictoryWithDeuce(game, isFirstPlayer);
		}else {
			isWinningPoint = addPointToPlayerAndReturnGameVictoryClassic(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private boolean addPointToPlayerAndReturnGameVictoryClassic(Game game, boolean isFirstPlayer) throws NullInputException, UnknownPlayerException{
		boolean isWinningPoint= false;
		if(getGamePointOfPlayer(game, isFirstPlayer)==LIMIT_GAME_POINT_TO_WIN) {
			reinitGame(game);
			isWinningPoint = true;
		}else {
			addGamePointToPlayer(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private boolean addPointToPlayerAndReturnGameVictoryWithDeuce(Game game, boolean isFirstPlayer) throws NullInputException, UnknownPlayerException {
		boolean isWinningPoint= false;
		if(getGamePointOfPlayer(game, isFirstPlayer)==LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE) {
			reinitGame(game);
			isWinningPoint = true;
		}else if(game.getPointPlayer1()+game.getPointPlayer2()>6){
			game.setPointPlayer1(3);
			game.setPointPlayer2(3);
		}else {
			addGamePointToPlayer(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private void reinitGame(Game game) throws NullInputException {
		if(game!=null) {
			game.setPointPlayer1(0);
			game.setPointPlayer2(0);
		}else {
			throw new NullInputException();
		}
	}
	
	private void setSetVinner(Player player, Set set) throws NullInputException {
		if(set!=null) {
			set.setWinner(player);
			set.setPointPlayer1(0);
			set.setPointPlayer2(0);
		}else {
			throw new NullInputException();
		}
	}
	
	private void addGamePointToPlayer(Game game, boolean isFirstPlayer) throws NullInputException{
		if(game!=null) {
			if(isFirstPlayer) {
				game.setPointPlayer1(game.getPointPlayer1() + 1 );
			}else{
				game.setPointPlayer2(game.getPointPlayer2() + 1 );
			}
		}else {
			throw new NullInputException();
		}
	}
	
	private void addSetPointToPlayer(Set set, boolean isFirstPlayer) throws NullInputException{
		if(set!=null) {
			if(isFirstPlayer) {
				set.setPointPlayer1(set.getPointPlayer1() + 1 );
			}else{
				set.setPointPlayer2(set.getPointPlayer2() + 1 );
			}
		}else {
			throw new NullInputException();
		}
	}
}
