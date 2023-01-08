import java.util.Arrays;
public class Main {
    public static void main(String[] args) {

        long startTime, endTime, elapsedTime;

        String[] names = Ex2_1.createTextFiles(500, 2, 10000);
        // System.out.println(Arrays.toString(names));
        System.out.println();
        
        //first without thread
        startTime = System.currentTimeMillis();
        int numOfLinesInTotal1 = Ex2_1.getNumOfLines(names);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        printTimes(numOfLinesInTotal1, elapsedTime);

        //second round with thread
        Ex2_1 therdsExmamle = new Ex2_1();
        startTime = System.currentTimeMillis();
        int numOfLinesInTotal2 = therdsExmamle.getNumOfLinesThreads(names);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        printTimes(numOfLinesInTotal2, elapsedTime);

        //thired round with threadpool
        startTime = System.currentTimeMillis();
        int numOfLinesInTotal3 = therdsExmamle.getNumOfLinesThreadPool(names);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        printTimes(numOfLinesInTotal3, elapsedTime);
    }


    public static void printTimes(int numOfLines, long elapsedTime){
        System.out.println("num of lines : " + numOfLines);
        System.out.println("time : "+ elapsedTime + " (milliseconds)");
        System.out.println();
    }

}


/*
        # measure time in milliseconds
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
 */