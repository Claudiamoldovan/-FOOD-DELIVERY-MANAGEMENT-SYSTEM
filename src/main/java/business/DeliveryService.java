package business;

import dataLayer.FileWriterClass;
import dataLayer.Serializator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import presentation.Angajat;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    //Aceasta clasa implementeaza interfata IdeliveryService, deci vom implementa toate metodele definite aici.
    // Variabilele de clasa folosite sunt: meniu (lista produselor), produseComandate (HashMap in care cheia este
    // comanda, iar valoarea e un HashSet de ItemMeniu) si comenzi ( lista comenzilor).
   private HashMap<Comanda,HashSet<ItemMeniu>> produseComandate;
   private HashSet<ItemMeniu> meniu;
   private HashSet<Comanda> comenzi;


    public  DeliveryService(){
       meniu= new HashSet<ItemMeniu>();
       meniu();
       produseComandate=new HashMap<Comanda, HashSet<ItemMeniu>>();
       comenzi=new HashSet<Comanda>();
    }

    public HashMap<Comanda, HashSet<ItemMeniu>> getProduseComandate() {
        return produseComandate;
    }

    public void setProduseComandate(HashMap<Comanda, HashSet<ItemMeniu>> produseComandate) {
        this.produseComandate = produseComandate;
    }

    public HashSet<ItemMeniu> getMeniu() {
        return meniu;
    }

    public void setMeniu(HashSet<ItemMeniu> meniu) {
        this.meniu = meniu;
    }

    public HashSet<Comanda> getComanda() {
        return comenzi;
    }

    public void setComanda(HashSet<Comanda> comanda) {
        this.comenzi = comanda;
    }
