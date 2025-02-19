public class Card {
    public enum animalSuit {
        DOG,CAT,COW
    }

    private final int number;
    private final animalSuit suit;

    public Card(int number, animalSuit suit){
        this.number=number;
        this.suit=suit;
    }

    public int getNumber() {
        return number;
    }

    public animalSuit getSuit() {
        return suit;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (suit==animalSuit.CAT){
            sb.append("\uD83D\uDC31");
        }
        else if (suit==animalSuit.DOG){
            sb.append("\uD83D\uDC36");
        }
        else if (suit == animalSuit.COW){
            sb.append("\uD83D\uDC2E");
        }
        sb.append(String.format("%02d",number));
        return sb.toString();
    }
}
