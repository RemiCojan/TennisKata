package com.talanlabs.kata.tennis.manager;

import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;
import com.talanlabs.kata.tennis.model.Set;

public class PartyManager {
	
	public static final int LIMIT_GAME_POINT_TO_WIN = 3;
	public static final int LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE= 4;
	
	public static final int LIMIT_SET_POINT_TO_WIN = 5;
	public static final int LIMIT_SET_POINT_TO_WIN_IN_TIGHT_CASE= 6;
	
	public static final int TIE_BREAKER_STARTING_SET_POINT = 6;
	public static final int LIMIT_GAME_POINT_TO_WIN_WITH_TIE_BREAKER = 6;
	
	public String getGameScorePlayer(Set set, Player player) throws InputScoreException, NullInputException, UnknownPlayerException{
		return isTieBreakerGame(set)? 
				convertGamePointToTieBreakerGameScore(getGamePointOfPlayer(set, player))
				: convertGamePointToGameScore(getGamePointOfPlayer(set, player), getGamePointOfPlayer(set, getOtherPlayer(set, player)));
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
	

	private String convertGamePointToTieBreakerGameScore(int i) throws InputScoreException {
		if(i<0) {
			throw new InputScoreException();
		}
		return Integer.toString(i);
	}
	
	private boolean isTieBreakerGame(Set set) throws NullInputException {
		if(set==null) {
			throw new NullInputException();
		}
		return set.getPointPlayer1()==TIE_BREAKER_STARTING_SET_POINT && set.getPointPlayer2()==TIE_BREAKER_STARTING_SET_POINT;
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
				if(addGamePointToPlayerAndReturnGameVictory(set.getGame(), isFirstPlayer(set, player), isTieBreakerGame(set))) {
					addSetPointToPlayerAndSetTheSetWinner(set, player, isTightSet(set));
				}
			}
		}else {
			throw new NullInputException();
		}
	}

	private boolean isTightSet(Set set) throws NullInputException {
		if(set==null) {
			throw new NullInputException();
		}
		return set.getPointPlayer1() + set.getPointPlayer2() > 9; // on ne peut dépacer 9 que si les deux joueurs ont au moins 5.
	}

	private void addSetPointToPlayerAndSetTheSetWinner(Set set, Player player, boolean isTightSet) throws NullInputException, UnknownPlayerException {
		if(isWinningSetPointToPlayer(set ,player , isTightSet)) {
			setSetWinner(player, set);
		}else {
			addSetPointToPlayer(set, isFirstPlayer(set,player));
		}
	}
	
	private boolean isWinningSetPointToPlayer(Set set, Player player, boolean isTightSet) throws NullInputException, UnknownPlayerException {
		if(set==null || player==null) {
			throw new NullInputException();
		}
		return (isTightSet && getSetPointOfPlayer(set, player)==LIMIT_SET_POINT_TO_WIN_IN_TIGHT_CASE)
				|| (!isTightSet && getSetPointOfPlayer(set, player)==LIMIT_SET_POINT_TO_WIN);
	}

	private boolean addGamePointToPlayerAndReturnGameVictory(Game game, boolean isFirstPlayer, boolean isTieBreakerGame) throws NullInputException, UnknownPlayerException{
		boolean isWinningPoint;
		if(isTieBreakerGame) {
			isWinningPoint = addPointToPlayerAndReturnGameVictoryTieBreaker(game, isFirstPlayer);
		}else if(isDeuceCase(game)) {
			isWinningPoint = addPointToPlayerAndReturnGameVictoryWithDeuce(game, isFirstPlayer);
		}else {
			isWinningPoint = addPointToPlayerAndReturnGameVictoryClassic(game, isFirstPlayer);
		}
		return isWinningPoint;
	}

	private boolean isDeuceCase(Game game) throws NullInputException {
		if(game==null) {
			throw new NullInputException();
		}
		return game.getPointPlayer1() + game.getPointPlayer2() > 5; // on ne peut dépacer 5 point que si les deux joueurs ont au moins 3.
	}
	
	private boolean addPointToPlayerAndReturnGameVictoryClassic(Game game, boolean isFirstPlayer) throws NullInputException, UnknownPlayerException{
		boolean isWinningPoint= false;
		if(isWinningGamePointToPlayer(game, isFirstPlayer, false)) {
			reinitGame(game);
			isWinningPoint = true;
		}else {
			addGamePointToPlayer(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private boolean addPointToPlayerAndReturnGameVictoryWithDeuce(Game game, boolean isFirstPlayer) throws NullInputException, UnknownPlayerException {
		boolean isWinningPoint= false;
		if(isWinningGamePointToPlayer(game, isFirstPlayer, true)) {
			reinitGame(game);
			isWinningPoint = true;
		}else if(isDeuceGamePointReinitialisationNeeded(game)){
			game.setPointPlayer1(3);
			game.setPointPlayer2(3);
		}else {
			addGamePointToPlayer(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private boolean addPointToPlayerAndReturnGameVictoryTieBreaker(Game game, boolean isFirstPlayer) throws NullInputException {
		boolean isWinningPoint= false;
		if(isWinningGamePointToPlayerInTieBreakerCase(game, isFirstPlayer)) {
			reinitGame(game);
			isWinningPoint = true;
		}else {
			addGamePointToPlayer(game, isFirstPlayer);
		}
		return isWinningPoint;
	}
	
	private boolean isWinningGamePointToPlayerInTieBreakerCase(Game game, boolean isFirstPlayer) throws NullInputException {
		if(game==null) {
			throw new NullInputException();
		}
		return getGamePointOfPlayer(game, isFirstPlayer)>getGamePointOfPlayer(game, !isFirstPlayer) 
				&& getGamePointOfPlayer(game, isFirstPlayer)>=LIMIT_GAME_POINT_TO_WIN_WITH_TIE_BREAKER;
	}

	private boolean isWinningGamePointToPlayer(Game game, boolean isFirstPlayer, boolean isDeuce) throws NullInputException, UnknownPlayerException {
		if(game==null) {
			throw new NullInputException();
		}
		return isDeuce ? getGamePointOfPlayer(game, isFirstPlayer)==LIMIT_GAME_POINT_TO_WIN_WITH_DEUCE : getGamePointOfPlayer(game, isFirstPlayer)==LIMIT_GAME_POINT_TO_WIN;
	}
	
	private boolean isDeuceGamePointReinitialisationNeeded(Game game) throws NullInputException {
		if(game==null) {
			throw new NullInputException();
		}
		return game.getPointPlayer1()+game.getPointPlayer2()>6;
	}
	
	private void reinitGame(Game game) throws NullInputException {
		if(game!=null) {
			game.setPointPlayer1(0);
			game.setPointPlayer2(0);
		}else {
			throw new NullInputException();
		}
	}
	
	private void setSetWinner(Player player, Set set) throws NullInputException {
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
