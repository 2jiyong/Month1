import java.util.Scanner;

public class Query {
    private FileSystem fileSystem;

    public Query(FileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

    public void getQuery(){
        Scanner sc = new Scanner(System.in);
        String result = "";
        System.out.println("저장된 파일 시스템이 없습니다.\n파일 시스템의 최대 크기를 입력해 주세요.");
        String size = sc.nextLine();
        System.out.println(size+" 파일 시스템의 초기화를 완료했습니다.");
        try {
            while (true) {
                System.out.print("my-vfs>");
                String query = sc.nextLine();
                if (query.equals("q") || query.equals("Q")) {
                    break;
                }
                String[] values = query.split(" ");
                if (values[0].equals("list")) {
                    //디렉토리 생성
                    System.out.println(fileSystem.readDirectory(values[1]));
                } else if (values[0].equals("makedir")) {
                    //파일 생성
                    fileSystem.makeDirectory(values[1], values[2]);
                    System.out.println(values[1]+" 디렉토리 아래 "+values[2]+"를 생성합니다.");
                } else if (values[0].equals("create")) {
                    //파일 생성
                    values = query.split(" ",4);
                    fileSystem.makeFile(values[1], values[2], values[3]);
                    System.out.println(values[1]+" 디렉토리 아래 "+values[2]+" 파일을 생성했습니다.");
                } else if (values[0].equals("read")) {
                    //파일 읽기
                    System.out.println(fileSystem.readFile(values[1]));
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
