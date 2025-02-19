public class Dart {
    final static int[] dartBoard = {20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5};

    public boolean canEarnScore(int index, int playerIndex){
        for (int i = 0; i<3; i++){
            if((index+i)%dartBoard.length==playerIndex) return true;
        }
        return false;
    }

    public int getScore(int index, int[] playerIndexArray) {
        int sum = 0;
        for (int playerIndex : playerIndexArray) {
            if (canEarnScore(index, playerIndex)) sum += dartBoard[playerIndex];
        }
        if (canEarnScore(index, playerIndexArray[0]) && playerIndexArray[0] == playerIndexArray[1] &&
                playerIndexArray[1] == playerIndexArray[2]) {
            sum += dartBoard[playerIndexArray[0]];
        }
        return sum;
    }
}
