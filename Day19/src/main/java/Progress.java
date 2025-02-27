

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
        String on = "\033[32m█\033[0m";
        String off = "\033[32m▁\033[0m";
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<percent; i++){
            sb.append(on);
            sb.append(on);
        }
        for(int i = 0; i<10-percent;i++){
            sb.append(off);
            sb.append(off);
        }
        sb.append(" \033[31m화성\033[0m까지 여행 ").append("\033[32m"+percent*10+"\033[0m").append("%");

        return sb.toString();
    }


}
