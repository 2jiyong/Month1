import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeAlpha {
    private static final Function<Integer, Set<Integer>> factors =
            number -> IntStream.rangeClosed(1,(int)Math.ceil(Math.sqrt(number)))
                    .filter(pod -> number % pod == 0)
                    .flatMap(pod -> IntStream.of(pod,number/pod))
                    .boxed()
                    .collect(Collectors.toSet());

    public static Predicate<Integer> isPrime = number -> {
        Set<Integer> primeSet = new HashSet<Integer>(){ {add(1); add(number);} };
        return number > 1 && factors.apply(number).equals(primeSet);
    };


    public static void main(String[] args) {
        PrimeAlpha prime1 = new PrimeAlpha();
        PrimeAlpha prime2 = new PrimeAlpha();

        System.out.println(prime1.isPrime.test(10));
        System.out.println(prime2.isPrime.test(7));
    }
}
