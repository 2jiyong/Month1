public class Test {
    public static void main(String[] args) {
        FileSystem fileSystem=FileSystem.getInstance(1000);
        fileSystem.makeDirectory("/","hello");

//        String currentDir = System.getProperty("user.dir");
//        System.out.println("현재 실행 중인 디렉토리: " + currentDir);
    }
}
