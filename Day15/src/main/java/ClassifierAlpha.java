import java.util.function.*;
import java.util.stream.IntStream;

public class ClassifierAlpha {

    private final Function<Integer, Integer> factors =
            number -> IntStream.range(1,(int)Math.ceil(Math.sqrt(number)))
                    .filter(pod -> number % pod == 0)
                    .flatMap(pod -> IntStream.of(pod,number/pod))
                    .distinct()
                    .sum();

    public final Predicate<Integer> isPerfect = number ->
            factors.apply(number)-number == number;
    ;

    public final Predicate<Integer> isAbundant = number ->
            factors.apply(number)-number > number;


    public final Predicate<Integer> isDeficient = number ->
            factors.apply(number)-number < number;



    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha();
        ClassifierAlpha alpha2 = new ClassifierAlpha();

        System.out.println(alpha1.isPerfect.test(10));
        System.out.println(alpha2.isPerfect.test(6));
    }
}
