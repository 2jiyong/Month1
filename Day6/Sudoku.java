public class Sudoku {
    Section[] sudokuBoard = new Section[9];

    public Sudoku() {
        for (int i = 0; i < 9; i++) {
            Section section = new Section();
            sudokuBoard[i] = section;
        }
    }

    public void addNumberToSections(int number){
        while (true){
            giveSectionRowIndexNumber(0);

            boolean noProblem = true;
            for(Section section : sudokuBoard){
                if(section.hasValue()) noProblem = false;
            }

            if(!noProblem) continue;
            else {
                for(Section section : sudokuBoard){
                    section.addNumber(number);
                }
                break;
            }
        }
    }

    // sudokuBoard 순회해가며 indexnumber추가
    public boolean giveSectionColIndexNumber(int startSection){
        // index를 추가가능한지 확인하기
        if (startSection==3){
            return true;
        }
        RandomIndex randomIndex = new RandomIndex();

        int[] randomIndexArray = new int[3];
        boolean canAddIndex = false;
        while (!canAddIndex){
            // cnt는 addindex 가능한 섹션 수, 3개가 되어야 함.
            int cnt = 0 ;
            //randomIndexArray에는 예) {1,2,0}
            //하나를 빼옴
            randomIndexArray = randomIndex.popLastIndexOrder();
            for(int i =0; i<3;i++){
                //colIndex 추가가 가능할때까지 뽑음
                if (sudokuBoard[startSection+i*3].isValidColIndex(randomIndexArray[i])) cnt++;
            }
            if (cnt==3) {
                canAddIndex = true;
                for (int i = 0; i < 3; i++) {
                    sudokuBoard[startSection + i*3].setColIndex(randomIndexArray[i]);
                }
                boolean result  = giveSectionColIndexNumber(startSection+1);
                if (result){
                    return true;
                }
                canAddIndex = false;
                for (int i = 0; i < 3; i++) {
                    sudokuBoard[startSection + i*3].setColIndex(0);
                }
            }
        }
        return false;
        // 3개의 섹션에 rowIndex 추가
    }

    public boolean giveSectionRowIndexNumber(int startSection){
        if (startSection==9){
            if(giveSectionColIndexNumber(0)) return true;
            return false;
        }
        RandomIndex randomIndex = new RandomIndex();
        // index를 추가가능한지 확인하기
        int[] randomIndexArray = new int[3];
        boolean canAddIndex = false;
        while (!canAddIndex){
            // cnt는 addindex 가능한 섹션 수, 3개가 되어야 함.
            int cnt = 0 ;
            //randomIndexArray에는 예) {1,2,0}
            //하나를 빼옴
            randomIndexArray = randomIndex.popLastIndexOrder();
            for(int i =0; i<3;i++){
                //rowIndex 추가가 가능할때까지 뽑음
                if (sudokuBoard[startSection+i].isValidRowIndex(randomIndexArray[i])) cnt++;
            }

            if (cnt==3) {
                canAddIndex = true;
                for (int i = 0; i < 3; i++) {
                    sudokuBoard[startSection + i].setRowIndex(randomIndexArray[i]);
                }
                boolean result  = giveSectionRowIndexNumber(startSection+3);
                if (result){
                    return true;
                }
                canAddIndex = false;
                for (int i = 0; i < 3; i++) {
                    sudokuBoard[startSection + i].setRowIndex(0);
                }
            }
        }
        return false;
    }

    public void showSudokuBoard(){
        show3Sections(0);
        show3Sections(3);
        show3Sections(6);
    }

    public void show3Sections(int startIndex){
        for (int line = 0 ; line <3 ; line++){
            for(int i = startIndex; i<startIndex+3; i++){
                sudokuBoard[i].showLine(line);
            }
            System.out.println();
        }
    }

//    public String show3SectionsString(int startIndex){
//        StringBuilder sb = new StringBuilder();
//        for (int line = 0 ; line <3 ; line++){
//            for(int i = startIndex; i<startIndex+3; i++){
//                sb.append(sudokuBoard[i].showLine(line));
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }

//    public String showSudokuBoardString(){
//        StringBuilder sb = new StringBuilder();
//        sb.append(show3SectionsString(0));
//        sb.append(show3SectionsString(3));
//        sb.append(show3SectionsString(6));
//
//        return sb.toString();
//    }





//    public int[][] copyRow(int startRow,int endRow){
//        int[][] copiedRow = new int[3][9];
//        for (int i = 0; i<3; i++){
//            System.arraycopy(sudokuBoard);
//        }
//    }

}
