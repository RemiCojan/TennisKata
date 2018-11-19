package com.talanlabs.kata.tennis;

import com.talanlabs.kata.tennis.manager.IncorrectPlayersInputException;
import com.talanlabs.kata.tennis.manager.InputScoreException;
import com.talanlabs.kata.tennis.manager.NullInputException;
import com.talanlabs.kata.tennis.manager.PartyManager;
import com.talanlabs.kata.tennis.manager.UnknownPlayerException;
import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;

public class App {

	protected Game game;
	
	protected PartyManager partyManager;

	public App(Player player1, Player player2) throws NullInputException, IncorrectPlayersInputException{
		super();
		if(player1!=null && player2!=null) {
			if(player1!=player2) {
				game = new Game(player1, player2);
			}else {
				throw new IncorrectPlayersInputException();
			}
		}else {
			throw new NullInputException();
		}
	}
	
	protected PartyManager getPartyManager() {
		if(partyManager==null) {
			partyManager = new PartyManager();
		}
		return partyManager;
	}

	public Game getGame() {
		return game;
	}

	public Player getWinner() {
		return game.getWinner();
	}

	public int getScorePlayer(Player player) throws InputScoreException, NullInputException, UnknownPlayerException {
		return getPartyManager().convertPointToScore(getPartyManager().getPointOfPlayer(game, player));
	}
	
	public void addPointPlayer(Player player) throws NullInputException, UnknownPlayerException {
		getPartyManager().addPointToPlayer(game, player);
	}

}
