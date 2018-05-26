package ELAB;

import java.sql.Timestamp;
import java.util.Date;

public class Rechnung {
    private int id;
    private Date datum;
    private String name;
    private Kunde auftraggeber;
    private Mitglied ansprechpartner;
    private Topf topf;
    private float betrag;
    private String status;
    private Timestamp zeitstempel;
    
    public Rechnung(int id,Date datum, String name, Kunde auftraggeber, Mitglied ansprechpartner, Topf topf, float betrag, String status, Timestamp zeitstempel)
    {
    	this.id = id;
    	this.datum = new Date();
    	this.name = name;
    	this.auftraggeber = auftraggeber;
    	this.ansprechpartner = ansprechpartner;
    	this.topf = topf;
    	this.betrag = betrag;
    	this.status = status;
    	this.zeitstempel = zeitstempel;
    }

    
    public int getId() {
		return id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kunde getAuftraggeber() {
		return auftraggeber;
	}

	public void setAuftraggeber(Kunde auftraggeber) {
		this.auftraggeber = auftraggeber;
	}

	public Mitglied getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(Mitglied ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	public Topf getTopf() {
		return topf;
	}

	public void setTopf(Topf topf) {
		this.topf = topf;
	}

	public float getBetrag() {
		return betrag;
	}

	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(Timestamp zeitstempel) {
		this.zeitstempel = zeitstempel;
	}


	public void exportToPDF() {

    }
    
    
}
