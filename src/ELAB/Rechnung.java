package ELAB;

import java.sql.SQLException;
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


    public Rechnung(int id, String name, float betrag, String bezahlart, boolean inBearbeitung, Timestamp statusZeitstempel_inBearbeitung,
                    boolean eingereicht, Timestamp statusZeitstempel_eingereicht, boolean abgewickelt,
                    Timestamp statusZeitstempel_abgewickelt, boolean ausstehend, Timestamp statusZeitstempel_ausstehend) {

        this.id = id;
        this.name = name;
        this.betrag = betrag;
        this.bezahlart = bezahlart;
        this.inBearbeitung = inBearbeitung;
        this.eingereicht = eingereicht;
        this.abgewickelt = abgewickelt;
        this.ausstehend = ausstehend;
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

    public String getBezahlart() {
        return bezahlart;
    }

    private String zeitstempelToString(Timestamp t) {
        if (t != null) {
            Date date = new Date();
            date.setTime(t.getTime());
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);
        }
        return "Null"; // für den Fall, dass der Zeitstempel null ist
    }

    public String getStatusZeitstempel_inBearbeitungString() {
        return this.zeitstempelToString(statusZeitstempel_inBearbeitung);
    }

    public void setStatusZeitstempel_inBearbeitung(Timestamp statusZeitstempel_inBearbeitung) {
        this.statusZeitstempel_inBearbeitung = statusZeitstempel_inBearbeitung;
    }

    public String getStatusZeitstempel_eingereichtString() {
        return this.zeitstempelToString(statusZeitstempel_eingereicht);
    }

    public void setStatusZeitstempel_eingereicht(Timestamp statusZeitstempel_eingereicht) {
        this.statusZeitstempel_eingereicht = statusZeitstempel_eingereicht;
    }

    public String getStatusZeitstempel_abgewickeltString() {
        return this.zeitstempelToString(statusZeitstempel_abgewickelt);
    }

    public void setStatusZeitstempel_abgewickelt(Timestamp statusZeitstempel_abgewickelt) {
        this.statusZeitstempel_abgewickelt = statusZeitstempel_abgewickelt;
    }

    public String getStatusZeitstempel_ausstehendString() {
        return this.zeitstempelToString(statusZeitstempel_ausstehend);
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


    //Methoden für Rechnung
    
    ExportPDF export = new ExportPDF();
    
    public void exportToPDF() {
    	
    	export.start(this);

    }


    public void updateStatus(boolean inBearbeitung, boolean eingereicht, boolean abgewickelt, boolean ausstehend) {

        Db db = new Db();
        String sql = "";
        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());

        if (this.isInBearbeitung() != inBearbeitung) {

            this.setInBearbeitung(inBearbeitung);
            this.setStatusZeitstempel_inBearbeitung(timestampNew);

            sql = " UPDATE Rechnung SET inBearbeitung = '" + inBearbeitung + "', statusZeitstempel_inBearbeitung = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";

            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (this.isEingereicht() != eingereicht) {

            this.setEingereicht(eingereicht);
            this.setStatusZeitstempel_eingereicht(timestampNew);

            sql = " UPDATE Rechnung SET eingereicht = '" + eingereicht + "', statusZeitstempel_eingereicht = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";

            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (this.isAbgewickelt() != abgewickelt) {

            this.setAbgewickelt(abgewickelt);
            this.setStatusZeitstempel_abgewickelt(timestampNew);

            sql = " UPDATE Rechnung SET abgewickelt = '" + abgewickelt + "', statusZeitstempel_abgewickelt = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";

            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (this.isAusstehend()!= ausstehend) {

            this.setAusstehend(ausstehend);
            this.setStatusZeitstempel_ausstehend(timestampNew);

            sql = " UPDATE Rechnung SET ausstehend = '" + ausstehend + "', statusZeitstempel_ausstehend = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";

            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    public void setNewTopfID(int id) {

        Db db = new Db();
        String sql = "UPDATE Rechnung SET TopfID = " + id
                + " WHERE ID = " + this.id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
