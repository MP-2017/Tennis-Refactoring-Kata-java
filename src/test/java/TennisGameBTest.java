import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameBTest {

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
        TennisGame1 gameUnderTest = new TennisGame1("first player", "second player");
        
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
    public void givenPlayer1WonWhenAssigningOneMorePointToPlayer1ThenAnExceptionIsRaised() {
        TennisGame1 gameUnderTest = new TennisGame1("winner", "loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        
        IllegalStateException actualException = null; 
        try {
            gameUnderTest.wonPoint("winner");
        } catch (IllegalStateException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Game over", actualException.getMessage());
        }
    }
    
    @Test
    public void givenPlayer2WonWhenAssigningOneMorePointToPlayer2ThenAnExceptionIsRaised() {
        TennisGame1 gameUnderTest = new TennisGame1("loser", "winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        
        IllegalStateException actualException = null; 
        try {
            gameUnderTest.wonPoint("winner");
        } catch (IllegalStateException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Game over", actualException.getMessage());
        }
    }
    
    @Test
    public void givenPlayer1WonWhenAssigningOneMorePointToThePlayer2ThenAnExceptionIsRaised() {
        TennisGame1 gameUnderTest = new TennisGame1("winner", "loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        
        IllegalStateException actualException = null; 
        try {
            gameUnderTest.wonPoint("loser");
        } catch (IllegalStateException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Game over", actualException.getMessage());
        }
    }
    
    @Test
    public void givenPlayer2WonWhenAssigningOneMorePointToPlayer1ThenAnExceptionIsRaised() {
        TennisGame1 gameUnderTest = new TennisGame1("loser", "winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("loser");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        gameUnderTest.wonPoint("winner");
        
        IllegalStateException actualException = null; 
        try {
            gameUnderTest.wonPoint("loser");
        } catch (IllegalStateException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Game over", actualException.getMessage());
        }
    }
}
