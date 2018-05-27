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
    private Timestamp statusZeitstempel; // todo Zeitstempel für den letzten Status implementieren (auch in Datenbank)
    private Timestamp zeitstempel;
    private Person auftraggeber;
    private boolean angenommen;
    private boolean gefertigt;
    private boolean kosten_kalkuliert;
    private boolean abgeholt;
    private boolean abgerechnet;
	private boolean wartenAufMaterial;
    private boolean fertigungFehlgeschlagen;
    private ArrayList<Person> auftragbearbeiter;
    
    public Auftrag(int id, String titel, String fertigungsart, String dateiname, String dateiort,
    		float kosten, boolean gefertigt, boolean kosten_kalkuliert, boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen, boolean angenommen) {
    	
        this.id = id;
        this.titel = titel;
        this.fertigungsart = fertigungsart;
        this.dateiname = dateiname;
        this.dateiort = dateiort;
        this.kosten = kosten;
        this.angenommen = false;
        this.gefertigt = false;
        this.kosten_kalkuliert = false;
        this.abgeholt = false;
        this.abgerechnet = false;
        this.wartenAufMaterial = false;
        this.fertigungFehlgeschlagen = false;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
    }
    
    
    
    public Timestamp getStatusZeitstempel() {
		return statusZeitstempel;
	}

	public void setStatusZeitstempel(Timestamp statusZeitstempel) {
		this.statusZeitstempel = statusZeitstempel;
	}

	public boolean isAngenommen() {
		return angenommen;
	}

	public void setAngenommen(boolean angenommen) {
		this.angenommen = angenommen;
	}

	public boolean isGefertigt() {
		return gefertigt;
	}

	public void setGefertigt(boolean gefertigt) {
		this.gefertigt = gefertigt;
	}

	public boolean isKosten_kalkuliert() {
		return kosten_kalkuliert;
	}

	public void setKosten_kalkuliert(boolean kosten_kalkuliert) {
		this.kosten_kalkuliert = kosten_kalkuliert;
	}

	public boolean isAbgeholt() {
		return abgeholt;
	}

	public void setAbgeholt(boolean abgeholt) {
		this.abgeholt = abgeholt;
	}

	public boolean isAbgerechnet() {
		return abgerechnet;
	}

	public void setAbgerechnet(boolean abgerechnet) {
		this.abgerechnet = abgerechnet;
	}

	public boolean isWartenAufMaterial() {
		return wartenAufMaterial;
	}

	public void setWartenAufMaterial(boolean wartenAufMaterial) {
		this.wartenAufMaterial = wartenAufMaterial;
	}

	public boolean isFertigungFehlgeschlagen() {
		return fertigungFehlgeschlagen;
	}

	public void setFertigungFehlgeschlagen(boolean fertigungFehlgeschlagen) {
		this.fertigungFehlgeschlagen = fertigungFehlgeschlagen;
	}

	public Timestamp getZeitstempel() {
		return zeitstempel;
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

    public String getZeitstempelString() {
        return zeitstempel.toString();
    }

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Person getAuftraggeber() {
        return auftraggeber;
    }

    public void setAuftraggeber(Person auftraggeber) {
        this.auftraggeber = auftraggeber;
    }

    public ArrayList<Person> getAuftragbearbeiter() {
        return auftragbearbeiter;
    }

    public void setAuftragbearbeiter(ArrayList<Person> auftragbearbeiter) {
        this.auftragbearbeiter = auftragbearbeiter;
    }

    public String getAuftragbearbeiterString() {
        StringBuilder output = new StringBuilder();
        for (Person person : this.auftragbearbeiter) {
            output.append(person.getName()).append("\r");
        }
        return output.toString();
    }
}
