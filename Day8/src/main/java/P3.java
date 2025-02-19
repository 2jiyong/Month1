import java.util.*;

class P3 {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        Set<Integer> sumSet = new HashSet<>();
        for (int i =0 ; i<numbers.length; i++){
            for (int j =i+1 ; j<numbers.length;j++){
                sumSet.add(numbers[i]+numbers[j]);
            }
        }

        answer = sumSet.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();

        return answer;
    }
}