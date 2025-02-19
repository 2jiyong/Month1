import java.util.*;

public class Table {
    //CREATE TABLE billboard (singer String, year Numeric, song String)
    //singer:String
    private static int idCounter = 1;
    private final Map<String,String> header = new LinkedHashMap<>();
    private final List<Map<String,String>> recordList = new ArrayList<>(){};

    public Table(List<String> headers) {
        //CREATE TABLE billboard (singer String, year Numeric, song String)
        // 리스트를 순회하며 headerMap으로 변환하고 테이블 반환
        for (String inputHeader : headers) {
            String[] splitHeader = inputHeader.split(" ");
            if(splitHeader[0].equals("id")) throw new IllegalArgumentException("id는 헤더로 사용할 수 없습니다.");
            header.put(splitHeader[0], splitHeader[1]);
        }
    }

    public Set<String> getHeaderKeys(){
        return header.keySet();
    }

    public void addRecordToTable(List<String> headers, List<String> values){
        recordList.add(createRecord(headers,values));
    }

    private Map<String,String> createRecord(List<String> headers, List<String> values){
        Map<String,String> record = new HashMap<>();
        for(int i = 0 ; i<headers.size(); i++){
            record.put(headers.get(i),values.get(i));
        }
        record.put("id", String.valueOf(idCounter++));
        return record;
    }

    public List<Map<String, String>> getRecordList() {
        return recordList;
    }


    public String showRecordInfo(Map<String,String> record){
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(record.get("id")).append(", ");
        for(String key : header.keySet()){
            if(header.get(key).equals("String")) sb.append("\"");
            sb.append(record.get(key));
            if(header.get(key).equals("String")) sb.append("\"");
            sb.append(", ");
        }
        if(!sb.isEmpty()) sb.delete(sb.length()-2,sb.length());
        sb.append(")");

        return sb.toString();
    }


    public String showLastRecordInfo(){
        Map<String,String> record = recordList.getLast();
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(record.get("id")).append(", ");
        for(String key : header.keySet()){
            if(header.get(key).equals("String")) sb.append("\"");
            sb.append(record.get(key));
            if(header.get(key).equals("String")) sb.append("\"");
            sb.append(", ");
        }
        if(!sb.isEmpty()) sb.delete(sb.length()-2,sb.length());
        sb.append(")");

        return sb.toString();
    }

    public int getHeaderSize(){
        return header.size();
    }
}
