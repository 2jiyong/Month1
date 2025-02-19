import java.time.YearMonth;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CarCollection {
    private final Set<Car> carSet;

    public CarCollection(){
        carSet = new TreeSet<>(new CarComparator());
    }

    public CarCollection(Comparator<Car> comparator){
        carSet = new TreeSet<>(comparator);
    }

    public void add(Car car){
        carSet.add(car);
    }

//    public void printCars(){
//        for(Car car : cars){
//            System.out.println(car.getName());
//        }
//    }

    public void printValidCars(YearMonth yearMonth, int passengerCount){
        StringBuilder sb = new StringBuilder();
        for(Car car : carSet){
            if (car.isValid(yearMonth,passengerCount)){
                sb.append(car.showCarInfo());
                sb.append(",");
            }
        }
        if (!sb.isEmpty())sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
