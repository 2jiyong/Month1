import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemManager {
    public void init(int size){

    }


//    public boolean isDirectoryExist(String path){
//
//    }

    // 해당 경로로 directory 가 있는지 확인
    public static boolean readDataToFindDirectory(String path){
        String metaDataFile = "src/main/java/myfs.dat";
        try (BufferedReader br = new BufferedReader(new FileReader(metaDataFile))) {
            String line;
            //헤더 제거
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 쉼표로 분리
                if (values[1]==path) return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void writeData(String path){
        String metaDataFile = "src/main/java/myfs.dat";
        Path parent = Paths.get(path).getParent();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(metaDataFile,true))) {
            bw.write("Directory");
            bw.write(",");
            bw.write(path);
            bw.write(",");
            bw.write("0");
            bw.write(",");
            bw.write(parent.toString().replace("\\","/"));
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("오류");
        }
    }
}
