package ELAB;

import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private float istbestand;
    private ArrayList<Rechnung> rechnungen;
    
	public Topf(int id, String name, float sollbestand, float istbestand, ArrayList<Rechnung> rechnungen) {
		this.id = id;
		this.name = name;
		this.sollbestand = sollbestand;
		this.istbestand = istbestand;
		this.rechnungen = rechnungen;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getSollbestand() {
		return sollbestand;
	}

	public void setSollbestand(float sollbestand) {
		this.sollbestand = sollbestand;
	}

	public float getIstbestand() {
		return istbestand;
	}

	public void setIstbestand(float istbestand) {
		this.istbestand = istbestand;
	}

	public ArrayList<Rechnung> getRechnungen() {
		return rechnungen;
	}

	public void setRechnungen(ArrayList<Rechnung> rechnungen) {
		this.rechnungen = rechnungen;
	}
	
	public void fuegeRechnungHinzu(Rechnung r)
    {
    	rechnungen.add(r);
    }
	
    
    
}
