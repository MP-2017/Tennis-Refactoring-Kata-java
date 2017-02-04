
public class TennisGame1 implements TennisGame {

    private int score1 = 0;
    private int score2 = 0;
    private final String player1;
    private final String player2;

    public TennisGame1(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String playerName) {
        if (player1.equals(playerName))
            score1 += 1;
        else if (player2.equals(playerName))
            score2 += 1;
        else 
            throw new IllegalArgumentException("Invalid player name: " + playerName);
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (score1 == score2) {
            switch (score1) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            default:
                score = "Deuce";
                break;
            }
        } else if (score1 >= 4 || score2 >= 4) {
            int minusResult = score1 - score2;
            if (minusResult == 1)
                score = "Advantage " + player1;
            else if (minusResult == -1)
                score = "Advantage " + player2;
            else if (minusResult >= 2)
                score = "Win for " + player1;
            else
                score = "Win for " + player2;
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1)
                    tempScore = score1;
                else {
                    score += "-";
                    tempScore = score2;
                }
                switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
                }
            }
        }
        return score;
    }
}
