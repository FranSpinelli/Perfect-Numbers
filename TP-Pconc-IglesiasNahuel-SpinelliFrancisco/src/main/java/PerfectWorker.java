import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PerfectWorker extends Thread {

    private Buffer buffer;
    private Barrier barrier;

    public PerfectWorker(Buffer aBuffer, Barrier aBarrier) {

        this.buffer = aBuffer;
        this.barrier = aBarrier;
    }

    public void run() {

        Boolean keep_running = true;
        ArrayList<BigInteger> perfectNumbersFound = new ArrayList<BigInteger>();

        while(keep_running){
            BigInteger bigNumber = this.buffer.pop();

            if(bigNumber.compareTo(BigInteger.valueOf(0)) >= 0){

                if(this.isPerfect(bigNumber)){

                    perfectNumbersFound.add(bigNumber);
                }

            } else {
                keep_running = false;
                barrier.waitBarrier(); //notifico que ya termine de procesar y empiezo a subir mis perfect numbers
                this.putPerfectNumberInBuffer(perfectNumbersFound);
                return;
            }
        }
    }

    private void putPerfectNumberInBuffer(List<BigInteger> aList) {

        Iterator<BigInteger> aListIterator = aList.iterator();
        while(aListIterator.hasNext()){

            buffer.push(aListIterator.next());
        }
        buffer.push(BigInteger.valueOf(0));
    }

    private Boolean isPerfect(BigInteger aNumber) {

            // Para almacenar la suma de los divisore
            BigInteger sum = BigInteger.valueOf(1);

            // Encuentra a los divisores y los suma
            for (BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(aNumber) <= 0; i = i.add(BigInteger.valueOf(1))) {

                if (aNumber.mod(i).equals(BigInteger.valueOf(0))) {

                    if (!i.multiply(i).equals(aNumber))
                        sum = sum.add(i).add(aNumber.divide(i));
                    else
                        sum = sum.add(i);
                }
            }
            // Si la suma de los divisores es igual a
            // n, n = numero perfecto
            return sum.equals(aNumber) && !aNumber.equals(BigInteger.valueOf(1));
    }
}