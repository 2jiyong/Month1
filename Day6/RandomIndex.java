import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RandomIndex {
    private List<int[]> randomIndexOrder;
    private int[] randomRowIndex;
    private int[] randomColIndex;

    public RandomIndex(){
        randomIndexOrder = new ArrayList<>(Arrays.asList(
                new int[] {0, 1, 2},
                new int[] {0, 2, 1},
                new int[] {1, 0, 2},
                new int[] {1, 2, 0},
                new int[] {2, 0, 1},
                new int[] {2, 1, 0}
        ));
        Collections.shuffle(randomIndexOrder);
    }

    public void makeNewRandomIndexOrder(){
        randomIndexOrder = new RandomIndex().getRandomIndexOrder();
    }

    public List<int[]> getRandomIndexOrder() {
        return randomIndexOrder;
    }

    //무작위 인덱스 모음 반환 예){1,2,0}
    public int[] popLastIndexOrder(){
        return randomIndexOrder.removeLast();
    }

    public void printRandomIndexOrder(){
        for (int[] row : randomIndexOrder) {
            System.out.println(Arrays.toString(row));
        }
    }


}
