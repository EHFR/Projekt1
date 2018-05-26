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
}
