import java.util.Arrays;

class P1 {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = Arrays.stream(arr)
                .filter(e -> e % divisor==0)
                .sorted()
                .toArray();
        return answer.length!=0 ? answer: new int[] {-1};
    }
}