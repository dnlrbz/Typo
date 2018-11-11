package at.ac.univie.hci.typo.Model;

public class Score {

    private Player player;
    private int score;

    public Score(Player player, int score) {
        this.player = player;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "player=" + player +
                ", score=" + score +
                '}';
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
