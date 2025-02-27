public class MarsCalender {
    public static String printMarsCalender(int year, int month, int day){
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
