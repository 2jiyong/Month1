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

    public boolean isPerfect() {
        return factor.apply(number)-number == number;
    }
    public boolean isAbundant() {
        return factor.apply(number)-number > number;
    }
    public boolean isDeficient() {
        return factor.apply(number)-number < number;
    }

    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha(10);
        ClassifierAlpha alpha2 = new ClassifierAlpha(6);

        System.out.println(alpha1.isPerfect());
        System.out.println(alpha2.isPerfect());
    }
}
