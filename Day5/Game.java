import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Card> deck;
    private List<Player> playerList;
    private List<Card> tableCards;
    private List<Card> discardDeck;
    private final int MAX_CARD_NUMBER;
    private final int HAND_SIZE;
    private final int TABLE_SIZE;
    private final int NUMBER_OF_PLAYERS;

    public Game(int numberOfPlayers){
        this.NUMBER_OF_PLAYERS =numberOfPlayers;
        MAX_CARD_NUMBER = NUMBER_OF_PLAYERS != 3 ? 12 : 11;
        HAND_SIZE = getHandSize(NUMBER_OF_PLAYERS);
        TABLE_SIZE = getTableSize(NUMBER_OF_PLAYERS);
        deck = createDeck(NUMBER_OF_PLAYERS);
        playerList = createPlayerList(NUMBER_OF_PLAYERS);
        tableCards = new ArrayList<>();
        while (!deck.isEmpty()){
            tableCards.add(deck.removeLast());
        }
        discardDeck= new ArrayList<>();
    }

    public void playGame(){
        Scanner sc = new Scanner(System.in);
        while (true){
            int num = sc.nextInt();
            if (num == 0 ) return;
            for (Player player : playerList){
                playTurn(player);
            }
        }
    }


    private ArrayList<Player> createPlayerList(int numberOfPlayers){
        ArrayList<Player> tempPlayerList = new ArrayList<>();
        for (int i=0; i<numberOfPlayers; i++){
            tempPlayerList.add(createPlayer(HAND_SIZE));
        }
        return tempPlayerList;
    }

    private Player createPlayer(int handSize){
        Player player = new Player();
        for(int i = 0; i<handSize; i++){
            player.addCard(drawOneCard());
        }
        player.handSort();
        return player;
    }

    private ArrayList<Card> createDeck(int numberOfPlayers){
        ArrayList<Card> tempDeck = new ArrayList<>();
        for(int i = 1 ; i <= MAX_CARD_NUMBER; i++){
            for (Card.animalSuit suit : Card.animalSuit.values()) {
                Card card = new Card(i, suit);
                tempDeck.add(card);
            }
        }
        Collections.shuffle(tempDeck);
        return tempDeck;
    }

    private int getHandSize(int numberOfPlayers){
        if (numberOfPlayers==3) return 8;
        if (numberOfPlayers==4) return 7;
        if (numberOfPlayers==5) return 6;
        return 0;
    }
    private int getTableSize(int numberOfPlayers){
        if (numberOfPlayers==3) return 9;
        if (numberOfPlayers==4) return 8;
        if (numberOfPlayers==5) return 6;
        return 0;
    }

    public void showEveryCards(){
        for (Player player : playerList){
            player.showHand();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("바닥 [");
        for(Card card : tableCards){
            sb.append(card);
            sb.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("]\n");

        if (!discardDeck.isEmpty()) {
            sb.append("제거 [");
            for (Card card : discardDeck) {
                sb.append(card);
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public void playTurn(Player player){
        if (!player.isCanPlay()){
            System.out.println((char)(player.getPlayerNumber()+64)+"는 더 이상 플레이 할 수 없습니다.");
            return;
        }
        if (isValidCard(player,player.getFirstCardNumber())){
            scoreGetLogic(player,true);
        }
        else if (isValidCard(player,player.getLastCardNumber())){
            scoreGetLogic(player, false);
        }
        else {
            player.setCanPlay(false);
        }
        showEveryCards();
    }

    public void scoreGetLogic(Player player, boolean isFisrtNumber){
        player.setScore(player.getScore()+1);
        int targetNumber;
        if(isFisrtNumber){
            targetNumber = player.getFirstCardNumber();
        }
        else{
            targetNumber = player.getLastCardNumber();
        }
        for (Player everyPlayer : playerList ){
            if (everyPlayer.getFirstCardNumber() == targetNumber){
                discardDeck.add(everyPlayer.discardCard(true));
            }
            else if (everyPlayer.getLastCardNumber() == targetNumber){
                discardDeck.add(everyPlayer.discardCard(false));
            }
            if (everyPlayer == player){
                if (everyPlayer.getFirstCardNumber() == targetNumber){
                    discardDeck.add(everyPlayer.discardCard(true));
                }
                else if (everyPlayer.getLastCardNumber() == targetNumber){
                    discardDeck.add(everyPlayer.discardCard(false));
                }
            }
        }
        tableCards.removeIf(card -> {
            if (card.getNumber() == targetNumber) {
                discardDeck.add(card);
                return true;  // 조건에 맞는 요소 제거
            }
            return false;
        });
    }

    private boolean isValidCard(Player player, int number){
        boolean isValid = false;
        for (Player otherPlayer : playerList){
            if (otherPlayer == player) {
                continue;
            }
            if (otherPlayer.getFirstCardNumber() == number || otherPlayer.getLastCardNumber()==number){
                isValid=true;
            }
        }
        for(Card card : tableCards){
            if (card.getNumber() == number){
                isValid=true;
            }
        }
        return isValid;
    }

    private Card drawOneCard(){
        return deck.removeLast();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getTableCards() {
        return tableCards;
    }

}
