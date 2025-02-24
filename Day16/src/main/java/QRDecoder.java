import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class QRDecoder {
    private Map<Integer, String> parseMap = new HashMap<>();

    public QRDecoder() {
        initializeMap();
    }

    public void initializeMap() {
        for (int i = 0; i < 10; i++) {
            parseMap.put(i, Integer.toString(i));
        }
        for (int i = 10; i < 36; i++) {
            parseMap.put(i, Character.toString((char) (i + 55)));
        }
        parseMap.put(36, " ");
        parseMap.put(37, "$");
        parseMap.put(38, "%");
        parseMap.put(39, "*");
        parseMap.put(40, "+");
        parseMap.put(41, "-");
        parseMap.put(42, ".");
        parseMap.put(43, "/");
        parseMap.put(44, ":");
    }

    private Function<DataBlock, String> createMoveFunction(int startdr, int startdc, int enddr, int enddc, Function<int[][], int[][]> convertFunction) {
        return dataBlock -> {
            dataBlock.moveStartCoordinate(startdr, startdc);
            dataBlock.moveEndCoordinate(enddr, enddc);
            return parseMap.get(converter.applyTransform(convertFunction).apply(dataBlock.readQRArray()));
        };
    }

    private Function<DataBlock, Integer> createMoveFunctionInt(int startdr, int startdc, int enddr, int enddc, Function<int[][], int[][]> convertFunction) {
        return dataBlock -> {
            dataBlock.moveStartCoordinate(startdr, startdc);
            dataBlock.moveEndCoordinate(enddr, enddc);
            return converter.applyTransform(convertFunction).apply(dataBlock.readQRArray());
        };
    }

    // dataBlock을 받아서 위치를 옮기고, 그 위치의 값을 읽어 String으로 parse
    public Function<DataBlock, String> upFunction = createMoveFunction(-4, 0, -4, 0, converter.convertUpToInt);
    public Function<DataBlock, String> upAfterCWFunction = createMoveFunction(-4, 0, -2, -2, converter.convertUpToInt);
    public Function<DataBlock, String> upSpaceFunction = createMoveFunction(-5, 0, -5, 0, converter.convertUpToInt);
    public Function<DataBlock, String> downFunction = createMoveFunction(4, 0, 4, 0, converter.convertDownToInt);
    public Function<DataBlock, String> downAfterCCWFunction = createMoveFunction(2, 0, 4, -2, converter.convertDownToInt);
    public Function<DataBlock, String> downSpaceFunction = createMoveFunction(5, 0, 5, 0, converter.convertDownToInt);
    public Function<DataBlock, String> CCWFunction = createMoveFunction(-2, -2, -4, 0, converter.convertCCWToInt);
    public Function<DataBlock, String> CWFunction = createMoveFunction(4, -2, 2, 0, converter.convertCWToInt);
    public Function<DataBlock, Integer> lengthFunction = createMoveFunctionInt(-4, 0, -2, 0, converter.convertUpToInt);


    private List<Function<DataBlock, String>> decodeStep = new ArrayList<>(List.of(upFunction, CCWFunction, downAfterCCWFunction, downFunction, CWFunction,
            upAfterCWFunction, upFunction, CCWFunction, downAfterCCWFunction, downFunction, CWFunction, upAfterCWFunction, upFunction, upFunction,
            upSpaceFunction, CCWFunction, downAfterCCWFunction, downSpaceFunction, downFunction, downFunction));

    public String resultDecode() {
        DataBlock dataBlock = new DataBlock(new int[]{19, 19}, new int[]{20, 20});
        int start = converter.applyTransform(converter.convertStartToInt).apply(dataBlock.readQRArray());

        DataBlock endDataBlock = new DataBlock(new int[]{19, 9}, new int[]{20, 10});
        int end = converter.applyTransform(converter.convertEndToInt).apply(endDataBlock.readQRArray());
        if (!(start == 12 & end == 6)) {
            throw new IllegalArgumentException("잘못된 QR입니다.");
        }
        int length = lengthFunction.apply(dataBlock);

        return decodeStep.stream().map(step -> step.apply(dataBlock))
                .limit(length)
                .reduce("", (a, b) -> a + b);
    }

    private String errorDecode() {
        DataBlock[] errorDataBlocks = new DataBlock[]{new DataBlock(new int[]{9, 7}, new int[]{12, 8}), new DataBlock(new int[]{9, 4}, new int[]{12, 5}),
                new DataBlock(new int[]{9, 2}, new int[]{12, 3}), new DataBlock(new int[]{9, 0}, new int[]{12, 1})};

        StringBuilder sb = new StringBuilder();

        sb.append("0x");
        for (int i = 0; i < errorDataBlocks.length; i++) {
            if (i % 2 == 0) {
                sb.append(String.format("%2X", converter.applyTransform(converter.convertUpToInt).apply(errorDataBlocks[i].readQRArray())));
            } else {
                sb.append(String.format("%2X", converter.applyTransform(converter.convertDownToInt).apply(errorDataBlocks[i].readQRArray())));
            }
        }
        return sb.toString();
    }

    public void decode() {
        String result = "";
        String error = "";
        try {
            result = resultDecode();
            error = errorDecode();
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 QR 입니다." + e);
            result = "";
            error = "";
        }

        String[] QRResult = new String[]{result, error};

        System.out.println("data = " + QRResult[0]);
        System.out.println("error = " + QRResult[1]);
    }
}
