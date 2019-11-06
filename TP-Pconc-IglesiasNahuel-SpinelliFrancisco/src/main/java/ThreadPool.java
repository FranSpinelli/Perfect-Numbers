import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

public class ThreadPool {

    private Barrier barrier;
    private Buffer buffer;
    private Integer threadsToCreate;

    public ThreadPool(Barrier aBarrier, Buffer aBuffer, Integer aQuantityOfThreads){

        barrier = aBarrier;
        buffer = aBuffer;
        threadsToCreate = aQuantityOfThreads;
    }

    public void runWorkers(){

        for(Integer i = 0; i < threadsToCreate; i++){

            new PerfectWorker(buffer,barrier).start();
        }
    }

    public void loadData(List<BigInteger> data){

        Iterator<BigInteger> dataIterator = data.iterator();
        while(dataIterator.hasNext()){

            buffer.push(dataIterator.next());

        }

        this.loadNegativeNumbers();
    }

    private void loadNegativeNumbers(){

        for(Integer i=0; i < threadsToCreate; i++){

            buffer.push(BigInteger.valueOf(-1));
        }
    }
}
