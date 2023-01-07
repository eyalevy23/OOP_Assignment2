
import java.io.IOException;
import java.io.*;
import java.util.concurrent.Callable;


public class ThreadPoolNumOfLines implements Callable<Integer> {
    String file;
    int lineCount = 0;

    ThreadPoolNumOfLines(String file){
        this.file = file;
    }

    @Override
    public Integer call() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.readLine() != null) {
            lineCount++;
        }
        reader.close();
        return lineCount;
    }
}
