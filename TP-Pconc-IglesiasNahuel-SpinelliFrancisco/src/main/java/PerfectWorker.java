import java.math.BigInteger;

public class PerfectWorker extends Thread {

    private Buffer buffer;

    public PerfectWorker(Buffer unBuffer) {

        this.buffer = unBuffer;
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
                //TODO: agregar Barrier
                //TODO: como matar thread
            }
        }
    }

    public Boolean isPerfect(BigInteger aNumber) {

        {
            // To store sum of divisors
            BigInteger sum = BigInteger.valueOf(1);

            // Find all divisors and add them
            for (BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(aNumber) <= 0; i = i.add(BigInteger.valueOf(1))) {

                if (aNumber.mod(i).equals(BigInteger.valueOf(0))) {

                    if (!i.multiply(i).equals(aNumber))
                        sum = sum.add(i).add(aNumber.divide(i));
                    else
                        sum = sum.add(i);
                }
            }
            // If sum of divisors is equal to
            // n, then n is a perfect number
            return sum.equals(aNumber) && !aNumber.equals(BigInteger.valueOf(1));
        }
    }
}