import java.util.*;

public class LogManager {
    private List<LogEntry> logs = new ArrayList<>();
    private Map<String,List<LogEntry>> logsByLevel = new HashMap<>();
    private Map<String,List<LogEntry>> logsByProcess = new HashMap<>();

    public void addLogEntry(LogEntry logEntry) {
        logs.add(logEntry);
        logsByLevel.computeIfAbsent(logEntry.getLogLevel(), k -> new ArrayList<>()).add(logEntry);
        logsByProcess.computeIfAbsent(logEntry.getProcess(), k -> new ArrayList<>()).add(logEntry);
    }

    public List<LogEntry> getLogs(){
        return logs;
    }

    public List<LogEntry> getLogsByLevelAndProcess(String level,String process){
        Set<LogEntry> levelSet = new HashSet<>(logsByLevel.get(level));
        Set<LogEntry> processSet = new HashSet<>(logsByProcess.get(process));
        levelSet.retainAll(processSet);
        return new ArrayList<>(levelSet);
    }

    public List<LogEntry> getLogsByLevel(String level){
        return logsByLevel.get(level);
    }

    public List<LogEntry> getLogsByProcess(String process){
        return logsByProcess.get(process);
    }

//    public List<LogEntry> sortListByComparator(List<LogEntry> logList, Comparator comparator){
//
//    }

}
