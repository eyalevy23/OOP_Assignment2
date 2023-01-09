import java.io.IOException;
import java.io.*;

class MyThread extends Thread {
    String file;
    int lineCount;

    MyThread(String file){
        this.file = file;
    }

    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.close();            
        } catch(IOException  e){
            e.printStackTrace();
        }  
    }
}