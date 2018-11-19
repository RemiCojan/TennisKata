package com.talanlabs.kata.tennis.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.talanlabs.kata.tennis.manager.InputScoreException;
import com.talanlabs.kata.tennis.manager.NullInputException;
import com.talanlabs.kata.tennis.manager.PartyManager;
import com.talanlabs.kata.tennis.manager.UnknownPlayerException;
import com.talanlabs.kata.tennis.model.Game;
import com.talanlabs.kata.tennis.model.Player;

public class PartyManagerTest {
	
	@Test
	public void testConvertInScorePoint() throws InputScoreException{
		PartyManager partyManager = new PartyManager();
		assertEquals(0,partyManager.convertPointToScore(0));
		assertEquals(15,partyManager.convertPointToScore(1));
		assertEquals(30,partyManager.convertPointToScore(2));
		assertEquals(40,partyManager.convertPointToScore(3));
    }
	
	@Test(expected = InputScoreException.class )
	public void testConvertInScorePointError() throws InputScoreException {
		PartyManager partyManager = new PartyManager();
		partyManager.convertPointToScore(-1);
	}
	
	@Test
	public void testGetIndexOfPlayer1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		assertEquals(1, partyManager.getIndexOfPlayer(game, player1));
	}
	
	@Test
	public void testGetIndexOfPlayer2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		assertEquals(2, partyManager.getIndexOfPlayer(game, player2));
	}
	
	@Test(expected = NullInputException.class )
	public void testGetIndexOfPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.getIndexOfPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetIndexOfPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.getIndexOfPlayer(game, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetIndexOfPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		partyManager.getIndexOfPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testGetIndexOfPlayerErrorOtherPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.getIndexOfPlayer(game, player3);
	}
	
	@Test
	public void testGetPointPlayerTestCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(2);
		
		assertEquals(2, partyManager.getPointOfPlayer(game, player1));
		assertEquals(0, partyManager.getPointOfPlayer(game, player2));
	}
	
	@Test
	public void testGetPointPlayerTestCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer2(3);
		
		assertEquals(0, partyManager.getPointOfPlayer(game, player1));
		assertEquals(3, partyManager.getPointOfPlayer(game, player2));
	}

	@Test(expected = NullInputException.class )
	public void testGetPointPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.getPointOfPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetPointPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.getPointOfPlayer(game, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetPointPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		partyManager.getPointOfPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testGetPointPlayerErrorOtherPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.getPointOfPlayer(game, player3);
	}
	
	@Test
	public void testAddPointToPlayerTestCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		partyManager.addPointToPlayer(game, player1);
		
		assertEquals(1, game.getPointPlayer1());
		assertEquals(0, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerTestCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(2);
		game.setPointPlayer2(1);
		
		partyManager.addPointToPlayer(game, player1);
		
		assertEquals(3, game.getPointPlayer1());
		assertEquals(1, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerTestCase3() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer2(1);
		
		partyManager.addPointToPlayer(game, player2);
		
		assertEquals(0, game.getPointPlayer1());
		assertEquals(2, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerTestCase4() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(2);
		game.setPointPlayer2(3);
		
		partyManager.addPointToPlayer(game, player2);
		
		assertEquals(0, game.getPointPlayer1());
		assertEquals(0, game.getPointPlayer2());
		assertNotNull(game.getWinner());
        assertEquals(game.getWinner(), player2);
	}
	
	@Test
	public void testAddPointToPlayerTestCase5() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);

		game.setWinner(player1);
		
		partyManager.addPointToPlayer(game, player2);
		
		assertEquals(0, game.getPointPlayer1());
		assertEquals(0, game.getPointPlayer2());
		assertNotNull(game.getWinner());
        assertEquals(game.getWinner(), player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.addPointToPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.addPointToPlayer(game, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		partyManager.addPointToPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointToPlayerErrorOtherPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.addPointToPlayer(game, player3);
	}
	
}
