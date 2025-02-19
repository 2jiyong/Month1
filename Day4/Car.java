import java.time.YearMonth;

public class Car {
    private final String name;
    private final boolean isDiscontinued;
    private final String type;
    private final int passengerCapacity;
    private final YearMonth productionStartDate;
    private final YearMonth productionEndDate;

    public Car(String name, boolean isDiscontinued, String type, int passengerCapacity, String productionStartDate, String productionEndDate) {
        this.name = name;
        this.isDiscontinued = isDiscontinued;
        this.type = type;
        this.passengerCapacity = passengerCapacity;
        this.productionStartDate = stringToYearMonth(productionStartDate);
        this.productionEndDate = stringToYearMonth(productionEndDate);
    }

    public static YearMonth stringToYearMonth(String date){
        return YearMonth.of(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4)));
    }

    public boolean isValid(YearMonth yearMonth, int passengerCount){
        if (yearMonth.isAfter(productionStartDate) && productionEndDate.isAfter(yearMonth)){
            if (passengerCapacity>=passengerCount) return true;
        }
        return false;
    }

    public String showCarInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (isDiscontinued) sb.append("*");
        sb.append("(").append(type).append(")");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public String getType() {
        return type;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public YearMonth getProductionStartDate() {
        return productionStartDate;
    }

    public YearMonth getProductionEndDate() {
        return productionEndDate;
    }
}
