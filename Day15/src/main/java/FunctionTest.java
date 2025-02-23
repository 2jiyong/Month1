import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionTest {
    public static Function<Integer, String> print = number -> {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(" : ");
        sb.append(ClassifierAlpha.isPerfect.test(number) ? "Perfect " : "");
        sb.append(ClassifierAlpha.isAbundant.test(number) ? "Abundant " : "");
        sb.append(ClassifierAlpha.isDeficient.test(number) ? "Deficient " : "");
        sb.append(ClassifierAlpha.isSquared.test(number) ? "Squared " : "");
        sb.append(PrimeAlpha.isPrime.test(number) ? "Prime " : "");
        return sb.toString();
    };


    public static void main(String[] args) {
        IntStream.range(2, 101).forEach(number -> System.out.println(print.apply(number)));
    }
}
