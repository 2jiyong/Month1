import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Query {
    public static void executeQuery(String line){
        String upperCaseline = line.toUpperCase();
        try {
            if (upperCaseline.startsWith("CREATE TABLE")){
                create(line);
            } else if (upperCaseline.startsWith("INSERT INTO")){
                insert(line);
            } else if (upperCaseline.startsWith("DELETE FROM")){
                delete(line);
            }
        }catch(IllegalArgumentException e){
            System.out.println("잘못된 명령입니다. "+e.getMessage());
        }
    }
    //괄호 안에 있는 String만 추출
    private static List<String> extractString(String line){
        List<String> stringList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            stringList.add(matcher.group(1));
        }

        return stringList;
    }

    private static void create(String line) {
        String fileName = line.split(" ")[2];
        if (TableManager.isTableExist(fileName)){
            throw new IllegalArgumentException("이미 존재하는 테이블입니다.");
        }
        List<String> stringList = extractString(line);
        String[] columns = stringList.get(0).split(",");
        List<String> headers = Arrays.stream(columns).map(String::trim).collect(Collectors.toList());
        Table table = new Table(headers);
        TableManager.addTable(fileName,table);
        FileCreater.createCSVFile(fileName,table);
    }

    private static void insert(String line) {
        String fileName = line.split(" ")[2];
        List<String> stringList = extractString(line);
        String[] columns = stringList.get(0).split(",");
        String[] values = stringList.get(1).split(",");
        List<String> headers = Arrays.stream(columns).map(String::trim).collect(Collectors.toList());
        List<String> valueList = Arrays.stream(values).map(String::trim).map(s -> s.replaceAll("^\"|\"$", "")).collect(Collectors.toList());
        if(!TableManager.isTableExist(fileName)) throw new IllegalArgumentException("테이블이 존재하지 않습니다.");

        // 테이블 가져오기
        Table table = TableManager.getTable(fileName);
        if(headers.size()!=valueList.size()||headers.size()!=table.getHeaderSize()) throw new IllegalArgumentException("컬럼 갯수가 일치하지 않습니다.");

        table.addRecordToTable(headers,valueList);
        FileCreater.createCSVFile(fileName,TableManager.getTable(fileName));
        System.out.println("INSERTED "+table.showLastRecordInfo());
    }

    private static void delete(String line) {
        String fileName = line.split(" ")[2];
        if(!TableManager.isTableExist(fileName)) throw new IllegalArgumentException("테이블이 존재하지 않습니다.");

        String[] stringList = line.split("WHERE");
        String trimmedString = stringList[1].trim();

        String[] condition = trimmedString.split(" ");
        String targetColumn = condition[0].trim();
        String targetValue = condition[2].trim();

        Table table = TableManager.getTable(fileName);

        boolean isRemoved = false;
        for( int i = 0; i < table.getRecordList().size(); i++){
            if(table.getRecordList().get(i).get(targetColumn).equals(targetValue)){
                System.out.println("DELETED"+table.showRecordInfo(table.getRecordList().get(i)));
                table.getRecordList().remove(i);
                isRemoved = true;
                break;
            }
        }
        if(!isRemoved) throw new IllegalArgumentException("조건에 맞는 데이터가 존재하지 않습니다.");

        FileCreater.createCSVFile(fileName,TableManager.getTable(fileName));
    }
}
