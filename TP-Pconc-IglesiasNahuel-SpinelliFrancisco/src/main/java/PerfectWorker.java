import java.math.BigInteger;

public class PerfectWorker extends Thread {

    private Buffer buffer;
    private Barrier barrier;

    public PerfectWorker(Buffer aBuffer, Barrier aBarrier) {

        this.buffer = aBuffer;
        this.barrier = aBarrier;
    }

    public void run() {

        Boolean keep_running = true;

        while(keep_running){
            BigInteger bigNumber = this.buffer.pop();

            if(bigNumber.compareTo(BigInteger.valueOf(0)) >= 0){

                if(this.isPerfect(bigNumber)){
                    System.out.println(bigNumber + " is a Perfect Number");
                }

            } else {
                keep_running = false;
                barrier.notifyBarrier();
                return;
            }
        }
    }

    private Boolean isPerfect(BigInteger aNumber) {

            //Guarda la suma de los divisores
            BigInteger sum = BigInteger.valueOf(1);

            // Encuentra todos los divisores y los suma
            for (BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(aNumber) <= 0; i = i.add(BigInteger.valueOf(1))) {

                if (aNumber.mod(i).equals(BigInteger.valueOf(0))) {

                    if (!i.multiply(i).equals(aNumber))
                        sum = sum.add(i).add(aNumber.divide(i));
                    else
                        sum = sum.add(i);
                }
            }
            // Si la suma de los divisores es n, n = numero perfecto
            return sum.equals(aNumber) && !aNumber.equals(BigInteger.valueOf(1));
    }
}