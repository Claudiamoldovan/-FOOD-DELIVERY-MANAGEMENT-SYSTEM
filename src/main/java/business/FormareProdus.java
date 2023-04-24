package business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FormareProdus extends ItemMeniu {
    private HashSet<ItemMeniu> formare;
//Mostenind clasa ItemMeniu, este caracterizata de aceleasti atribute, in plus avand un HashSet in care
// pastram produsele ce compun un astfel de produs.Ca si metode, le  vom  rescrie pe cele din clasa ItemMeniu,
// particularizandu-le ( exememplu: la calorii, proteine, grasimi, sodiu – le vom adauga intr-o variabila valorile
// soecifice fiecarui produs, la nota vom realiza media aritmetica, iar la pret, pentru fiecare produs adaugat in
// comanda, din suma produselor vom scadea 10 lei)
    public FormareProdus(HashSet<ItemMeniu> formare, String nume,  float nota, float calorii, float proteine, float grasimi, float sodiu,float pret) {
        super(nume, nota, calorii, proteine, grasimi, sodiu,pret);
        this.formare = new HashSet<ItemMeniu>();
    }


    @Override
    public float formarePret() {
        float pret = 0;
        for (ItemMeniu menu : formare) {
            pret += menu.formarePret();
        }
        pret = pret - formare.size() * 5;
        return pret;
    }

    public void adaugareProdus(ItemMeniu menu) {
        formare.add(menu);
    }

    public HashSet<ItemMeniu> getformare() {
        return formare;
    }

    public void addElement(ItemMeniu menu) {
        formare.add(menu);
    }

    public void deleteElement(ItemMeniu menu) {
        formare.remove(menu);
    }

    public boolean gasesteProdus(String nume) {
        for (ItemMeniu menu : formare) {
            if (menu.getProdus().equals(nume))
                return true;
        }
        return false;
    }

    @Override
    public void setProdus(String nume) {
        super.setProdus(nume);
    }

    @Override
    public String getProdus() {
        return super.getProdus();
    }

    @Override
    public float getPret() {
        return super.getPret();
    }

    @Override
    public void setPret(float pret) {
        super.setPret(pret);
    }

    @Override
    public float getnota() {
        float nota = 0;
        for (ItemMeniu menu : formare) {
            nota += menu.getnota();
        }
        nota = nota / formare.size();
        return nota;
    }

    @Override
    public void setnota(float nota) {
        super.setnota(nota);
    }

    @Override
    public float getCalorii() {
        float calorii = 0;
        for (ItemMeniu menu : formare) {
            calorii += menu.getCalorii();
        }
        return calorii;
    }

    @Override
    public void setCalorii(float calorii) {
        super.setCalorii(calorii);
    }

    @Override
    public float getProteine() {
        float proteine = 0;
        for (ItemMeniu menu : formare) {
            proteine += menu.getProteine();
        }
        return proteine;
    }

    @Override
    public void setProteine(float proteine) {
        super.setProteine(proteine);
    }

    @Override
    public float getGrasimi() {
        float grasimi = 0;
        for (ItemMeniu menu : formare) {
            grasimi += menu.getGrasimi();
        }
        return grasimi;
    }

    @Override
    public void setGrasimi(float grasimi) {
        super.setGrasimi(grasimi);
    }

    @Override
    public float getSodiu() {
        float sodiu = 0;
        for (ItemMeniu menu : formare) {
            sodiu += menu.getnota();
        }
        return sodiu;
    }

    @Override
    public void setSodiu(float sodiu) {
        super.setSodiu(sodiu);
    }


}
