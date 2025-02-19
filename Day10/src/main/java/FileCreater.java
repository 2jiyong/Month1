import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

public class FileCreater {
    // singer String, year Numeric, song String 이렇게 길이가 3인 리스트를 받음

    public static void createCSVFile(String fileName, Table table){
        String filePath = "";
        String file = filePath+fileName+".csv";
        try (FileWriter writer = new FileWriter(file)){
            //헤더
            Set<String> headerKeys = table.getHeaderKeys();
            StringBuilder sb = new StringBuilder();
            for(String header : headerKeys){
                sb.append(header);
                sb.append(",");
            }
            if(!sb.isEmpty()) sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            sb.append("-----------\n");
            //레코드

            for (Map<String,String> record : table.getRecordList()){
                for(String header : headerKeys){
                    sb.append(record.get(header));
                    sb.append(",");
                }
                if(!sb.isEmpty()) sb.deleteCharAt(sb.length()-1);
                sb.append("\n");
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("파일 생성 실패");
        }
    }
}
