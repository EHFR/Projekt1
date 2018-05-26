package ELAB;

import java.util.ArrayList;

public class Kategorie {
    private int id;
    private String name;
    private ArrayList<Produkt> produkte;
    
	public Kategorie(int id, String name, ArrayList<Produkt> produkte) {
		this.id = id;
		this.name = name;
		this.produkte = produkte;
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
