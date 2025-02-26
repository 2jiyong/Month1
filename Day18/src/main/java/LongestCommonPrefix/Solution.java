package LongestCommonPrefix;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int idx = 0 ;
        StringBuilder sb = new StringBuilder();
        try{
            while (true){
                boolean isCommon = true;
                char first = strs[0].charAt(idx);
                for(int i = 1 ; i<strs.length; i++){
                    if(first!=strs[i].charAt(idx)) isCommon=false;
                }
                if(isCommon) sb.append(first);
                else return sb.toString();
                idx++;
            }
        } catch(Exception e){
            return sb.toString();
        }
    }
}