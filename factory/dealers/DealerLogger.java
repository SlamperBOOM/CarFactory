package factory.dealers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class DealerLogger {
    private static Writer writer;

    public static void openLogger(){
        try{
            writer = new OutputStreamWriter(new FileOutputStream("src/factory/dealers/log.log"));
        }catch (IOException e){

        }
    }

    public synchronized static void logDeal(String message){
        try {
            writer.write(message + "\n");
        }catch (IOException e){

        }
    }

    public static void closeLogger(){
        try {
            writer.close();
        }catch (IOException e){

        }
    }
}
