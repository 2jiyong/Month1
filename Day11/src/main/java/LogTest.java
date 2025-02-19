import java.util.List;

public class LogTest {
    public static void main(String[] args) {
        String fileName = "1701410305471system.log";
        String resultFile = "Result.log";

        LogManager logManager = new LogManager();
        //파일 읽기
        FileManager.readFile(fileName,logManager);
        // 정렬할 Comparator 생성
        LogComparator logComparator = new LogComparator(LogComparator.ComparatorType.TIME_ASC);
        // 로그 레벨이 error인 로그들을 logComparator(프로세스 오름차순)으로 정렬
        List<LogEntry> logEntryList = LogComparator.sortLogList(logManager.getLogsByLevelAndProcess("default","Airmail"),logComparator);
        // 그 리스트를 다시 log파일로 작성
        FileManager.writeFile(resultFile , logEntryList);
    }
}
