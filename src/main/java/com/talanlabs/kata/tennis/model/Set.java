package com.talanlabs.kata.tennis.model;

public class Set {
	
	protected Player player1;
	protected Player player2;
	
	protected Game game;
	
	protected int pointPlayer1;
	protected int pointPlayer2;
	
	protected Player winner;

	public Set(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		pointPlayer1 = 0;
		pointPlayer2 = 0;
		game = new Game();
		winner = null;
	}

	public int getPointPlayer1() {
		return pointPlayer1;
	}
	
	public void setPointPlayer1(int point) {
		pointPlayer1 = point;
	}

	public int getPointPlayer2() {
		return pointPlayer2;
	}
	
	public void setPointPlayer2(int point) {
		pointPlayer2 = point;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Game getGame() {
		return game;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	

}
