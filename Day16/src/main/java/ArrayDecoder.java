
import java.util.function.Consumer;
import java.util.function.Function;

public class ArrayDecoder {
    public static int[][] QRArray=  new int[21][21];

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
