package ReverseInteger;

class Solution {
    public int reverse(int x) {
        String reversedNum = new StringBuilder(Integer.toString(Math.abs(x))).reverse().toString();
        if (x<0){
            reversedNum= "-"+reversedNum;
        }
        try{
            return Integer.parseInt(reversedNum);
        } catch(Exception e){
            return 0;
        }
    }
}