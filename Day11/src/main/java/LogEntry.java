import java.time.OffsetTime;

public class LogEntry {
    private String logLevel;
    private OffsetTime dateTime;
    private String process;
    private String record;

    public LogEntry(String logLevel, OffsetTime dateTime, String process, String record) {
        this.logLevel = logLevel;
        this.dateTime = dateTime;
        this.process = process;
        this.record = record;
    }
    public void setRecord(String string){
        record = string;
    }

    public String getLogLevel(){
        return logLevel;
    }
    public String getProcess(){
        return process;
    }
    public OffsetTime getDateTime(){
        return dateTime;
    }
    public String getRecord(){
        return record;
    }

    public String showInfo(){
        return (logLevel+"\t"+dateTime+"\t"+process+"\t"+record+"\n");
    }


}
