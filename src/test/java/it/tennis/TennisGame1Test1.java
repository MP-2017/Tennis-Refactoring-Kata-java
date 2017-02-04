package it.tennis;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.tennis.TennisGame;
import it.tennis.TennisGame1;

@RunWith(Parameterized.class)
public class TennisGame1Test1 {

    private final String player1Name;
    private final String player2Name;
    private final int player1Points;
    private final int player2Points;
    private final String expectedScore;

    public TennisGame1Test1(String player1Name, int player1Points, String player2Name, int player2Points, String expectedScore) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.expectedScore = expectedScore;
    }
    
    @Parameters
    public static Collection<Object[]> getAllScores() {
        return Arrays.asList(new Object[][] {
                { "Tom", 0, "Jerry", 0, "Love-All"},
                { "Tom", 1, "Jerry", 1, "Fifteen-All"},
                { "Tom", 2, "Jerry", 2, "Thirty-All"},
                { "Tom", 3, "Jerry", 3, "Deuce"},
                { "Tom", 4, "Jerry", 4, "Deuce"},
                { "Tom", 5, "Jerry", 5, "Deuce"},
                { "Tom", 17, "Jerry", 17, "Deuce"},
                
                { "Cip", 1, "Ciop", 0, "Fifteen-Love"},
                { "Cip", 0, "Ciop", 1, "Love-Fifteen"},
                { "Cip", 2, "Ciop", 0, "Thirty-Love"},
                { "Cip", 0, "Ciop", 2, "Love-Thirty"},
                { "Cip", 3, "Ciop", 0, "Forty-Love"},
                { "Cip", 0, "Ciop", 3, "Love-Forty"},
                { "Cip", 4, "Ciop", 0, "Win for Cip"},
                { "Cip", 0, "Ciop", 4, "Win for Ciop"},
                
                { "Jerry", 2, "Tom", 1, "Thirty-Fifteen"},
                { "Jerry", 1, "Tom", 2, "Fifteen-Thirty"},
                { "Jerry", 3, "Tom", 1, "Forty-Fifteen"},
                { "Jerry", 1, "Tom", 3, "Fifteen-Forty"},
                { "Jerry", 4, "Tom", 1, "Win for Jerry"},
                { "Jerry", 1, "Tom", 4, "Win for Tom"},

                { "Ciop", 3, "Cip", 2, "Forty-Thirty"},
                { "Ciop", 2, "Cip", 3, "Thirty-Forty"},
                { "Ciop", 4, "Cip", 2, "Win for Ciop"},
                { "Ciop", 2, "Cip", 4, "Win for Cip"},
                
                { "Tom", 4, "Jerry", 3, "Advantage Tom"},
                { "Tom", 3, "Jerry", 4, "Advantage Jerry"},
                { "Tom", 5, "Jerry", 4, "Advantage Tom"},
                { "Tom", 4, "Jerry", 5, "Advantage Jerry"},
                { "Tom", 15, "Jerry", 14, "Advantage Tom"},
                { "Tom", 14, "Jerry", 15, "Advantage Jerry"},

                { "Cip", 6, "Ciop", 4, "Win for Cip"},
                { "Cip", 4, "Ciop", 6, "Win for Ciop"},
                { "Cip", 16, "Ciop", 14, "Win for Cip"},
                { "Cip", 14, "Ciop", 16, "Win for Ciop"},
        });
    }

    public void checkAllScores(TennisGame game) {
        int maxPoints = Math.max(this.player1Points, this.player2Points);
        for (int i = 0; i < maxPoints; i++) {
            if (i < this.player1Points)
                game.wonPoint(this.player1Name);
            if (i < this.player2Points)
                game.wonPoint(this.player2Name);
        }
        assertEquals(this.expectedScore, game.getScore());
    }

    @Test
    public void checkAllScoresTennisGame1() {
        TennisGame1 game = new TennisGame1(this.player1Name, this.player2Name);
        checkAllScores(game);
    }

}
