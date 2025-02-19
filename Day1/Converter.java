public class Converter {
    public boolean[] dec2bin(int decimal) {
        boolean[] array = new boolean[8];
        int index = 0;
        while (decimal > 0){
            array[index] = decimal % 2 != 0;
            index++;
            decimal = decimal / 2;
        }
        boolean[] answer = new boolean[index];
        System.arraycopy(array, 0, answer, 0 ,index);
        return answer;
    }

    public int bin2dec(boolean[] binary) {
        int answer = 0;
        for (int i = 0; i<binary.length; i++){
            if (binary[i]){
                answer += (int) Math.pow(2, i);
            }
        }
        return answer;
    }
}

