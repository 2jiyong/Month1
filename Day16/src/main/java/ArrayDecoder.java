public class ArrayDecoder {
    private int[][] QRArray=  new int[21][21];

    public ArrayDecoder(String[] QR){
        for(int i = 0 ; i<21; i++){
            for (int j = 0; j<21;j++){
                QRArray[i][j]= QR[i].charAt(j);
            }
        }
    }




}
