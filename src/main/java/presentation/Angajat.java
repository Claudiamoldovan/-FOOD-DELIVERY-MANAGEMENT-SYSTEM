package presentation;

import business.DeliveryService;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import business.*;

public class Angajat extends JFrame implements Observer {

    private DeliveryService ds;
    private JTextArea textList;
    private JLabel labelList;

    public Angajat(DeliveryService ds){
        this.ds=ds;

        getContentPane().setLayout(null);
        this.setBounds(0, 0, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelList = new JLabel(" Lista comenzi");
        labelList.setBounds(10, 10, 200, 100);
        getContentPane().add(labelList);

        textList = new JTextArea();
        textList.setBounds(10, 98, 450, 347);
        this.setBounds(10, 300, 500, 500);
        getContentPane().add(textList);
    }

    public DeliveryService getDs() {
        return ds;
    }

    public void setDs(DeliveryService ds) {
        this.ds = ds;
    }


    @Override
    public void update(Observable o, Object arg) {

        DeliveryService d=(DeliveryService) o;
        Comanda c=(Comanda) arg;
        HashSet<ItemMeniu> prod=d.getProduseComandate().get(c);
        String string="Comanda "+c.toString()+"\n";
        string+="Produse \n";
        for(ItemMeniu menu:prod){
            string+=menu.toString()+"\n";
        }
        textList.setText(string);
    }
}
