package com.talanlabs.kata.tennis.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.talanlabs.kata.tennis.manager.NullInputException;
import com.talanlabs.kata.tennis.manager.PartyManager;
import com.talanlabs.kata.tennis.manager.UnknownPlayerException;
import com.talanlabs.kata.tennis.model.Player;
import com.talanlabs.kata.tennis.model.Set;

public class PartyManagerTest {

	// test getGamePointOfPlayer
	
	@Test
	public void testGetGamePointOfPlayerTestCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Set set = new Set(player1, player2);
		
		set.getGame().setPointPlayer1(2);
		
		assertEquals(2, partyManager.getGamePointOfPlayer(set, player1));
		assertEquals(0, partyManager.getGamePointOfPlayer(set, player2));
	}
	
	@Test
	public void testGetGamePointOfPlayerTestCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Set set = new Set(player1, player2);
		
		set.getGame().setPointPlayer2(3);
		
		assertEquals(0, partyManager.getGamePointOfPlayer(set, player1));
		assertEquals(3, partyManager.getGamePointOfPlayer(set, player2));
	}

	@Test(expected = NullInputException.class )
	public void testGetGamePointOfPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.getGamePointOfPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetGamePointOfPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Set set = new Set(player1, player2);
		partyManager.getGamePointOfPlayer(set, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetGamePointOfPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		partyManager.getGamePointOfPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testGetGamePointOfPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Set set = new Set(player1, player2);
		partyManager.getGamePointOfPlayer(set, player3);
	}
	
	// test getSetPointOfPlayer
	
		@Test
		public void testGetSetPointOfPlayerTestCase1() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			Player player1 = new Player("P1");
			Player player2 = new Player("P2");
			Set set = new Set(player1, player2);
			
			set.setPointPlayer1(2);
			
			assertEquals(2, partyManager.getSetPointOfPlayer(set, player1));
			assertEquals(0, partyManager.getSetPointOfPlayer(set, player2));
		}
		
		@Test
		public void testGetSetPointOfPlayerTestCase2() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			Player player1 = new Player("P1");
			Player player2 = new Player("P2");
			Set set = new Set(player1, player2);
			
			set.setPointPlayer2(3);
			
			assertEquals(0, partyManager.getSetPointOfPlayer(set, player1));
			assertEquals(3, partyManager.getSetPointOfPlayer(set, player2));
		}

		@Test(expected = NullInputException.class )
		public void testGetSetPointOfPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			Player player1 = new Player("P1");
			partyManager.getSetPointOfPlayer(null, player1);
		}
		
		@Test(expected = NullInputException.class )
		public void testGetSetPointOfPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			Player player1 = new Player("P1");
			Player player2 = new Player("P2");
			Set set = new Set(player1, player2);
			partyManager.getSetPointOfPlayer(set, null);
		}
		
		@Test(expected = NullInputException.class )
		public void testGetSetPointOfPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			partyManager.getSetPointOfPlayer(null, null);
		}
		
		@Test(expected = UnknownPlayerException.class )
		public void testGetSetPointOfPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
			PartyManager partyManager = new PartyManager();
			Player player1 = new Player("P1");
			Player player2 = new Player("P2");
			Player player3 = new Player("P3");
			Set set = new Set(player1, player2);
			partyManager.getSetPointOfPlayer(set, player3);
		}
	
	// test getOtherPlayer
	
	@Test
	public void testGetOtherPlayerTestCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Set set = new Set(player1, player2);
		
		assertEquals(player1, partyManager.getOtherPlayer(set, player2));
		assertEquals(player2, partyManager.getOtherPlayer(set, player1));
	}

	@Test(expected = NullInputException.class )
	public void testGetOtherPlayerErrorNullCase1() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Set set = new Set(player1, player2);
		partyManager.getOtherPlayer(set, null);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetOtherPlayerErrorNullCase2() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		partyManager.getOtherPlayer(null, player1);
	}
	
	@Test(expected = NullInputException.class )
	public void testGetPointOfOtherPlayerErrorNullCase3() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		partyManager.getOtherPlayer(null, null);
	}
	
	@Test(expected = UnknownPlayerException.class )
	public void testGetPointOfOtherPlayerErrorThirdPlayerCase() throws NullInputException, UnknownPlayerException{
		PartyManager partyManager = new PartyManager();
		Player player1 = new Player("P1");
		Player player2 = new Player("P2");
		Player player3 = new Player("P3");
		Set set = new Set(player1, player2);
		partyManager.getOtherPlayer(set, player3);
	}
	
}
