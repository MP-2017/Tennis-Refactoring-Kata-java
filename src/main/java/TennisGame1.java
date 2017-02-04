
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

    public void wonPoint(String playerName) {
        if (player1.equals(playerName)) {
            score1 = incrementScore(score1);
        } else if (player2.equals(playerName)) {
            score2 = incrementScore(score2);
        } else { 
            throw new IllegalArgumentException("Invalid player name: " + playerName);
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
