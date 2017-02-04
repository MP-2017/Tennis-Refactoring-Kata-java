import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGame1Test2 {

    @Test
    public void givenATennisGameWhenAssigningAPointToAnUnexistingPlayerThenAnExceptionIsRaised() {
        TennisGame1 game = new TennisGame1("first player", "second player");
        
        IllegalArgumentException actualException = null; 
        try {
            game.wonPoint("third player");
        } catch (IllegalArgumentException e) {
            actualException = e;
        } finally {
            assertNotNull(actualException);
            assertEquals("Invalid player name: third player", actualException.getMessage());
        }
    }

}
