import java.util.HashMap;
import java.util.Map;

public class TableManager {
    private static final Map<String,Table>  tables = new HashMap<>();

    public static void addTable(String tableName, Table table){
        tables.put(tableName,table);
    }

    public static Table getTable(String tableName){
        return tables.get(tableName);
    }

    public static boolean isTableExist(String tableName){
        return tables.containsKey(tableName);
    }

}
