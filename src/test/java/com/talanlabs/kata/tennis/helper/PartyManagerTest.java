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
		assertEquals("0",partyManager.convertPointToScore( 0, 0));
		assertEquals("15",partyManager.convertPointToScore( 1, 0));
		assertEquals("30",partyManager.convertPointToScore( 2, 0));
		assertEquals("40",partyManager.convertPointToScore( 3, 0));
    }
	
	@Test
	public void testConvertInScorePointWithDeuce() throws InputScoreException{
		PartyManager partyManager = new PartyManager();
		assertEquals("DEUCE",partyManager.convertPointToScore( 3, 3));
		assertEquals("40",partyManager.convertPointToScore( 3, 4));
		assertEquals("ADV",partyManager.convertPointToScore( 4, 3));
    }
	
	@Test(expected = InputScoreException.class )
	public void testConvertInScorePointErrorTestCase1() throws InputScoreException {
		PartyManager partyManager = new PartyManager();
		partyManager.convertPointToScore(-1, 1);
	}
	
	@Test(expected = InputScoreException.class )
	public void testConvertInScorePointErrorTestCase2() throws InputScoreException {
		PartyManager partyManager = new PartyManager();
		partyManager.convertPointToScore(1, -1);
	}
	
	@Test(expected = InputScoreException.class )
	public void testConvertInScorePointErrorTestCase3() throws InputScoreException {
		PartyManager partyManager = new PartyManager();
		partyManager.convertPointToScore(1, 5);
	}
	
	@Test(expected = InputScoreException.class )
	public void testConvertInScorePointErrorTestCase4() throws InputScoreException {
		PartyManager partyManager = new PartyManager();
		partyManager.convertPointToScore(5, 3);
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
	public void testGetIndexOfPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
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
	public void testGetPointPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.getPointOfPlayer(game, player3);
	}
	
	@Test
	public void testGetOtherPlayerTestCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		assertEquals(player1, partyManager.getOtherPlayer(game, player2));
		assertEquals(player2, partyManager.getOtherPlayer(game, player1));
	}

	@Test(expected = NullInputException.class )
	public void testGetOtherPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.getOtherPlayer(game, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetOtherPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.getOtherPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetPointOfOtherPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		partyManager.getOtherPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testGetPointOfOtherPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.getOtherPlayer(game, player3);
	}
	
	@Test
	public void testAddPointToPlayerTestCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player1);
		
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
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player1);
		
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
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player2);
		
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
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player2);
		
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
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player2);
		
		assertEquals(0, game.getPointPlayer1());
		assertEquals(0, game.getPointPlayer2());
		assertNotNull(game.getWinner());
        assertEquals(game.getWinner(), player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.addPointToPlayerAndSetTheWinner(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		partyManager.addPointToPlayerAndSetTheWinner(game, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		partyManager.addPointToPlayerAndSetTheWinner(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointToPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		partyManager.addPointToPlayerAndSetTheWinner(game, player3);
	}
	
	@Test
	public void testAddPointToPlayerWithDeuceTestCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(2);
		game.setPointPlayer2(3);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player1);
		
		assertEquals(3, game.getPointPlayer1());
		assertEquals(3, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerWithDeuceTestCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(3);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player1);
		
		assertEquals(4, game.getPointPlayer1());
		assertEquals(3, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerWithDeuceTestCase3() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(4);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player1);
		
		assertEquals(3, game.getPointPlayer1());
		assertEquals(3, game.getPointPlayer2());
		assertNull(game.getWinner());
	}
	
	@Test
	public void testAddPointToPlayerWithDeuceTestCase4() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(4);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, player2);
		
		assertEquals(0, game.getPointPlayer1());
		assertEquals(0, game.getPointPlayer2());
		assertNotNull(game.getWinner());
		assertEquals(game.getWinner(), player2);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerWithDeuceErrorNullCase1() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(3);
		
		partyManager.addPointToPlayerAndSetTheWinner(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerWithDeuceErrorNullCase2() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(4);
		
		partyManager.addPointToPlayerAndSetTheWinner(null, player2);
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointToPlayerWithDeuceErrorNullCase3() throws NullInputException, UnknownPlayerException {
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Game game = new Game(player1, player2);
		
		game.setPointPlayer1(3);
		game.setPointPlayer2(4);
		
		partyManager.addPointToPlayerAndSetTheWinner(game, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointToPlayerWithDeuceErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Game game = new Game(player1, player2);
		game.setPointPlayer1(3);
		game.setPointPlayer2(4);
		partyManager.addPointToPlayerAndSetTheWinner(game, player3);
	}
	
}
