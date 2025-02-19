import java.util.ArrayList;
import java.util.List;

public class Table {
    private final int number;
    private final List<ComputerPart> computerPartList = new ArrayList<>();

    public Table(int number){
        this.number = number;
    }

    public void resetTable(){
        computerPartList.clear();
    }

    public void moveComputerPartToTable(Table table){
        table.addComputerPart(removeComputerPart());
    }

    public void addComputerPart(ComputerPart computerPart){
        computerPartList.add(computerPart);
    }

    private ComputerPart removeComputerPart(){
        return computerPartList.removeLast();
    }

    public int getNumber(){
        return number;
    }

    public String getComputerPartStringLine(int lineNumber){
        String[] stringLines = makeComputerPartString();
        return stringLines[lineNumber];
    }

    private String[] makeComputerPartString(){
        String BLANK = "         ";
        String[] originString = {
                BLANK,
                BLANK,
                BLANK,
                BLANK,
                BLANK,
                BLANK,
                BLANK,
                BLANK,
                BLANK,
        };
        int lineIndex = originString.length-1;
        for(ComputerPart computerPart : computerPartList){
            for(int i = 0; i< computerPart.getHeight(); i++,lineIndex--){
                originString[lineIndex]= computerPart.getStringLine(i);
            }
        }
        return originString;
    }
}
