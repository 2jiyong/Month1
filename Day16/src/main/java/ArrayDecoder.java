import java.util.function.Consumer;

public class ArrayDecoder {
    public static int[][] QRArray =  new int[21][21];

    public static void setQRArray(){
        String[] QR ={
                "111111101000001111111",
                "100000101000001000001",
                "101110100000001011101",
                "101110100000001011101",
                "101110101000001011101",
                "100000101000001000001",
                "111111101010101111111",
                "000000000000000000000",
                "111010101000011101011",
                "110001000000000000101",
                "010011100000000000100",
                "000000001000000000001",
                "001100101000000000001",
                "000000001000000000100",
                "111111100000000001100",
                "100000101000000000010",
                "101110100000000000110",
                "101110100000000000100",
                "101110101000000000100",
                "100000101100000000000",
                "111111101010000110011"
        };
        for(int i = 0 ; i<21; i++){
            for (int j = 0; j<21;j++){
                QRArray[i][j]= Character.getNumericValue(QR[i].charAt(j));
            }
        }
    }

    public ArrayDecoder(String[] QR){
        for(int i = 0 ; i<21; i++){
            for (int j = 0; j<21;j++){
                QRArray[i][j]= QR[i].charAt(j);
            }
        }
    }

    public static Consumer<DataBlock> upFunction = dataBlock -> {
        dataBlock.moveStartCoordinate(-4,0);
        dataBlock.moveEndCoordinate(-4,0);
    };



}
