import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.*;
import java.util.stream.IntStream;

public class ClassifierAlpha {
    private final int number;

    public ClassifierAlpha(int number) {
        this.number = number;
    }

    private final Function<Integer, Integer> factor =
            number -> IntStream.range(1,(int)Math.ceil(Math.sqrt(number)))
                    .filter(pod -> number % pod == 0)
                    .flatMap(pod -> IntStream.of(pod,number/pod))
                    .distinct()
                    .sum();

    private final BiPredicate<Integer,BiPredicate<Integer,Integer>> tester =
            (number, predicate) ->
            {return predicate.test(factor.apply(number)-number,number);};

    public boolean isPerfect() {
        return tester.test(number,(sum,number)-> sum == number);
    }
    public boolean isAbundant() {
        return tester.test(number,(sum,number)-> sum > number);
    }
    public boolean isDeficient() {
        return tester.test(number,(sum,number)-> sum < number);
    }

    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha(10);
        ClassifierAlpha alpha2 = new ClassifierAlpha(6);

        System.out.println(alpha1.isPerfect());
        System.out.println(alpha2.isPerfect());
    }
}
