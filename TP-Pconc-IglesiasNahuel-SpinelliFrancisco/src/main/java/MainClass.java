import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

    ThreadPool threadPool;
    Barrier barrier;
    List<BigInteger> numberList;

    public MainClass(List<BigInteger> listToWorkWith, Integer numberOfThreads){

        barrier = new Barrier(numberOfThreads);
        threadPool =  new ThreadPool(this.barrier, 10, numberOfThreads);

        numberList = listToWorkWith;
    }

    public void runMain(){

        long startTime = System.nanoTime();

        threadPool.runWorkers();
        threadPool.loadData(numberList);

        barrier.waitBarrier();

        long endTime = System.nanoTime();
        long executionDuration = (endTime - startTime) / 1000000;

        System.out.println("tiempo de ejecucion total: " + executionDuration + " ms");
    }

    public static void main(String[] args) {

        ArrayList<BigInteger> listOfNumbers = crearLista();

        MainClass mainClass = new MainClass(listOfNumbers,200);

        mainClass.runMain();
    }

    private static ArrayList<BigInteger> crearLista() {

        ArrayList<BigInteger> numerosPerfectos = new ArrayList<BigInteger>();
        //el 6 y el 28 (los 2 primeros numeros perfectos) son agregados en el for loop
        numerosPerfectos.add(BigInteger.valueOf(496));
        numerosPerfectos.add(BigInteger.valueOf(8128));
        numerosPerfectos.add(BigInteger.valueOf(33550336));
        numerosPerfectos.add(new BigInteger("8589869056"));
        numerosPerfectos.add(new BigInteger("137438691328"));

        for(Integer i=0; i < 195; i++){
                numerosPerfectos.add(BigInteger.valueOf(i));
        }

        Collections.shuffle(numerosPerfectos);
        return numerosPerfectos;
    }
}
