package com.talanlabs.kata.tennis;

import com.talanlabs.kata.tennis.manager.IncorrectPlayersInputException;
import com.talanlabs.kata.tennis.manager.InputScoreException;
import com.talanlabs.kata.tennis.manager.NullInputException;
import com.talanlabs.kata.tennis.manager.PartyManager;
import com.talanlabs.kata.tennis.manager.UnknownPlayerException;
import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;
import com.talanlabs.kata.tennis.model.Set;

public class App {

	protected Set set;
	
	protected PartyManager partyManager;

	public App(Player player1, Player player2) throws NullInputException, IncorrectPlayersInputException{
		super();
		if(player1!=null && player2!=null) {
			if(player1!=player2) {
				set = new Set(player1, player2);
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
	
	public Set getSet() {
		return set;
	}

	public Game getGame() {
		return set.getGame();
	}

	public Player getWinner() {
		return set.getWinner();
	}

	public String getGameScorePlayer(Player player) throws InputScoreException, NullInputException, UnknownPlayerException{
		return getPartyManager().getGameScorePlayer(set, player);
	}
	
	public int getSetScorePlayer(Player player) throws NullInputException, UnknownPlayerException {
		return getPartyManager().getSetPointOfPlayer(set, player);
	}
	
	public void addPointPlayer(Player player) throws NullInputException, UnknownPlayerException{
		getPartyManager().addPointToPlayerAndSetTheWinner(set, player);
	}
}
