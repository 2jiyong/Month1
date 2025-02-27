

public class Progress extends Thread{

    @Override
    public void run(){
        printProgress();
    }

    public void printProgress(){
        for(int i = 0 ; i <=10;i++){
            try {
                System.out.print("\r"+progressPercentage(i)+"   ");
                System.out.flush();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    public String progressPercentage(int percent){
        String on = "█";
        String off = "▁";
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<percent; i++){
            sb.append(on);
            sb.append(on);
        }
        for(int i = 0; i<10-percent;i++){
            sb.append(off);
            sb.append(off);
        }
        sb.append(" 화성까지 여행 ").append(percent*10).append("%");

        return sb.toString();
    }


}
