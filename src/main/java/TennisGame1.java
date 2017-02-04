
public class TennisGame1 implements TennisGame {

    private static enum Score {
        LOVE, FIFTEEEN, THIRTY, FORTY, DEUCE, ADVANTAGE, WON;
    }
    
    private Score score1 = Score.LOVE;
    private Score score2 = Score.LOVE;
    private final String player1;
    private final String player2;

    public TennisGame1(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String pointPlayer) {
        raiseExceptionForInvalidPlayer(pointPlayer);
        raiseExceptionForGameOver();

        if (player1.equals(pointPlayer)) {
            score1 = incrementScore(score1);
        } else if (player2.equals(pointPlayer)) {
            score2 = incrementScore(score2);
        }
        setBothDeuceWhenRequired();
    }

    private void setBothDeuceWhenRequired() {
        if ( score1 == score2 && (score1 == Score.FORTY || score1 == Score.ADVANTAGE) ) {
            score1 = Score.DEUCE;
            score2 = Score.DEUCE;
        }
    }

    private Score incrementScore(Score score) {
        final Score incrementedScore;
        if ( score == Score.LOVE ) {
            incrementedScore = Score.FIFTEEEN;
        } else if ( score == Score.FIFTEEEN ) {
            incrementedScore = Score.THIRTY;
        } else if ( score == Score.THIRTY ) {
            incrementedScore = Score.FORTY;
        } else if ( score == Score.FORTY ) {
            incrementedScore = Score.WON;
        } else if ( score == Score.DEUCE ) {
            incrementedScore = Score.ADVANTAGE;
        } else if ( score == Score.ADVANTAGE ) {
            incrementedScore = Score.WON;
        } else {
            throw new IllegalStateException(score.name());
        }
        return incrementedScore;
    }
    
    private void raiseExceptionForInvalidPlayer(String player) {
        if ( !player1.equals(player) && !player2.equals(player) ) {
            throw new IllegalArgumentException("Invalid player: " + player);
        }
    }
    
    private void raiseExceptionForGameOver() {
        if ( score1 == Score.WON || score2 == Score.WON ) {
            throw new IllegalStateException("Game over");
        }
    }
    
    public String getScore() {
        String textScoreDescription = "";
        if (score1 == score2 && score1 == Score.DEUCE) {
            textScoreDescription = "Deuce";
        } else if (score1 == score2) {
            textScoreDescription = getScoreLabel(score1) + "-All";
        } else if (score1 == Score.ADVANTAGE) {
            textScoreDescription = "Advantage " + player1;
        } else if (score2 == Score.ADVANTAGE) {
            textScoreDescription = "Advantage " + player2;
        } else if (score1 == Score.WON) {
            textScoreDescription = "Win for " + player1;
        } else if (score2 == Score.WON) {
            textScoreDescription = "Win for " + player2;
        } else {
            textScoreDescription = getScoreLabel(score1) 
                    + "-" + getScoreLabel(score2);
        }
        return textScoreDescription;
    }
 
    private String getScoreLabel(Score score) {
        switch (score) {
        case LOVE:
            return "Love";
        case FIFTEEEN:
            return "Fifteen";
        case THIRTY:
            return "Thirty";
        case FORTY:
            return "Forty";
        default:
            throw new IllegalArgumentException(score.name());
        }
    }
}
