package com.talanlabs.kata.tennis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.talanlabs.kata.tennis.manager.IncorrectPlayersInputException;
import com.talanlabs.kata.tennis.manager.InputScoreException;
import com.talanlabs.kata.tennis.manager.NullInputException;
import com.talanlabs.kata.tennis.manager.UnknownPlayerException;
import com.talanlabs.kata.tennis.model.Player;

public class AppTest {
	
	//test starting the App
	
	@Test
	public void testAppStart() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException {
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		// verification que les scores sont bien à 0, que aucun set n'est marqué comme gagné et qu'il n'y a pas de vainqueur déclaré
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
        assertEquals(0,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test(expected = NullInputException.class )
	public void testAppStartErrorNullCase1() throws NullInputException, IncorrectPlayersInputException{
		Player player1 = new Player("P1");
		new App(player1, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testAppStartErrorNullCase2() throws NullInputException, IncorrectPlayersInputException{
		Player player2 = new Player("P2");
		new App(null, player2);
	}
	
	@Test(expected = NullInputException.class )
	public void testAppStartErrorNullCase3() throws NullInputException, IncorrectPlayersInputException{
		new App(null, null);
	}
	
	@Test(expected = IncorrectPlayersInputException.class )
	public void testAppStartErrorSamePlayerCase() throws IncorrectPlayersInputException, NullInputException{
		Player player1 = new Player("P1");
		new App(player1, player1);
	}
	
	// test getGameScorePlayer
	
	@Test
	public void testGetGameScorePlayerTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(2);
		
		assertEquals("15",app.getGameScorePlayer(player1));
        assertEquals("30",app.getGameScorePlayer(player2));
	}
	
	@Test
	public void testGetGameScorePlayerTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		assertEquals("40",app.getGameScorePlayer(player1));
		assertEquals("15",app.getGameScorePlayer(player2));
	}
	
	@Test(expected = NullInputException.class )
	public void testGetGameScorePlayerErrorNullCase() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		app.getGameScorePlayer(null);
	}
	
	//  test getGameScorePlayer with deuce rules
	
	@Test
	public void testGetGameScorePlayerDeuceTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(3);
		
		assertEquals("DEUCE",app.getGameScorePlayer(player1));
		assertEquals("DEUCE",app.getGameScorePlayer(player2));
	}
	
	@Test
	public void testGetGameScorePlayerDeuceTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		
		assertEquals("ADV",app.getGameScorePlayer(player1));
		assertEquals("40",app.getGameScorePlayer(player2));
	}
	
	// test getSetScorePlayer
	
	@Test
	public void testGetSetScorePlayerTestCase1() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getSet().setPointPlayer1(1);
		app.getSet().setPointPlayer2(3);
		
		assertEquals(1,app.getSetScorePlayer(player1));
        assertEquals(3,app.getSetScorePlayer(player2));
	}
	
	@Test
	public void testGetSetScorePlayerTestCase2() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getSet().setPointPlayer1(4);
		app.getSet().setPointPlayer2(5);
		
		assertEquals(4,app.getSetScorePlayer(player1));
		assertEquals(5,app.getSetScorePlayer(player2));
	}
	
	@Test(expected = NullInputException.class )
	public void testGetSetScorePlayerErrorNullCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getSet().setPointPlayer1(3);
		app.getSet().setPointPlayer2(1);
		
		app.getSetScorePlayer(null);
	}
	
	// test addPointPlayer out deuce rule case
	
	@Test
	public void testAddPointPlayerTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(player1);
		
		assertEquals("30",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(1,app.getSetScorePlayer(player2));
	}
	
	@Test
	public void testAddPointPlayerTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(2);
		app.getSet().setPointPlayer1(3);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(player2);
		
		assertEquals("15",app.getGameScorePlayer(player1));
        assertEquals("40",app.getGameScorePlayer(player2));
        assertEquals(3,app.getSetScorePlayer(player1));
	    assertEquals(1,app.getSetScorePlayer(player2));
	}
	
	@Test
	public void testAddPointPlayerTestCase3() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(2);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(4);
		
		app.addPointPlayer(player2);
		
		assertEquals("15",app.getGameScorePlayer(player1));
        assertEquals("40",app.getGameScorePlayer(player2));
        assertEquals(2,app.getSetScorePlayer(player1));
	    assertEquals(4,app.getSetScorePlayer(player2));
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointPlayerErrorNullCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(4);
		
		app.addPointPlayer(null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointPlayerErrorThirdPlayerCase() throws UnknownPlayerException, NullInputException, IncorrectPlayersInputException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(4);
		
		app.addPointPlayer(player3);
	}
	
	// test addPointPlayer when a player win a game out deuce rule case
	
	@Test
	public void testAddPointPlayerAndWinGameTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(2);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(1,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerAndWinGameTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(player1);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(3,app.getSetScorePlayer(player1));
	    assertEquals(1,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}

	// test addPointPlayer in deuce rule case
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(2);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player1);
		
		assertEquals("DEUCE",app.getGameScorePlayer(player1));
        assertEquals("DEUCE",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(0,app.getSetScorePlayer(player2));
	    assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(player2);
		
		assertEquals("40",app.getGameScorePlayer(player1));
        assertEquals("ADV",app.getGameScorePlayer(player2));
        assertEquals(2,app.getSetScorePlayer(player1));
	    assertEquals(1,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase3() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		app.getSet().setPointPlayer1(0);
		app.getSet().setPointPlayer2(3);
		
		app.addPointPlayer(player1);
		
		assertEquals("DEUCE",app.getGameScorePlayer(player1));
        assertEquals("DEUCE",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(3,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointPlayerWithDeuceErrorNullCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointPlayerWithDeuceErrorThirdPlayerCase() throws UnknownPlayerException, NullInputException, IncorrectPlayersInputException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(0);
		app.getSet().setPointPlayer2(3);
		
		app.addPointPlayer(player3);
	}
	
	
	// test addPointPlayer when a player win a game in deuce rule case
	
	@Test
	public void testAddPointPlayerAndWinWithDeuce() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(1);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(2,app.getSetScorePlayer(player1));
	    assertEquals(2,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	// test addPointPlayer when a player win a set with two "set point" ahead
	
	@Test
	public void testAddPointPlayerAndWinSetTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(2);
		app.getSet().setPointPlayer2(5);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(0,app.getSetScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(player2,app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerAndWinSetTestCase2() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(5);
		app.getSet().setPointPlayer2(4);
		
		app.addPointPlayer(player1);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(0,app.getSetScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(player1,app.getWinner());
	}
	
	// test addPointPlayer for the end of a set without two "set point" ahead
	
	@Test
	public void testAddPointPlayerTestCase4() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(5);
		app.getSet().setPointPlayer2(5);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(5,app.getSetScorePlayer(player1));
	    assertEquals(6,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerTestCase5() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(5);
		app.getSet().setPointPlayer2(6);
		
		app.addPointPlayer(player1);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(6,app.getSetScorePlayer(player1));
	    assertEquals(6,app.getSetScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerAndWinSetTestCase3() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(2);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(5);
		app.getSet().setPointPlayer2(6);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(0,app.getSetScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(player2,app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerAndWinSetTestCase4() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		app.getSet().setPointPlayer1(6);
		app.getSet().setPointPlayer2(6);
		
		app.addPointPlayer(player1);
		
		assertEquals("0",app.getGameScorePlayer(player1));
        assertEquals("0",app.getGameScorePlayer(player2));
        assertEquals(0,app.getSetScorePlayer(player1));
	    assertEquals(0,app.getSetScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(player1,app.getWinner());
	}
}
