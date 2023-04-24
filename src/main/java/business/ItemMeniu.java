package business;

import java.io.Serializable;
import java.util.HashSet;
//Aceasta clasa contine datele despre un produs si anume: nume, nota, calorii, proteine, sodiu, grasimi si pret.
// Ca si metode existente in aceasta clasa sunt doar metoda de tipul setter si getter pentru a accesa variabilele
// de clasa. De asemenea, contine si metoda toString pentru a putea afisa informatiile despre un client.
// In plus, mai avem o metoda numita formarePret() prin care aflam  valoarea pretului unui produs.
// Folosind  Composite Design Pattern, aceasta clasa va fi mostenita de catre BaseProduct si FormareProdus
public class ItemMeniu implements Serializable{

    private String produs;
    private float nota;
    private float calorii;
    private float proteine;
    private float grasimi;
    private float sodiu;
    private float pret;

    public ItemMeniu(String nume) {
        this.produs=nume;
    }
    public ItemMeniu(String nume,float nota, float calorii, float proteine, float grasimi, float sodiu,float pret) {
        this.produs=nume;
        this.nota=nota;
        this.calorii=calorii;
        this.proteine=proteine;
        this.sodiu=sodiu;
        this.grasimi=grasimi;
        this.pret=pret;
    }

    public float formarePret(){
        return pret;
    };

    public String getProdus() {
        return produs;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public float getnota() {
        return nota;
    }

    public void setnota(float nota) {
        this.nota = nota;
    }

    public float getCalorii() {
        return calorii;
    }

    public void setCalorii(float calorii) {
        this.calorii = calorii;
    }

    public float getProteine() {
        return proteine;
    }

    public void setProteine(float proteine) {
        this.proteine = proteine;
    }

    public float getGrasimi() {
        return grasimi;
    }

    public void setGrasimi(float grasimi) {
        this.grasimi = grasimi;
    }

    public float getSodiu() {
        return sodiu;
    }

    public void setSodiu(float sodiu) {
        this.sodiu = sodiu;
    }

    public String toString(){
        return produs+", "+nota+", "+calorii+", "+proteine+", "+grasimi+", "+sodiu+", "+pret;
    }


}
