public class Test {
    public static void main(String[] args) {
        FileSystem fileSystem=FileSystem.init(1000);
        Query query = new Query(fileSystem);
        query.getQuery();
    }
}
