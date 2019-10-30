import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class Buffer {

    private Integer size;
    private BigInteger[] values;
    private Integer nextIn;
    private Integer nextOut;

    public Buffer(Integer unSize){

        this.size = unSize;
        this.values = new BigInteger[this.size + 1];

        this.nextIn = 0;
        this.nextOut = 0;
    }

    public synchronized void push(BigInteger n){

        while(this.isFull()){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        this.values[this.nextIn] = n;
        nextIn = this.next(nextIn);
        this.notifyAll();
    }

    public synchronized BigInteger pop(){

        BigInteger result;

        while(this.isEmpty()){
            try{
                this.wait();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = this.values[this.nextOut];
        this.nextOut = this.next(this.nextOut);
        notifyAll();

        return result;
    }

    private Boolean isEmpty(){

        return this.nextIn.equals(this.nextOut);
    }

    private Boolean isFull(){

        return this.next(this.nextIn).equals(this.nextOut);
    }

    private Integer next(Integer i){

        return (i+1) % (this.size+1);
    }
}
