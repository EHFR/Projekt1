package ELAB;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private float istbestand;
    private ArrayList<Rechnung> rechnungen;
    private String konto;
    private Timestamp zeitstempel;

    public Timestamp getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	public Topf(int id, String name, float sollbestand, float istbestand, String konto) {
        this.id = id;
        this.name = name;
        this.sollbestand = sollbestand;
        this.istbestand = istbestand;
        this.konto = konto;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
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

    public void fuegeRechnungHinzu(Rechnung r) {
        rechnungen.add(r);
    }

    public String getKonto() {
        return konto;
    }
}
