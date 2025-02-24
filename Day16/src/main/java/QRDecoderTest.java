public class QRDecoderTest {
    public static void main(String[] args) {
        QRDecoder decoder = new QRDecoder();

        ArrayDecoder.setQRArray();
        decoder.decode();
    }
}
