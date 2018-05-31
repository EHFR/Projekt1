package ELAB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rechnung {
    private int id;
    private Date datum;
    private String name;
    private Person auftraggeber;
    private Person ansprechpartner;
    private Topf topf;
    private float betrag;
    private String bezahlart;
    private String status;
    private Timestamp statusZeitstempel_inBearbeitung;
    private Timestamp statusZeitstempel_eingereicht;
    private Timestamp statusZeitstempel_abgewickelt;
    private Timestamp statusZeitstempel_ausstehend;
    private boolean inBearbeitung;
    private boolean eingereicht;
    private boolean abgewickelt;
    private boolean ausstehend;
    private Timestamp zeitstempel;
    private Timestamp statusZeitstempel;

    //todo Hier fehlt noch Bezahlart und ein Getter Methode dafür!

    public Rechnung(int id, String name, Person auftraggeber, Person ansprechpartner, Topf topf, 
			float betrag, String bezahlart, boolean inBearbeitung, Timestamp statusZeitstempel_inBearbeitung, 
			boolean eingereicht, Timestamp statusZeitstempel_eingereicht, boolean abgewickelt, 
			Timestamp statusZeitstempel_abgewickelt, boolean ausstehend, Timestamp statusZeitstempel_ausstehend,
			Date datum) {
    			
    	this.id = id;
    	this.name = name;
    	this.auftraggeber = auftraggeber;
    	this.ansprechpartner = ansprechpartner;
		this.topf = topf;
		this.betrag = betrag;
		this.bezahlart = bezahlart;
		this.inBearbeitung = false;
		this.eingereicht = false;
		this.abgewickelt = false;
		this.ausstehend = false;
		this.statusZeitstempel_inBearbeitung = statusZeitstempel_inBearbeitung;
		this.statusZeitstempel_eingereicht = statusZeitstempel_eingereicht;
		this.statusZeitstempel_abgewickelt = statusZeitstempel_abgewickelt;
		this.statusZeitstempel_ausstehend = statusZeitstempel_ausstehend;
		this.datum = new Timestamp(System.currentTimeMillis());
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

    public Person getAuftraggeber() {
        return auftraggeber;
    }

    public void setAuftraggeber(Person auftraggeber) {
        this.auftraggeber = auftraggeber;
    }

    public Person getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(Person ansprechpartner) {
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

    public String getZeitstempelString() {
        if (zeitstempel != null) {
            Date date = new Date();
            date.setTime(zeitstempel.getTime());
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);
        }
        return "Null"; // für den Fall, dass der Zeitstempel null ist
    }

    public String getBezahlart(){
        return "Die Bezahlart"; // todo das hier ist nur ein Platzhalter
    }

    public Timestamp getStatusZeitstempel_inBearbeitung() {
		return statusZeitstempel_inBearbeitung;
	}

	public void setStatusZeitstempel_inBearbeitung(Timestamp statusZeitstempel_inBearbeitung) {
		this.statusZeitstempel_inBearbeitung = statusZeitstempel_inBearbeitung;
	}

	public Timestamp getStatusZeitstempel_eingereicht() {
		return statusZeitstempel_eingereicht;
	}

	public void setStatusZeitstempel_eingereicht(Timestamp statusZeitstempel_eingereicht) {
		this.statusZeitstempel_eingereicht = statusZeitstempel_eingereicht;
	}

	public Timestamp getStatusZeitstempel_abgewickelt() {
		return statusZeitstempel_abgewickelt;
	}

	public void setStatusZeitstempel_abgewickelt(Timestamp statusZeitstempel_abgewickelt) {
		this.statusZeitstempel_abgewickelt = statusZeitstempel_abgewickelt;
	}

	public Timestamp getStatusZeitstempel_ausstehend() {
		return statusZeitstempel_ausstehend;
	}

	public void setStatusZeitstempel_ausstehend(Timestamp statusZeitstempel_ausstehend) {
		this.statusZeitstempel_ausstehend = statusZeitstempel_ausstehend;
	}

	public boolean isInBearbeitung() {
		return inBearbeitung;
	}

	public void setInBearbeitung(boolean inBearbeitung) {
		this.inBearbeitung = inBearbeitung;
	}

	public boolean isEingereicht() {
		return eingereicht;
	}

	public void setEingereicht(boolean eingereicht) {
		this.eingereicht = eingereicht;
	}

	public boolean isAbgewickelt() {
		return abgewickelt;
	}

	public void setAbgewickelt(boolean abgewickelt) {
		this.abgewickelt = abgewickelt;
	}

	public boolean isAusstehend() {
		return ausstehend;
	}

	public void setAusstehend(boolean ausstehend) {
		this.ausstehend = ausstehend;
	}

	public Timestamp getStatusZeitstempel() {
		return statusZeitstempel;
	}

	public void setStatusZeitstempel(Timestamp statusZeitstempel) {
		this.statusZeitstempel = statusZeitstempel;
	}

    public void exportToPDF() {

    }
}
