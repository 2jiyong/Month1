import java.util.ArrayList;
import java.util.List;

public class ComputerMove {
    private final List<Table> tableList = new ArrayList<>();
    private int stepNumber = 1 ;

    public ComputerMove(){
        tableList.add(new Table(1));
        tableList.add(new Table(2));
        tableList.add(new Table(3));
        initComputerMove();
    }

    public void showStepState(int step){
        initComputerMove();
        moveStep(3,tableList.get(0),tableList.get(1),tableList.get(2),step);
        showResultString();
    }

    private void initComputerMove(){
        for(Table table : tableList){
            table.resetTable();
        }
        makeComputerPartToTable(tableList.getFirst());
        stepNumber = 1;
    }

    private void makeComputerPartToTable(Table table){
        table.addComputerPart(ComputerPart.COMPUTE);
        table.addComputerPart(ComputerPart.DISPLAY);
        table.addComputerPart(ComputerPart.DRV);
    }

    private void moveComputerPart(Table fromTable, Table toTable){
        fromTable.moveComputerPartToTable(toTable);
    }

    private void moveStep(int partNumberToMove, Table fromTable,Table midTable,Table toTable,int targetStep){
        if (stepNumber>=targetStep){
            return;
        }
        if (partNumberToMove==1){
            moveComputerPart(fromTable,toTable);
            stepNumber ++;
            return;
        }

        moveStep(partNumberToMove-1,fromTable,toTable,midTable,targetStep);

        if (stepNumber<targetStep){
            moveComputerPart(fromTable,toTable);
            stepNumber++;
        }

        moveStep(partNumberToMove-1,midTable,fromTable,toTable,targetStep);
    }

    private void showResultString(){
        System.out.println("Step "+ stepNumber);
        for(int line = 0 ; line < 9; line++){
            for(Table table : tableList){
                System.out.print(table.getComputerPartStringLine(line)+" ");
            }
            System.out.println();
        }
        System.out.println("1======== 2======== 3========\n");
    }
}

