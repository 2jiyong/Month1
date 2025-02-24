import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            parseMap.put(i,Character.toString((char)(i+55)));
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
        //위치 옮기고
        dataBlock.moveStartCoordinate(-4,0);
        dataBlock.moveEndCoordinate(-4,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertUpToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> upAfterCWFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(-4,0);
        dataBlock.moveEndCoordinate(-2,-2);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertUpToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> upSpaceFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(-5,0);
        dataBlock.moveEndCoordinate(-5,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertUpToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> downFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(4,0);
        dataBlock.moveEndCoordinate(4,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertDownToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> downAfterCCWFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(2,0);
        dataBlock.moveEndCoordinate(4,-2);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertDownToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> downSpaceFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(5,0);
        dataBlock.moveEndCoordinate(5,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertDownToInt(dataBlock.readQRArray()));
    };



    public Function<DataBlock,String> CCWFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(-2,-2);
        dataBlock.moveEndCoordinate(-4,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertCCWToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,String> CWFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(4,-2);
        dataBlock.moveEndCoordinate(2,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return parseMap.get(ConvertingFunctions.convertCWToInt(dataBlock.readQRArray()));
    };

    public Function<DataBlock,Integer> lengthFunction = dataBlock -> {
        //위치 옮기고
        dataBlock.moveStartCoordinate(-4,0);
        dataBlock.moveEndCoordinate(-2,0);
        // 배열을 변환하고, 값을 읽어 String으로 변환
        return ConvertingFunctions.convertUpToInt(dataBlock.readQRArray());
    };

    private List<Function<DataBlock,String>> decodeStep = new ArrayList<>(List.of(upFunction,CCWFunction,downAfterCCWFunction,downFunction,CWFunction,
            upAfterCWFunction,upFunction,CCWFunction,downAfterCCWFunction,downFunction,CWFunction,upAfterCWFunction,upFunction,upFunction,
            upSpaceFunction,CCWFunction,downAfterCCWFunction,downSpaceFunction,downFunction,downFunction));



    public void decode() {
        DataBlock dataBlock = new DataBlock(new int[]{19, 19}, new int[]{20, 20});
        int start = ConvertingFunctions.convertStartToInt(dataBlock.readQRArray());

        DataBlock endDataBlock = new DataBlock(new int[]{19,9},new int[]{20,10});
        int end = ConvertingFunctions.convertEndToInt(endDataBlock.readQRArray());
        if (!(start==12 & end == 6)){
            throw new IllegalArgumentException("잘못된 QR입니다.");
        }


        int length = lengthFunction.apply(dataBlock);

        String result = decodeStep.stream().map(step->step.apply(dataBlock))
                .limit(length)
                .reduce("",(a,b)->a+b);

        System.out.print(start+" "+end);
        System.out.println("data = \""+result+"\"");
    }
}
