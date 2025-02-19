public class CSVTest {
    public static void main(String[] args) {
        Query.executeQuery("CREATE TABLE billboard (singer String, year Numeric, song String)");

        Query.executeQuery("INSERT INTO billboard (singer, year, song) VALUES (\"BTS\", 2020, \"Dynamite\")");
        Query.executeQuery("INSERT INTO billboard (singer, year, song) VALUES (\"빅뱅\", 2019, \"Loser\")");
        

    }
}
    