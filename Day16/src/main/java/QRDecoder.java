import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class QRDecoder {
    private Map<Integer,String> parseMap = new HashMap<>();

    public QRDecoder(){
        initializeMap();
    }

    public void initializeMap(){
        for(int i = 0 ; i<10;i++){
            parseMap.put(i,Integer.toString(i));
        }
        for(int i = 10; i<36;i++){
            parseMap.put(i,Integer.toString((char)(i+55)));
        }
        parseMap.put(36," ");
        parseMap.put(37,"$");
        parseMap.put(38,"%");
        parseMap.put(39,"*");
        parseMap.put(40,"+");
        parseMap.put(41,"-");
        parseMap.put(42,".");
        parseMap.put(43,"/");
        parseMap.put(44,":");
    }

    // dataBlock을 받아서 위치를 옮기고, 그 위치의 값을 읽어 String으로 parse
    public Function<DataBlock,String> upFunction = dataBlock -> {
        dataBlock.moveStartCoordinate(-4,0);
        dataBlock.moveEndCoordinate(-4,0);
        int[][] convertedArray = convertArrayUp(dataBlock.readQRArray());
        return parseMap.get(convertArrayToInt(convertedArray));
    };

    public int[][] convertArrayUp(int[][] array){
        int rows = array.length;
        int cols = array[0].length;
        int[][] reversed = new int[rows][cols];
        // 180도 회전 (위아래 + 좌우 뒤집기)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                reversed[i][j] = array[rows - 1 - i][cols - 1 - j];
            }
        }
        return reversed;
    }

    private int convertArrayToInt(int[][] array){
        StringBuilder sb = new StringBuilder();
        for(int r = 0 ; r<array.length; r++){
            for(int c = 0 ; c<array[0].length;c++){
                sb.append(array[r][c]);
            }
        }
        return Integer.parseInt(sb.toString());
    }




    private List<Function<DataBlock,String>> decodeStep = new ArrayList<>(List.of(upFunction,upFunction));

    public void decode() {
        DataBlock dataBlock = new DataBlock(new int[]{15, 19}, new int[]{18, 20});

        decodeStep.stream().forEach(step->step.apply(dataBlock));

    }
}
