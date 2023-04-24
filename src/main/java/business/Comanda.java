package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

public class Comanda extends Observable implements Serializable {
    private int id;
    private Date data;
    private static int idC = 1;
    private float pret;
    private int idClient;
//Aceasta  clasa contine datele ce caracterizeaza o comanda is anume: id, idClient, data si totalul comenzii.
// Ca si metode existente in aceasta clasa sunt doar metoda de tipul setter si getter pentru a accesa variabilele
// de clasa. De asemenea, contine si metoda toString pentru a putea afisa informatiile despre o comanda.
// In plus, mai avem o metoda hashCode() ce returneaza id-ul comenzii.
    public Comanda(float pret,int idClient) {
        this.id=idC;
        data=new Date();
        this.idClient=idClient;
        this.pret = pret;
        idC++;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getId() {
        return idC-1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData() {
        this.data = new Date();
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public int hashCode() {
        return id;

    }
    public String toString(){
        return " client "+idClient+" total "+pret+"\n";
    }



}
