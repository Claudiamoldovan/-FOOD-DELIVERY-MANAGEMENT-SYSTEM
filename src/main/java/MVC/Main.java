package MVC;

import business.DeliveryService;
import business.IDeliveryServiceProcessing;
import dataLayer.Serializator;
import presentation.Angajat;
import presentation.LogInPres;

import java.io.Serializable;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        DeliveryService ds=new DeliveryService();
        ds=Serializator.deserialize();
        Angajat e=new Angajat(ds);
        ds.observerAdd(e);
        e.show();
        LogInPres f=new LogInPres(ds);
        f.setVisible(true);
    }
}
