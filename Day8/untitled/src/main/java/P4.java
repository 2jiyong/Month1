import java.util.*;
class P4 {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] scores = {0,0,0};
        int[] scoreALogic = {1,2,3,4,5};
        int[] scoreBLogic = {2,1,2,3,2,4,2,5};
        int[] scoreCLogic = {3,3,1,1,2,2,4,4,5,5};

        for (int i =0 ; i<answers.length; i++){
            if (scoreALogic[i%scoreALogic.length] == answers[i]) scores[0]+=1;
            if (scoreBLogic[i%scoreBLogic.length] == answers[i]) scores[1]+=1;
            if (scoreCLogic[i%scoreCLogic.length] == answers[i]) scores[2]+=1;
        }

        int maxNum = 0;
        for (int num : scores) {
            if (num > maxNum) {
                maxNum = num;
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i =0 ; i<3; i++){
            if (scores[i]==maxNum){
                list.add(i+1);
            }
        }

        answer = new int[list.size()];
        for (int i =0; i<list.size(); i++){
            answer[i]=list.get(i);
        }

        return answer;
    }
}