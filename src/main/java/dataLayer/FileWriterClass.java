package dataLayer;


import java.io.IOException;
import java.io.FileWriter;

public class FileWriterClass {
    private String filetoWrite;
    public FileWriterClass(String fileToWrite){
        this.filetoWrite=fileToWrite;
    }

    public void writeToFile(String str){
        FileWriter f;
        try{
            f=new FileWriter(this.filetoWrite);
            f.write(str);
            f.close();
        } catch (IOException e) {
            System.out.println("IOException Catched");
            e.printStackTrace();
        }
    }
}
