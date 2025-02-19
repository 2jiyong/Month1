public enum ComputerPart {
    COMPUTE("COMPUTE",3, new String[] {
                "+-------+",    // 1번 줄
                "|COMPUTE|",    // 2번 줄
                "+-------+"     // 3번 줄
    }),
    DISPLAY("DISPLAY",5,new String[] {
            "+-------+",    // 1번 줄
            "|       |",    // 2번 줄
            "|DISPLAY|",    // 3번 줄
            "|       |",    // 4번 줄
            "+-------+"     // 5번 줄
    }),
    DRV("DRV",1,new String[] {
            "    [DRV]"
    });

    private final String name;
    private final int height;
    private final String[] lines;

    ComputerPart(String name, int height, String[] lines){
        this.name = name;
        this.height = height;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public int getHeight(){
        return height;
    }

    public String getStringLine(int number){
        return lines[number];
    }
}
