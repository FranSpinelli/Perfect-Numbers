import java.math.BigInteger;

public class PerfectWorker extends Thread {

    private Buffer buffer;

    public PerfectWorker(Buffer unBuffer) {

        this.buffer = unBuffer;
    }

    public void run() {
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