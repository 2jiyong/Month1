import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayConverter {
    private final int MARS_MONTH_BY_DAY = 28;
    private final int MARS_YEAR_BY_MONTH = 24;
    private final int MARS_YEAR_BY_DAY = 668;
    private final int MARS_LEAP_YEAR_PERIOD = 2;
    private final LocalDate EARTH_START_DATE =LocalDate.of(1,1,1);


    public int findDaysByEarthDate(LocalDate earthDate){
        return (int) ChronoUnit.DAYS.between(EARTH_START_DATE,earthDate);
    }

    public int[] findMarsDateByDays(int days){
        int marsYear = 0, marsMonth = 1, marsDay = 1;
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
                }
            } else {
                if(leftDays>=MARS_MONTH_BY_DAY-1){
                    leftDays-=MARS_MONTH_BY_DAY-1;
                    marsMonth++;
                }
            }
        }

        marsDay+=leftDays;

        return new int[] {marsYear,marsMonth,marsDay};
    }
}
