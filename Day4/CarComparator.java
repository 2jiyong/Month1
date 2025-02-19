import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return Comparator.comparing(Car::getName)
                .compare(o1,o2);
    }
}
