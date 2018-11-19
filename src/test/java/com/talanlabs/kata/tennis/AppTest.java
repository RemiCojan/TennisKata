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
	
	@Test
	public void testGameStart() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		// verification que les scores sont bien à 0 et qu'il n'y a pas de vainqueur déclaré
		assertEquals("0",app.getScorePlayer(player1));
        assertEquals("0",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test(expected = NullInputException.class )
	public void testGameStartErrorNullCase1() throws NullInputException, IncorrectPlayersInputException{
		Player player1 = new Player("P1");
		new App(player1, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGameStartErrorNullCase2() throws NullInputException, IncorrectPlayersInputException{
		Player player2 = new Player("P2");
		new App(null, player2);
	}
	
	@Test(expected = NullInputException.class )
	public void testGameStartErrorNullCase3() throws NullInputException, IncorrectPlayersInputException{
		new App(null, null);
	}
	
	@Test(expected = IncorrectPlayersInputException.class )
	public void testGameStartErrorSamePlayerCase() throws NullInputException, IncorrectPlayersInputException{
		Player player1 = new Player("P1");
		new App(player1, player1);
	}
	
	@Test
	public void testGetScorePlayerTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(2);
		
		assertEquals("15",app.getScorePlayer(player1));
        assertEquals("30",app.getScorePlayer(player2));
	}
	
	@Test
	public void testGetScorePlayerTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		assertEquals("40",app.getScorePlayer(player1));
	}
	
	@Test(expected = NullInputException.class )
	public void testGetScorePlayerErrorNullCase() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		app.getScorePlayer(null);
	}
	
	@Test
	public void testGetScorePlayerDeuceTestCase1() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(3);
		
		assertEquals("DEUCE",app.getScorePlayer(player1));
		assertEquals("DEUCE",app.getScorePlayer(player2));
	}
	
	@Test
	public void testGetScorePlayerDeuceTestCase2() throws NullInputException, IncorrectPlayersInputException, InputScoreException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);

		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		
		assertEquals("ADV",app.getScorePlayer(player1));
		assertEquals("40",app.getScorePlayer(player2));
	}
	
	@Test
	public void testAddPointPlayerTestCase1() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		
		app.addPointPlayer(player1);
		
		assertEquals("30",app.getScorePlayer(player1));
        assertEquals("0",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerTestCase2() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(1);
		app.getGame().setPointPlayer2(2);
		
		app.addPointPlayer(player2);
		
		assertEquals("15",app.getScorePlayer(player1));
        assertEquals("40",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerAndWinTestCase1() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(2);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getScorePlayer(player1));
        assertEquals("0",app.getScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(app.getWinner(), player2);
	}
	
	@Test
	public void testAddPointPlayerAndWinTestCase2() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		app.addPointPlayer(player1);
		
		assertEquals("0",app.getScorePlayer(player1));
        assertEquals("0",app.getScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(app.getWinner(), player1);
	}

	@Test(expected = NullInputException.class )
	public void testAddPointPlayerErrorNullCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		app.addPointPlayer(null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointPlayerErrorThirdPlayerCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(1);
		
		app.addPointPlayer(player3);
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase1() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(2);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player1);
		
		assertEquals("DEUCE",app.getScorePlayer(player1));
        assertEquals("DEUCE",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase2() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player2);
		
		assertEquals("40",app.getScorePlayer(player1));
        assertEquals("ADV",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase3() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		
		app.addPointPlayer(player2);
		
		assertEquals("0",app.getScorePlayer(player1));
        assertEquals("0",app.getScorePlayer(player2));
        assertNotNull(app.getWinner());
        assertEquals(app.getWinner(), player2);
	}
	
	@Test
	public void testAddPointPlayerWithDeuceTestCase4() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException, InputScoreException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		
		app.addPointPlayer(player1);
		
		assertEquals("DEUCE",app.getScorePlayer(player1));
        assertEquals("DEUCE",app.getScorePlayer(player2));
        assertNull(app.getWinner());
	}
	
	@Test(expected = NullInputException.class )
	public void testAddPointPlayerWithDeuceErrorNullCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(3);
		app.getGame().setPointPlayer2(4);
		
		app.addPointPlayer(null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testAddPointPlayerWithDeuceErrorThirdPlayerCase() throws NullInputException, IncorrectPlayersInputException, UnknownPlayerException{
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		App app = new App(player1, player2);
		
		app.getGame().setPointPlayer1(4);
		app.getGame().setPointPlayer2(3);
		
		app.addPointPlayer(player3);
	}
}
