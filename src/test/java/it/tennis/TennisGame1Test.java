package it.tennis;
import static org.junit.Assert.*;

import org.junit.Test;

import it.tennis.TennisGame1;

public class TennisGame1Test {

    @Test
    public void givenANullPlayer1WhenCreatingANewGameThenAnExceptionIsRaised() {
        IllegalArgumentException actualException = null; 
        try {
            new TennisGame1(null , "some player");
        } catch (IllegalArgumentException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Invalid player: null", actualException.getMessage());
        }
    }
    
    @Test
    public void givenANullPlayer2WhenCreatingANewGameThenAnExceptionIsRaised() {
        IllegalArgumentException actualException = null; 
        try {
            new TennisGame1("some player", null);
        } catch (IllegalArgumentException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Invalid player: null", actualException.getMessage());
        }
    }
    
    @Test
    public void givenACoupleOfEqualPlayersWhenCreatingANewGameThenAnExceptionIsRaised() {
        IllegalArgumentException actualException = null; 
        try {
            new TennisGame1("same player", "same player");
        } catch (IllegalArgumentException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Identical players not allowed: same player - same player", actualException.getMessage());
        }
    }
    
    @Test
    public void givenATennisGameWhenAssigningAPointToAnUnexistingPlayerThenAnExceptionIsRaised() {
        final TennisGame1 gameUnderTest = new TennisGame1("first player", "second player");
        
        IllegalArgumentException actualException = null; 
        try {
            gameUnderTest.wonPoint("third player");
        } catch (IllegalArgumentException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Invalid player: third player", actualException.getMessage());
        }
    }
    
    @Test
    public void givenSomeRandomPlayerNamesWhenUsingAGameThenYouCanAssignPointsToPlayers() {
        
        final TennisGame1 gameUnderTest1 = new TennisGame1("first", "second");
        final TennisGame1 gameUnderTest2 = new TennisGame1("third", "fourth");
        
        gameUnderTest1.wonPoint("first");
        assertEquals( "Fifteen-Love", gameUnderTest1.getScore() );
        
        gameUnderTest1.wonPoint("second");
        assertEquals( "Fifteen-All", gameUnderTest1.getScore() );
        
        gameUnderTest2.wonPoint("fourth");
        assertEquals( "Love-Fifteen", gameUnderTest2.getScore() );
        
        gameUnderTest2.wonPoint("third");
        assertEquals( "Fifteen-All", gameUnderTest2.getScore() );
        
    }
    
}
