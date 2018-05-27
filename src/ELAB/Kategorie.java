package ELAB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kategorie {
    private int id;
    private String name;
    private int produktID;
    private ArrayList<Produkt> produkte;
    Map<Integer, Produkt> num = new HashMap<Integer, Produkt>();
    
	public Kategorie(int id, String name, int produkte) {
		this.id = id;
		this.name = name;
		this.produkte = num<produkte, Produkt>;
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
	
	public void produkteHinzufuegen(Produkt p)
	{
		produkte.add(p);
	}
}
