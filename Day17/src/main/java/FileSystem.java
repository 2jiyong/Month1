import java.io.File;

public class FileSystem {
    private static FileSystem instance;
    private int size;

    private FileSystem(int size){
        this.size = size;
    }

    public static FileSystem init(int size){
        if(instance == null){
            instance = new FileSystem(size);
        }
        instance.makeDirectory("","");
        return instance;
    }

    public void makeDirectory(String path, String name){
        String abPath = path +"/"+ name;
        if(path.equals("/")) abPath = "/"+name;
        if (!FileSystemManager.readDataToFindFile(abPath)){
            //디렉토리 생성
            FileSystemManager.createDirectory(abPath);
        }else{
            throw new IllegalArgumentException("이미 존재하는 디렉토리 입니다.");
        }
    }

    public void makeFile(String path, String name,String data){
        String abPath = path +"/"+ name;
        if (!FileSystemManager.readDataToFindFile(abPath)){
            //디렉토리 생성
            FileSystemManager.createFile(abPath);
            FileSystemManager.createFileData(abPath,data);
        }else{
            throw new IllegalArgumentException("이미 존재하는 파일 입니다.");
        }
    }

    public String readFile(String Path){
        if(!FileSystemManager.readDataToFindFile(Path)){
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        return FileSystemManager.readFileData(Path);
    }

    public String readDirectory(String Path){
        return FileSystemManager.readDirectoryData(Path);
    }

}
