import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

    private ThreadPool threadPool;
    private Barrier barrier;
    private Buffer buffer;

    private List<BigInteger> numberList;
    private Integer numberOfThreadsToCreate;

    public MainClass(List<BigInteger> listToWorkWith, Integer numberOfThreads, Integer aSize) {

        this.barrier = new Barrier(numberOfThreads);
        this.buffer = new Buffer(aSize);
        this.threadPool = new ThreadPool(this.barrier, this.buffer, numberOfThreads);

        this.numberList = listToWorkWith;
        this.numberOfThreadsToCreate = numberOfThreads;
    }

    public MyPair<List<BigInteger>, Long> runMain() {

        long startTime = System.nanoTime();
        MyPair<List<BigInteger>, Long> resolution = new MyPair();

        threadPool.runWorkers();
        threadPool.loadData(numberList);

        barrier.waitBarrier(); // espero que todos hayan procesado los datos

        List<BigInteger> finalList= recolectarDatos();

        //barrier.waitBarrier(); // espero que todos hayan terminado de pushear los datos

        //System.out.println("Numeros perfectos de la lista recibida: \n\n" + finalList);

        long endTime = System.nanoTime();
        long executionDuration = (endTime - startTime) / 1000000;

        //System.out.println("\ntiempo de ejecucion total: " + executionDuration + " ms");

        resolution.setX(finalList);
        resolution.setY(executionDuration);

        return resolution;
    }

    private ArrayList<BigInteger> recolectarDatos() {

        ArrayList<BigInteger> generatedList = new ArrayList<BigInteger>();
        Integer threadsThatFinishedToUploadData = 0;

        while(threadsThatFinishedToUploadData < numberOfThreadsToCreate){

            BigInteger numberPopped = this.buffer.pop();
            if(numberPopped.compareTo(BigInteger.valueOf(0)) == 0){

                threadsThatFinishedToUploadData++;
            }else{

                generatedList.add(numberPopped);
            }
        }

        return generatedList;
    }

//--------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        ArrayList<BigInteger> listOfNumbers = createList();
        MyPair<List<BigInteger>, Long> mainResult;

        MainClass mainClass = new MainClass(listOfNumbers, 20, 50);

        mainResult = mainClass.runMain();
        System.out.println("Numeros perfectos de la lista recibida: \n\n" + mainResult.getX());
        System.out.println("\ntiempo de ejecucion total: " + mainResult.getY() + " ms");
    }

    private static ArrayList<BigInteger> createList() {

        ArrayList<BigInteger> listToWorkWith = new ArrayList<BigInteger>();
        //el 6 y el 28 (los 2 primeros numeros perfectos) son agregados en el for loop
        listToWorkWith.add(BigInteger.valueOf(496));
        listToWorkWith.add(BigInteger.valueOf(8128));
        listToWorkWith.add(BigInteger.valueOf(33550336));
        listToWorkWith.add(new BigInteger("8589869056"));
        listToWorkWith.add(new BigInteger("137438691328"));

        for (Integer i = 0; i < 195; i++) {
            listToWorkWith.add(BigInteger.valueOf(i));
        }

        Collections.shuffle(listToWorkWith);
        return listToWorkWith;
    }
}