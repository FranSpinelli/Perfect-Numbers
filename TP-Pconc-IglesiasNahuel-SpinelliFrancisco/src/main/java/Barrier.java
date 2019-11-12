public class Barrier {

    private Integer finishedThreads;
    private Integer threadsToWait;

    public Barrier(Integer n){

        this.threadsToWait = n;
        this.finishedThreads = 0;
    }

    public synchronized void waitBarrier(){

        finishedThreads++;
        while(this.finishedThreads < this.threadsToWait + 1){

            try{
                this.wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
    }
}
