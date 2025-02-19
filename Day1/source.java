import java.util.Arrays;

public class source{
    public static void main(String[] args){
        Converter myConverter = new Converter();
        boolean[] answer = myConverter.dec2bin(173);
        System.out.println(Arrays.toString(answer));
        System.out.println(myConverter.bin2dec(answer));
    }
}