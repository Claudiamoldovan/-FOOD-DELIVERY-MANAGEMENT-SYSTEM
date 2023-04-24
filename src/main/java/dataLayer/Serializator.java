package dataLayer;

import business.DeliveryService;

import java.io.*;

public class Serializator implements Serializable {
    private final static String file="file.txt";
    @Serial
    private static final long serialVersionUID = -8307568111969923319L;

    public static void serialize(DeliveryService d){
        try{
            FileOutputStream f=new FileOutputStream("file.txt");
            ObjectOutputStream out=new ObjectOutputStream(f);
            out.writeObject(d);
            out.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize(){
        DeliveryService d=new DeliveryService();
        try{
            FileInputStream f=new FileInputStream("file.txt");
            ObjectInputStream in=new ObjectInputStream(f);
            d=(DeliveryService) in.readObject();
            in.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return d;
    }

}
