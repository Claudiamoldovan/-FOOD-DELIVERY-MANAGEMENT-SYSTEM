package business;

public class BaseProduct extends ItemMeniu  {
    //Mostenind clasa ItemMeniu, este caracterizata de aceleasti atribute, iar metodele din clsaa
    // ItemMenu  vor fi rescrise, particularizandu-le.
    public BaseProduct(String nume, float nota, float calorii, float proteine, float grasimi, float sodiu,float pret)
    {super(nume,nota,calorii,proteine,grasimi,sodiu,pret);
    }

    @Override
    public float formarePret() {
        return super.getPret();
    }

    @Override
    public void setProdus(String nume){
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
        return super.getnota();
    }
    @Override
    public void setnota(float nota) {
        super.setnota(nota);
    }
    @Override
    public float getCalorii() {
        return super.getCalorii();
    }
    @Override
    public void setCalorii(float calorii) {
        super.setCalorii(calorii);
    }
    @Override
    public float getProteine() {
        return super.getProteine();
    }
    @Override
    public void setProteine(float proteine) {
        super.setProteine(proteine);
    }
    @Override
    public float getGrasimi() {
        return super.getGrasimi();
    }
    @Override
    public void setGrasimi(float grasimi) {
        super.setGrasimi(grasimi);
    }
    @Override
    public float getSodiu() {
       return super.getSodiu();
    }
    @Override
    public void setSodiu(float sodiu) {
        super.setSodiu(sodiu);
    }

}
