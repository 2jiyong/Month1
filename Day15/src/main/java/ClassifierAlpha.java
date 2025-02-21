import java.util.stream.IntStream;

public class ClassifierAlpha {
    private int number;

    public ClassifierAlpha(int number) {
        this.number = number;
    }

    public int sumFactors(int number) {
        return IntStream.rangeClosed(1, (int) Math.ceil(Math.sqrt(number))) // 1부터 sqrt(number)까지 반복
                .filter(i -> number % i == 0) // 약수인지 판별
                .flatMap(i -> IntStream.of(i, number / i)) // i와 number / i 추가
                .distinct() // 중복 제거
                .sum(); // 총합 계산
        }

    public boolean isPerfect() {
        return sumFactors(number) - number == number;
    }

    public boolean isAbundant() {
        return sumFactors(number) - number > number;
    }

    public boolean isDeficient() {
        return sumFactors(number) - number < number;
    }

    public static void main(String[] args) {
        ClassifierAlpha alpha1 = new ClassifierAlpha(10);
        ClassifierAlpha alpha2 = new ClassifierAlpha(6);

        System.out.println(alpha1.isPerfect());
        System.out.println(alpha2.isPerfect());
    }
}