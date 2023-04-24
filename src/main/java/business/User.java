package business;

public class User {
    private String nume;
    private String parola;
    private int tip;//0-client; 1- admin
//Aceasta clasa contine datele care mapeaza tabelul „user”.
    //setere, getere si metoda to string pentru afisare
    public User(String nume, String parola,int tip){
        this.nume=nume;
        this.parola=parola;
        this.tip=tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
