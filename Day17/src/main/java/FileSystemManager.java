import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemManager {



    public static boolean readDataToFindFile(String path){
        String metaDataFile = "src/main/java/myfs.dat";
        try (BufferedReader br = new BufferedReader(new FileReader(metaDataFile))) {
            String line;
            //헤더 제거
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 쉼표로 분리
                if (values[1].equals(path)) return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createDirectory(String path){
        String metaDataFile = "src/main/java/myfs.dat";
        Path parent = Paths.get(path).getParent();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(metaDataFile,true))) {
            bw.write("Directory");
            bw.write(",");
            bw.write(path);
            bw.write(",");
            bw.write("0");
            bw.write(",");
            if(parent ==null) bw.write("");
            else bw.write(parent.toString().replace("\\","/"));
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("오류");
        }
    }

    public static void createFile(String path){
        String metaDataFile = "src/main/java/myfs.dat";
        Path parent = Paths.get(path).getParent();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(metaDataFile,true))) {
            bw.write("File");
            bw.write(",");
            bw.write(path);
            bw.write(",");
            bw.write("0");
            bw.write(",");
            if(parent ==null) bw.write("");
            else bw.write(parent.toString().replace("\\","/"));
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 생성 오류");
        }
    }

    public static void createFileData(String path,String data){
        String dataFile = "src/main/java/myfs.info";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile,true))) {
            bw.write(path);
            bw.write(",");
            bw.write(data);
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 데이터 생성 오류");
        }
    }

    public static String readFileData(String path) {
        String dataFile = "src/main/java/myfs.info";
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 쉼표로 분리
                if (values[0].equals(path)) return values[1];
            }
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public static String readDirectoryData(String path) {
        String dataFile = "src/main/java/myfs.dat";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",",-1); // 쉼표로 분리
                // 부모가 path 인 경우에 추가
                if (values[3].equals(path)) sb.append(values[0]+" ").append(values[1]).append("\n");
            }
            if(sb.isEmpty()) sb.append("파일이 없습니다.");
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
