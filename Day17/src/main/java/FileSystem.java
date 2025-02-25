public class FileSystem {
    private static FileSystem instance;
    private int size;

    private FileSystem(int size){
        this.size = size;
    }

    public FileSystem getInstance(int size){
        if(instance != null){
            instance = new FileSystem(size);
        }
        return instance;
    }

    public void makeDirectory(String path, String name){
        String abPath = path + name;
        if (!FileSystemManager.readDataToFindDirectory(abPath)){
            //디렉토리 생성
        }
    }
}
