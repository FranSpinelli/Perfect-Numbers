public class Barrier {

    private Integer threadsWaiting;
    private Integer threadsToWait;

    public Barrier(Integer n){

        this.threadsToWait = n;
        this.threadsWaiting = 0;
    }

    public synchronized void waitBarrier(){

        this.threadsWaiting ++;
        while(this.threadsWaiting <= this.threadsToWait){

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
