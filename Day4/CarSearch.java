import java.time.YearMonth;
import java.util.Scanner;

public class CarSearch {
    public static void main(String[] args) {
        CarCollection carCollection = makeCarCollection();
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        YearMonth yearMonth = Car.stringToYearMonth(input[0].trim());
        int passengerCount = Integer.parseInt(input[1].trim());
        carCollection.printValidCars(yearMonth,passengerCount);
    }

    public static CarCollection makeCarCollection(){
        CarCollection carCollection = new CarCollection();
        carCollection.add(new Car("Tuscani",true,"Coupe",2,
                "200109","200810"));
        carCollection.add(new Car("Porter",true,"Truck",3,
                "197702","200405"));
        carCollection.add(new Car("Cortina",true,"Sedan",5,
                "196801","198004"));
        carCollection.add(new Car("Elantra",true,"Sedan",5,
                "199010","199512"));
        carCollection.add(new Car("Grandeur",false,"Sedan",5,
                "198607","202305"));
        carCollection.add(new Car("Pony",true,"Sedan",5,
                "197512","198201"));
        carCollection.add(new Car("SantaFe",false,"RV",7,
                "200006","202305"));
        carCollection.add(new Car("Aerotown",false,"Bus",30,
                "199406","202305"));
        carCollection.add(new Car("Universe",false,"Bus",45,
                "200612","202305"));
        return carCollection;
    }
}
