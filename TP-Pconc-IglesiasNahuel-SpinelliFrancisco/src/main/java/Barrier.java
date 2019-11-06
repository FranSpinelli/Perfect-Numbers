public class Barrier {

    private Integer finishedThreads;
    private Integer threadsToWait;

    public Barrier(Integer n){

        this.threadsToWait = n;
        this.finishedThreads = 0;
    }

    public synchronized void waitBarrier(){

        while(this.finishedThreads < this.threadsToWait){

            try{
                this.wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //System.out.println("Barrera destrabada");
    }

    public synchronized void notifyBarrier(){

        this.finishedThreads ++;
        this.notify();
    }
}
