import java.util.*;

public class Player {
    private List<Card> hand;
    private int playerNumber;
    private static int playerNumberCount = 1;
    private int score;
    private boolean canPlay = true;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(){
        hand = new ArrayList<>();
        playerNumber = playerNumberCount++;
        score = 0;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card discardCard(boolean isFirstCard){
        if (isFirstCard){
            return hand.removeFirst();
        }
        else{
            return hand.removeLast();
        }
    }


    public void showHand() {
        StringBuilder sb = new StringBuilder();
        sb.append((char)(playerNumber+64));
        sb.append(" : ");
        sb.append(score);
        sb.append(" [");
        if (!isHandEmpty()) {
            for (Card card : hand) {
                sb.append(card);
                sb.append(", ");
            }
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }
    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }


    public void handSort(){
        Collections.sort(hand, Comparator.comparing(Card::getNumber));
    }

    public int getFirstCardNumber(){
        return hand.getFirst().getNumber();
    }

    public int getLastCardNumber(){
        return hand.getLast().getNumber();
    }

    public boolean isFirstCardSame(){
        return hand.getFirst().getNumber()==hand.get(1).getNumber();
    }

    public boolean isLastCardSame(){
        return hand.getLast().getNumber()==hand.get(hand.size()-2).getNumber();
    }

    public boolean isHandEmpty(){
        return hand.isEmpty();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