//punem in lista de produse inregistrarile din fisierul „products.csv”
    public void meniu(){
        File csv =new File("products.csv");
        try{
            int start=0;
            InputStreamReader i=new InputStreamReader(new FileInputStream(csv));
            CSVParser csvParser= CSVFormat.DEFAULT.parse(i);
            for(CSVRecord csvRecord:csvParser){
                if(start==0){
                    start=1;
                }
                else{
                    Vector a=new Vector();
                    String string=csvRecord.get(0);
                    Float nota= Float.valueOf(csvRecord.get(1));
                    Float calorii= Float.valueOf(csvRecord.get(2));
                    Float proteine= Float.valueOf(csvRecord.get(3));
                    Float grasimi= Float.valueOf(csvRecord.get(4));
                    Float sodiu= Float.valueOf(csvRecord.get(5));
                    Float pret= Float.valueOf(csvRecord.get(6));
                    meniu.add(new ItemMeniu(string,nota,calorii,proteine,grasimi,sodiu,pret));
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void refreshCSV(){
        //rescriem in CSV noile informatii. Aceasta metoda ne ajuta sa pastram  in fisier datele mereu actualizate,
        // astfel incat tabelul dupa o anumita operatie se va actualiza imediat
        FileWriterClass file=new FileWriterClass("products.csv");
        String string="Title,nota,Calories,Protein,Fat,Sodium,Price\n";
        for(ItemMeniu m:meniu){
            string=string+m.toString()+"\n";
        }
        file.writeToFile(string);
    }

    @Override
    public void stergereMeniu(ItemMeniu menu) {
        assert menu!=null;
        assert wellFormed();
        int mSize=meniu.size();
        meniu.remove(menu);
        refreshCSV();
        assert meniu.size()==mSize-1;
        assert wellFormed();
    }

    @Override
    public void editareMeniu(ItemMeniu menu1, ItemMeniu menu2) {
        assert menu1!=null && menu2!=null;
        assert wellFormed();
        int mSize=meniu.size();
        meniu.remove(menu1);
        meniu.add(menu2);
        refreshCSV();
        assert meniu.size()==mSize;
        assert wellFormed();
    }

    @Override
    public void adaugareMeniu(ItemMeniu menu) {
        assert menu!=null;
        assert wellFormed();
        int mSize=meniu.size();
        meniu.add(menu);
        refreshCSV();
        assert meniu.size()==mSize+1;
        assert wellFormed();
    }

    @Override
    public void adaugareMeniuNou(String nume, HashSet<ItemMeniu> nou){
        assert nume!=null && nou!=null;
        assert wellFormed();
        int mSize=meniu.size();
        Float calorii = Float.valueOf(0),protein=Float.valueOf(0),grasimi=Float.valueOf(0),sodiu=Float.valueOf(0),nota=Float.valueOf(0),pret=Float.valueOf(0);
        for(ItemMeniu m:nou){
            calorii+=m.getCalorii();
            protein+=m.getProteine();
            grasimi+=m.getGrasimi();
            sodiu+=m.getSodiu();
            nota+=m.getnota();
            pret+=m.getPret()-10;
        }
       FormareProdus produs=new FormareProdus(nou,nume,nota/5,calorii,protein,grasimi  ,sodiu,pret);
        meniu.add(produs);
        refreshCSV();
        assert meniu.size()==mSize+1;
        assert wellFormed();
    }

    @Override
    public void comanda(Comanda c,HashSet<ItemMeniu> produse) {
        //metoda utilizata de client in momentul in care transmite o comnda. In acel moment, se va adauga
        // o comanda la lista de compenzi si produsele din comanda la lista produselor comandate
        assert c!=null && produse!=null;
        assert wellFormed();
        comenzi.add(c);
        produseComandate.put(c,produse);
        setChanged();
        notifyObservers(c);
    }


    @Override
    public List<ItemMeniu> gasesteProduse(String categorie, String detaliu) {
        //foloseste lambda expression. Are drept parametrii categoria (in functie de ce parametru cautam)
        // si detaliu (poate fi string sau int, in functie daca cautam dupa nume sa dupa restul atributelor).
        // De exemplu, pentru nume, lambda expression, folosind stream.filter vor gasi elementele ce contin in
        // numele lor cuvantul introdus de noi. La final, vom pune „.collect(Collectors.toList())” pentru a le
        // introduce intr-o lista pe care ulterior o vom afisa in JTable
        assert  categorie!= null && detaliu!=null;
        assert wellFormed();
        if (categorie.equals("Nume")) {
            return meniu.stream().filter(mi -> detaliu == null ||
                    mi.getProdus().toLowerCase().contains(detaliu.toLowerCase())).collect(Collectors.toList());
        } else if (categorie.equals("nota")) {
            Float nota = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> nota == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu) mi).getnota()).compareTo(nota) == 0)).collect(Collectors.toList());
        } else if (categorie.equals("Calorii")) {
            Float calories = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> calories == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu) mi).getCalorii()).equals(calories))).collect(Collectors.toList());
        } else if (categorie.equals("Proteine")) {
            Float protein = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> protein == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu) mi).getProteine()).equals(protein))).collect(Collectors.toList());
        } else if (categorie.equals("Grasimi")) {
            Float fats = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> fats == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu) mi).getGrasimi()).equals(fats))).collect(Collectors.toList());
        } else if (categorie.equals("Sodiu")) {
            Float sodium = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> sodium == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu) mi).getSodiu()).equals(sodium))).collect(Collectors.toList());
        } else if (categorie.equals("Pret")) {
            Float price = Float.valueOf(detaliu);
            return meniu.stream().filter(mi -> price == null || (mi instanceof ItemMeniu && Float.valueOf(((ItemMeniu)mi).getPret()).compareTo(price) == 0)).collect(Collectors.toList());
        }
            return null;
    }

    @Override
    public void generareInterval(int ora1,int ora2){
        //are ca parametrii ora de inceput si cea de sfarsit. Se foloseste in aceeasi maniera ca la metoda
        // gaseste produs lambda expression, utilizand un filtru pentru a vedea daca ora comenzii este in
        // intervalul dat de noi.
        assert ora1<ora2 &&ora1>=0&&ora2>=0&&ora1<=24&&ora2<=24;
        FileWriterClass f=new FileWriterClass("Raport Interval Timp.txt");
        String string="Comenzile plasate intre orele "+ora1+" - "+ora2+"\n\n";
        List<Comanda> c=comenzi.stream().filter(mi -> mi.getData().getHours()>=ora1 &&mi.getData().getHours()<=ora2).collect(Collectors.toList());

        for(Comanda i:c){
            string=string+i.toString()+"\n";
       }
        f.writeToFile(string);
    }

    @Override
    public void generareRaport2(int timp){
        //parcurgem o data comenzile pentru a introduce produsele intr-un ArrayList pe care ulterior,
        // utilizand lambda expression vom numara numarul de aparitii (functioneaza ca un vector de frecventa).
        // Daca numarul de aparitii este mai mare decat atributul dat de noi in antet, vom printa acel produs.
        assert timp>0;
        FileWriterClass f=new FileWriterClass("Raport Produse.txt");
        String string="Produsele comandate de mai mult de  "+timp+" ori \n\n";
        ArrayList<ItemMeniu> produse=new ArrayList<ItemMeniu>();
        for(Comanda c:comenzi){
            HashSet<ItemMeniu> prod=produseComandate.get(c);
            for(ItemMeniu menu:prod){
                produse.add(menu);
                }
            }

        Map<Object, Long> count=produse.stream().collect(Collectors.groupingBy(p->p.getProdus(),Collectors.counting()));

        for(Map.Entry<Object, Long> i: count.entrySet()){
            if(i.getValue()>=timp){
                string=string+i.getKey()+" "+i.getValue()+"\n";
            }
        }
        f.writeToFile(string);
    }

    @Override
    public void generareRaport3(int timp, float total) {
        //filtram o data comenzile pentru a le lua doar pe cele cu pretul de peste suma introdusa de noi,
        // urmand sa numaram comenzile date de fiecare client, iar in cazul de e mai mare decat numarul dat de
        // noi, vom afisa in raport id-ul clientului cu numarul de comenzi
        assert timp>0 && total>=0;
        FileWriterClass file=new FileWriterClass("Raport Clienti.txt");
        String string="Produsele comandate de mai mult de  "+timp+" ori \n\n idClient -> nr comenzi\n\n";
        List<Comanda> c=comenzi.stream().filter(mi -> mi.getPret()>=total).collect(Collectors.toList());
        ArrayList<ItemMeniu> clienti=new ArrayList<ItemMeniu>();

        Map<Object, Long> count=c.stream().collect(Collectors.groupingBy(p->p.getIdClient(),Collectors.counting()));

        for(Map.Entry<Object, Long> i: count.entrySet()){
            if(i.getValue()>=timp){
                string=string+ i.getKey()+" -> "+i.getValue()+"\n";
            }
        }
        file.writeToFile(string);
    }

    @Override
    public void generareRaport4(int zi) {
        //filtram comenzile penrtu a le obtine pe cele comandate intr-o anumita zi, urmand sa tinem intr0un
        // arrayList produsele pentru a le putea numara ulterior utilizand lambda expression, urmand sa le afisam.
        assert zi>=1&&zi<=31;
        FileWriterClass file=new FileWriterClass("Raport Produse Zi.txt");
        String string="Produsele comandate in ziua de  "+zi+" \n\n";
        List<Comanda> c=comenzi.stream().filter(mi -> mi.getData().getDay()-1==zi).collect(Collectors.toList());
        ArrayList<ItemMeniu> produse=new ArrayList<ItemMeniu>();
        for(Comanda c1:c){
            HashSet<ItemMeniu> prod=produseComandate.get(c1);
            for(ItemMeniu menu:prod){
                produse.add(menu);
            }
        }
        Map<Object, Long> count=produse.stream().collect(Collectors.groupingBy(p->p.getProdus(),Collectors.counting()));

        for(Map.Entry<Object, Long> i: count.entrySet()){
                string=string+i.getKey()+" "+i.getValue()+"\n";

        }
        file.writeToFile(string);
    }

    public float calcularePret(Comanda c){
        float total=0;
        HashSet<ItemMeniu> prod=produseComandate.get(c);
        for(ItemMeniu menu:prod){
            total+=menu.getPret();
        }
    return total;
    }

    public boolean wellFormed(){
        if(meniu==null||produseComandate==null||comenzi==null){
            return false;
        }
        return true;
    }

    @Override
    public void observerAdd(Angajat a) {
        this.addObserver(a);
    }
}
