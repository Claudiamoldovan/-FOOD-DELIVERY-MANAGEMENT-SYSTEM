package business;

import presentation.Angajat;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

public interface IDeliveryServiceProcessing {
//Aceasta interfata contine toate antetele metodelor ce vor fi implemetate ulterior in clasa DeliveryService.
// Aici, inainte de fiecare metoda avem setate pre si post conditii ce trebuie respectate pentru a se realiza cu
// bine metoda.
    //administrator
    /*@param m
    @pre m!=null
    @post meniu.size==meniu.size()@pre-1
     */
    void stergereMeniu(ItemMeniu m);

    /*@param m
    @pre m!=null
    @post meniu.size==meniu.size()@pre
     */
    void editareMeniu(ItemMeniu m1,ItemMeniu m2);

    /*@param m1
    @param m2
    @pre m1!=null && m2!=null
    @post meniu.size==meniu.size()@pre+1
     */
    void adaugareMeniu(ItemMeniu m);

    /*
    @param p
    @param n
    @pre p!=null & n!=null
    @post meniu.size==meniu.size()@pre+1
     */
    void adaugareMeniuNou(String nume, HashSet<ItemMeniu> nou);

    /*
    @param o1
    @param o2
    @pre o1>=o2 && o1>=0 &&o2>=0 &&o1<=24 &&o1<=24
     */
    void generareInterval(int ora1,int ora2);

    /*
    @param t
    @pre t>0
     */
    void generareRaport2(int timp);

    /*
    @param t
    @param tot
    @pre t>0 && tot>=0
     */
    void generareRaport3(int timp,float total);

    /*
    @param zi
    @pre zi>=1 && zi<=31
     */
    void generareRaport4(int zi);

    //client

    /*
    @param com
    @param prod
    @pre com!=null && prod!=null
     */
    void comanda(Comanda c,HashSet<ItemMeniu> produse);

    /*
    @param c
    @param d
    @return lista de produse
    @pre c!=null && d!=null
     */
    List<ItemMeniu> gasesteProduse(String categorie, String detaliu);

    //Angajat
    void observerAdd(Angajat a);




}
