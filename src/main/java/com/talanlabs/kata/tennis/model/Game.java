package com.talanlabs.kata.tennis.model;

public class Game {
	
	protected Player player1;
	protected Player player2;
	
	protected int pointPlayer1;
	protected int pointPlayer2;
	
	protected Player winner;

	public Game(Player p1, Player p2) {
		super();
		player1 = p1;
		player2 = p2;
		pointPlayer1 = 0;
		pointPlayer2 = 0;
		winner = null;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
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
}
