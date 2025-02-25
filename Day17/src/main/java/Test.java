public class Test {
    public static void main(String[] args) {
        FileSystem fileSystem=FileSystem.init(1000);
        fileSystem.makeDirectory("/","hello");
        fileSystem.makeFile("/hello","world.txt","hello!!");
        System.out.println(fileSystem.readFile("/hello/world.txt"));
        System.out.println(fileSystem.readDirectory("/hello"));

    }
}
