import java.util.Random;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class Ex2_1{

    public static String[] createTextFiles(int n, int seed, int bound){
        int numberOfLines;
        String str = "eyal and ori";
        String[] filesName = new String[n];
        String file;

        for (int i = 1; i <= n; i++) {
            numberOfLines = random(seed,bound);
            file = "file_" + i + ".txt";
            writeOnFile(file, str, numberOfLines);
            filesName[i-1] = file;
        }
        return filesName;
    }

    public static void writeOnFile(String file,String str, int row){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int j = 0; j < row; j++) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch(IOException  e){
            e.printStackTrace();
        }
    }


    public static int getNumOfLines(String[] fileNames){
        String file;
        int lineCount = 0;
        for(int i = 0; i < fileNames.length; i++){
            file = fileNames[i];
            lineCount += readLines(file);
        }
        return lineCount;
    }

    public static int readLines(String file){
        int lineCount = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.close();
        } catch(IOException  e){
            e.printStackTrace();
        }
        return lineCount;
    }

    public int getNumOfLinesThreads(String[] fileNames){
        Queue<MyThread> threads = new LinkedList<>();
        MyThread thread;
        int count = 0;
        for(int i = 0; i < fileNames.length; i++){
            thread = new MyThread(fileNames[i]);
            thread.start();
            threads.add(thread);
        }
        for(int i = 0; i < fileNames.length; i++){
            thread = threads.poll();
            try{
                thread.join();
                count += thread.lineCount;
            }catch(Exception e){}
        }
        return count;
    }

    public int getNumOfLinesThreadPool(String[] fileNames){
        // Create an ExecutorService with a fixed thread pool
        ExecutorService threadPool = Executors.newFixedThreadPool(25);
    
        // Create an array of Future objects
        Future<Integer>[] futures = new Future[fileNames.length];
    
        // Submit a task for each element in the fileNames array
        for (int i = 0; i < fileNames.length; i++) {
            futures[i] = threadPool.submit(new ThreadPoolNumOfLines(fileNames[i]));
        }
    
        // Wait for all tasks to complete and retrieve the results
        int result = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try {
                result += futures[i].get();
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
        // Shutdown the thread pool
        threadPool.shutdown();
        return result;
    }
    
    public static int random(int seed, int bound){
        Random rand = new Random(seed);
        return rand.nextInt(bound) + 1;
    }


}