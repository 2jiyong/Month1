import java.util.ArrayList;

class P2 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> basket = new ArrayList<>();
        for (int move : moves){
            int number = pickDoll(board,move);
            if (number!=0){
                basket.add(number);
                if(checkTwoDolls(basket)){
                    answer+=2;
                    basket.remove(basket.size()-1);
                    basket.remove(basket.size()-1);
                }
            }

        }
        return answer;
    }

    public static int pickDoll(int[][] board, int c){
        c--;
        for(int r = 0 ; r<board[0].length; r++){
            if (board[r][c]!=0){
                int number = board[r][c];
                board[r][c]=0;
                return number;
            }
        }
        return 0;
    }

    public static boolean checkTwoDolls(ArrayList<Integer> basket){
        if (basket.size()<2) return false;
        if (basket.get(basket.size()-1) == basket.get(basket.size()-2)){
            return true;
        }
        return false;
    }

}