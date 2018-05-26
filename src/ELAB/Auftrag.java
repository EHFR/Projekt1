package ELAB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Auftrag {
    private int id;
    private String titel;
    private String fertigungsart;
    private String dateiname;
    private String dateiort;
    private float kosten;
    private String status;
    private Timestamp zeitstempel;
    private ArrayList<Person> personen;
    
    public Auftrag(int id,String titel, String fertigungsart, String dateiname, String dateiort, float kosten, String status, Timestamp zeitstempel)
    {
    	this.id = id;
    	this.titel = titel;
    	this.fertigungsart = fertigungsart;
    	this.dateiname = dateiname;
    	this.dateiort = dateiort;
    	this.kosten = kosten;
    	this.status = status;
    	this.zeitstempel = zeitstempel;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getFertigungsart() {
		return fertigungsart;
	}

	public void setFertigungsart(String fertigungsart) {
		this.fertigungsart = fertigungsart;
	}

	public String getDateiname() {
		return dateiname;
	}

	public void setDateiname(String dateiname) {
		this.dateiname = dateiname;
	}

	public String getDateiort() {
		return dateiort;
	}

	public void setDateiort(String dateiort) {
		this.dateiort = dateiort;
	}

	public float getKosten() {
		return kosten;
	}

	public void setKosten(float kosten) {
		this.kosten = kosten;
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

	public ArrayList<Person> getPersonen() {
		return personen;
	}

	public void setPersonen(ArrayList<Person> personen) {
		this.personen = personen;
	}
    
    public void fügePersonHinzu(Person p)
    {
    	personen.add(p);
    }
    
    
    
}
