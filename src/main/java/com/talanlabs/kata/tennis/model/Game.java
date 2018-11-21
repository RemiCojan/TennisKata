package com.talanlabs.kata.tennis.model;

public class Game {

	protected int pointPlayer1;
	protected int pointPlayer2;

	public Game() {
		super();
		pointPlayer1 = 0;
		pointPlayer2 = 0;
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
}
