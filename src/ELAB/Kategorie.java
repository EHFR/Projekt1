package ELAB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kategorie {
    private int id;
    private String name;
    private int produktID;
    private ArrayList<Produkt> produkte;


    public Kategorie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Produkt> getProdukte() {
        return produkte;
    }

    public void setProdukte(ArrayList<Produkt> produkte) {
        this.produkte = produkte;
    }

    public void produkteHinzufuegen(Produkt p) {
        produkte.add(p);
    }

    public int getProduktID() {
        return produktID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }

//	public Map<Integer, Produkt> getNum() {
//		return num;
//	}
//
//	public void setNum(Map<Integer, Produkt> num) {
//		this.num = num;
//	}


}
