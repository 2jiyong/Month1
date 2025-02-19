import java.io.*;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileManager {
    public static void readFile(String filePath,LogManager logManager){
        // String line = "default\t14:22:03.774302+0900\tMTLCompilerService\tCompilation (pipeline) time 12.339620 ms\n";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line ;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSXX");
            LogEntry lastEntry = null;
            while ((line = br.readLine()) != null){
                String[] splitLine= line.split("\t");
                if (splitLine.length == 4){
                    OffsetTime time = OffsetTime.parse(splitLine[1], formatter);
                    lastEntry = new LogEntry(splitLine[0],time,splitLine[2],splitLine[3]);
                    logManager.addLogEntry(lastEntry);
                }else {
                    lastEntry.setRecord(lastEntry.getRecord().concat(line));
                }
            }
        } catch (IOException e){
            System.out.println("입출력 에러 발생 "+e);
        }
    }

    public static void writeFile(String filePath, List<LogEntry> logEntryList){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (LogEntry logEntry : logEntryList){
                writer.write(logEntry.showInfo());
            }
        } catch (IOException e) {
            System.out.println("입출력 에러 발생 "+e);
        }
    }
}
