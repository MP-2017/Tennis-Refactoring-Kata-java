package it.tennis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TennisGame1TestAllTransitions {

    private static Map<String, TennisGame1> gamesByScores;
    
    private final TennisGame1 gameUnderTest;
    private final String currentExpectedScore;
    private final String nextPointPlayer;
    private final String nextExpectedScore;

    public TennisGame1TestAllTransitions(String currentExpectedScore, String nextPointPlayer, String nextExpectedScore) {
        this.gameUnderTest = gamesByScores.get(currentExpectedScore).cloneAsTennisGame1();
        this.currentExpectedScore = currentExpectedScore;
        this.nextPointPlayer = nextPointPlayer;
        this.nextExpectedScore = nextExpectedScore;
    }
    
    @Parameters
    public static Collection<Object[]> getAllPossibleTransitions() {
        return Arrays.asList(new Object[][] {
            { "Love-All", "player one", "Fifteen-Love" },
            { "Love-All", "player two", "Love-Fifteen" },
            
            { "Fifteen-Love", "player one", "Thirty-Love" },
            { "Fifteen-Love", "player two", "Fifteen-All" },
            { "Love-Fifteen", "player one", "Fifteen-All" },
            { "Love-Fifteen", "player two", "Love-Thirty" },
            
            { "Thirty-Love",  "player one", "Forty-Love" },
            { "Thirty-Love",  "player two", "Thirty-Fifteen" },
            { "Fifteen-All",  "player one", "Thirty-Fifteen" },
            { "Fifteen-All",  "player two", "Fifteen-Thirty" },
            { "Love-Thirty",  "player one", "Fifteen-Thirty" },
            { "Love-Thirty",  "player two", "Love-Forty" },

            { "Forty-Love",     "player one", "Win for player one" },
            { "Forty-Love",     "player two", "Forty-Fifteen" },
            { "Thirty-Fifteen", "player one", "Forty-Fifteen" },
            { "Thirty-Fifteen", "player two", "Thirty-All" },
            { "Fifteen-Thirty", "player one", "Thirty-All" },
            { "Fifteen-Thirty", "player two", "Fifteen-Forty" },
            { "Love-Forty",     "player one", "Fifteen-Forty" },
            { "Love-Forty",     "player two", "Win for player two" },

            { "Forty-Fifteen","player one", "Win for player one" },
            { "Forty-Fifteen","player two", "Forty-Thirty" },
            { "Thirty-All",   "player one", "Forty-Thirty" },
            { "Thirty-All",   "player two", "Thirty-Forty" },
            { "Fifteen-Forty","player one", "Thirty-Forty" },
            { "Fifteen-Forty","player two", "Win for player two" },
            
            { "Forty-Thirty", "player one", "Win for player one" },
            { "Forty-Thirty", "player two", "Deuce" },
            { "Thirty-Forty", "player one", "Deuce" },
            { "Thirty-Forty", "player two", "Win for player two" },

            { "Deuce",                "player one", "Advantage player one" },
            { "Deuce",                "player two", "Advantage player two" },
            { "Advantage player one", "player one", "Win for player one" },
            { "Advantage player one", "player two", "Deuce" },
            { "Advantage player two", "player one", "Deuce" },
            { "Advantage player two", "player two", "Win for player two" },

            { "Win for player two", "player one", null },
            { "Win for player two", "player two", null },
            { "Win for player one", "player one", null },
            { "Win for player one", "player two", null },
        });
    }

    @BeforeClass
    public static void initAllPossibleStateGames() {
        gamesByScores = createAllPossibleStateGames();
    }
    
    private static Map<String, TennisGame1> createAllPossibleStateGames() {
        Map<String, TennisGame1> map = new HashMap<String, TennisGame1>();
        
        map.put("Love-All", new TennisGame1("player one", "player two"));
        
        map.put("Fifteen-Love", cloneLettingPlayerWinAPoint(map.get("Love-All"), "player one"));
        map.put("Love-Fifteen", cloneLettingPlayerWinAPoint(map.get("Love-All"), "player two"));
        
        map.put("Thirty-Love", cloneLettingPlayerWinAPoint(map.get("Fifteen-Love"), "player one"));
        map.put("Fifteen-All", cloneLettingPlayerWinAPoint(map.get("Love-Fifteen"), "player one"));
        map.put("Love-Thirty", cloneLettingPlayerWinAPoint(map.get("Love-Fifteen"), "player two"));
        
        map.put("Forty-Love", cloneLettingPlayerWinAPoint(map.get("Thirty-Love"), "player one"));
        map.put("Thirty-Fifteen", cloneLettingPlayerWinAPoint(map.get("Thirty-Love"), "player two"));
        map.put("Fifteen-Thirty", cloneLettingPlayerWinAPoint(map.get("Fifteen-All"), "player two"));
        map.put("Love-Forty", cloneLettingPlayerWinAPoint(map.get("Love-Thirty"), "player two"));
        
        map.put("Forty-Fifteen", cloneLettingPlayerWinAPoint(map.get("Forty-Love"), "player two"));
        map.put("Thirty-All", cloneLettingPlayerWinAPoint(map.get("Fifteen-Thirty"), "player one"));
        map.put("Fifteen-Forty", cloneLettingPlayerWinAPoint(map.get("Fifteen-Thirty"), "player two"));
        
        map.put("Forty-Thirty", cloneLettingPlayerWinAPoint(map.get("Thirty-All"), "player one"));
        map.put("Thirty-Forty", cloneLettingPlayerWinAPoint(map.get("Thirty-All"), "player two"));
        
        map.put("Deuce", cloneLettingPlayerWinAPoint(map.get("Thirty-Forty"), "player one"));
        map.put("Advantage player one", cloneLettingPlayerWinAPoint(map.get("Deuce"), "player one"));
        map.put("Advantage player two", cloneLettingPlayerWinAPoint(map.get("Deuce"), "player two"));
        
        map.put("Win for player two", cloneLettingPlayerWinAPoint(map.get("Advantage player two"), "player two"));
        map.put("Win for player one", cloneLettingPlayerWinAPoint(map.get("Forty-Love"), "player one"));
        
        return Collections.unmodifiableMap(map);
    }

    private static TennisGame1 cloneLettingPlayerWinAPoint(TennisGame1 game, String player) {
        final TennisGame1 clonedGame = game.cloneAsTennisGame1();
        clonedGame.wonPoint(player);
        return clonedGame;
    }
    
    @Test
    public void givenAGameWhenScoreIsAskedThenCurrentExpectedScoreIsReturned() {
        final String actualScore = gameUnderTest.getScore();
        
        assertEquals( currentExpectedScore, actualScore );
    }

    @Test
    public void givenAnUncompletedGameWhenNextPlayerWinsAPointThenNextExpectedScoreIsReached() {
        if ( nextExpectedScore != null ) {
            gameUnderTest.wonPoint(nextPointPlayer);
            
            final String actualScore = gameUnderTest.getScore();
            
            assertEquals( nextExpectedScore, actualScore );
        }
    }
    
    @Test
    public void givenACompletedGameWhenNextPlayerWinsAPointThenAnExceptionIsRaised() {
        if ( nextExpectedScore == null ) {
            IllegalStateException actualException = null; 
            try {
                gameUnderTest.wonPoint(nextPointPlayer);
            } catch (IllegalStateException e) {
                actualException = e;
            } finally {
                assertNotNull(actualException);
                assertEquals("Game over", actualException.getMessage());
            }
        }
    }

}
