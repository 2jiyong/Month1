import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayConverter extends Thread{
    private final int MARS_MONTH_BY_DAY = 28;
    private final int MARS_YEAR_BY_MONTH = 24;
    private final int MARS_YEAR_BY_DAY = 668;
    private final int MARS_LEAP_YEAR_PERIOD = 2;
    private final LocalDate EARTH_START_DATE =LocalDate.of(1,1,1);
    private final String earthDate;
    private String result;

    public DayConverter(String earthDate){
        this.earthDate = earthDate;
    }

    @Override
    public void run(){
        result = printConvertedCalender(earthDate);
    }

    public String getResult(){
        return result;
    }

    public int findDaysByEarthDate(LocalDate earthDate){
        return (int) ChronoUnit.DAYS.between(EARTH_START_DATE,earthDate);
    }

    public int[] findMarsDateByDays(int days){
        int marsYear = 1, marsMonth = 1, marsDay = 1;
        // 2년을 기준으로 해서 연을 구함
        marsYear += (days/(MARS_YEAR_BY_DAY*2+1))*2 ;
        int leftDays = days%(MARS_YEAR_BY_DAY*2+1);
        if (leftDays >= MARS_YEAR_BY_DAY) {
            leftDays-=MARS_YEAR_BY_DAY;
            marsYear++;
        }
        // 구한 연도가 짝수면 윤년
//        boolean isLeapYear = marsYear % 2 == 0;

        // 월 연산 남은 날짜의 최대는 668
        for(int month=1; month<MARS_YEAR_BY_MONTH; month++){
            if (month%6 != 0){
                if(leftDays>=MARS_MONTH_BY_DAY){
                    leftDays-=MARS_MONTH_BY_DAY;
                    marsMonth++;
                } else break;
            } else {
                if(leftDays>=MARS_MONTH_BY_DAY-1){
                    leftDays-=MARS_MONTH_BY_DAY-1;
                    marsMonth++;
                } else break;
            }
        }

        marsDay+=leftDays;

        return new int[] {marsYear,marsMonth,marsDay};
    }

    public String printConvertedCalender(String date){
        String[] splitDate = date.split("-");
        LocalDate localDate =LocalDate.of(Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2]));
        int days = findDaysByEarthDate(localDate);
        int[] convertedDate = findMarsDateByDays(days);
        StringBuilder sb = new StringBuilder();
        sb.append("지구날은 ").append(days).append(" => ");
        sb.append(convertedDate[0]).append(" 화성년 ").append(convertedDate[1]).append("월 ").append(convertedDate[2]).append("일\n");
        sb.append(printMarsCalender(convertedDate[0],convertedDate[1],convertedDate[2]));
        return sb.toString();
    }

    public String printMarsCalender(int year, int month, int day){
        StringBuilder sb = new StringBuilder();
        sb.append("\n     ").append(year).append("년 ").append(month).append("월\n");
        sb.append("Su Lu Ma Me Jo Ve Sa\n");
        sb.append(" 1  2  3  4  5  6  7\n" +
                " 8  9 10 11 12 13 14\n" +
                "15 16 17 18 19 20 21\n" +
                "22 23 24 25 26 27 ");

        if(month%6!=0) sb.append("28");
        else if (month==24 && year%2==0) sb.append("28");

        return sb.toString();
    }
}
