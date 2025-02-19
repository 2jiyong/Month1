import java.util.Comparator;
import java.util.List;

public class LogComparator implements Comparator<LogEntry> {
    ComparatorType comparatorType;
    public LogComparator(ComparatorType comparatorType){
        this.comparatorType  = comparatorType;
    }

    public enum ComparatorType{
        TIME_ASC,
        TIME_DSC,
        PROCESS_NAME_ASC,
        PROCESS_NAME_DSC,
    }

    public int compare(LogEntry log1 ,LogEntry log2){
        return switch (comparatorType){
            case TIME_ASC -> log1.getDateTime().compareTo(log2.getDateTime());
            case TIME_DSC -> log2.getDateTime().compareTo(log1.getDateTime());
            case PROCESS_NAME_ASC -> log1.getProcess().compareToIgnoreCase(log2.getProcess());
            case PROCESS_NAME_DSC -> log2.getProcess().compareToIgnoreCase(log1.getProcess());
            default -> log1.getDateTime().compareTo(log2.getDateTime());
        };
    }

    public static List<LogEntry> sortLogList(List<LogEntry> logList, LogComparator logComparator){
        logList.sort(logComparator);
        return logList;
    }
}
