public class Scores {
    private int scoreA;
    private int scoreB;

    public Scores(){
        scoreA=0;
        scoreB=0;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public void earnScoreA(int score){
        scoreA+=score;
    }

    public void earnScoreB(int score){
        scoreB+=score;
    }
}

