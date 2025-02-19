public class Section {
    private int[][] section;
    private int rowIndex;
    private int colIndex;

    public Section(){
        section = new int[3][3];
    }

    public int[][] getSection(){
        return section;
    }

    public void setRowIndex(int number){
        rowIndex = number;
    }
    public void setColIndex(int number){
        colIndex = number;
    }

    public void addNumber(int number){
        section[rowIndex][colIndex] = number;
    }

    public boolean hasValue(){
        if (section[rowIndex][colIndex]== 0) return false;
        return true;
    }

    public boolean isValidRowIndex(int indexNumber){
        for(int i =0; i<3 ; i++){
            //하나라도 빈칸이면 true (입력가능)
            if(section[indexNumber][i] == 0){
                return true;
            }
        }
        return false;
    }

    public boolean isValidColIndex(int indexNumber){
        for(int i =0; i<3 ; i++){
            if(section[i][indexNumber] == 0){
                return true;
            }
        }
        return false;
    }

    public boolean isValidIndex(int Index){
        return !hasValueInLocation(rowIndex,Index);
    }



    public int getRowIndex() {
        return rowIndex;
    }
    public int getColIndex() {
        return colIndex;
    }

//    public void showSection(){
//        for(int[] numbers : section){
//            for(int number : numbers){
//                System.out.print(number);
//            }
//            System.out.println();
//        }
//    }

    public void showLine(int lineIndex){
        for(int number : section[lineIndex]){
            System.out.print(number+" ");
        }
    }

    public boolean hasValueInLocation(int row, int col){
        return section[row][col]!=0;
    }
}
