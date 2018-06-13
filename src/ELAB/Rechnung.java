package ELAB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Rechnung {
    private int id;
    private String name;
    private Person auftraggeber;
    private Person ansprechpartner;
    private Topf topf;
    private float betrag;
    private String bezahlart;
    private Timestamp statusZeitstempel_inBearbeitung;
    private Timestamp statusZeitstempel_eingereicht;
    private Timestamp statusZeitstempel_abgewickelt;
    private Timestamp statusZeitstempel_ausstehend;
    private boolean inBearbeitung;
    private boolean eingereicht;
    private boolean abgewickelt;
    private boolean ausstehend;
    private Timestamp zeitstempel;

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

    Person getAuftraggeber() {
        return auftraggeber;
    }

    void setAuftraggeber(Person auftraggeber) {
        this.auftraggeber = auftraggeber;
    }

    Person getAnsprechpartner() {
        return ansprechpartner;
    }

    void setAnsprechpartner(Person ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    Topf getTopf() {
        return topf;
    }

    void setTopf(Topf topf) {
        this.topf = topf;
    }

    float getBetrag() {
        return betrag;
    }

    void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    String getZeitstempelString() {
        return this.zeitstempelToString(zeitstempel);
    }

    String getBezahlart() {
        return bezahlart;
    }

    private String zeitstempelToString(Timestamp t) {
        if (t != null) {
            Date date = new Date();
            date.setTime(t.getTime());
            return new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(date);
        }
        return "Null"; // für den Fall, dass der Zeitstempel null ist
    }

    String getStatusZeitstempel_inBearbeitungString() {
        return this.zeitstempelToString(statusZeitstempel_inBearbeitung);
    }

    private void setStatusZeitstempel_inBearbeitung(Timestamp statusZeitstempel_inBearbeitung) {
        this.statusZeitstempel_inBearbeitung = statusZeitstempel_inBearbeitung;
    }

    String getStatusZeitstempel_eingereichtString() {
        return this.zeitstempelToString(statusZeitstempel_eingereicht);
    }

    private void setStatusZeitstempel_eingereicht(Timestamp statusZeitstempel_eingereicht) {
        this.statusZeitstempel_eingereicht = statusZeitstempel_eingereicht;
    }

    String getStatusZeitstempel_abgewickeltString() {
        return this.zeitstempelToString(statusZeitstempel_abgewickelt);
    }

    private void setStatusZeitstempel_abgewickelt(Timestamp statusZeitstempel_abgewickelt) {
        this.statusZeitstempel_abgewickelt = statusZeitstempel_abgewickelt;
    }

    String getStatusZeitstempel_ausstehendString() {
        return this.zeitstempelToString(statusZeitstempel_ausstehend);
    }

    private void setStatusZeitstempel_ausstehend(Timestamp statusZeitstempel_ausstehend) {
        this.statusZeitstempel_ausstehend = statusZeitstempel_ausstehend;
    }

    boolean isInBearbeitung() {
        return inBearbeitung;
    }

    private void setInBearbeitung(boolean inBearbeitung) {
        this.inBearbeitung = inBearbeitung;
    }

    boolean isEingereicht() {
        return eingereicht;
    }

    private void setEingereicht(boolean eingereicht) {
        this.eingereicht = eingereicht;
    }

    boolean isAbgewickelt() {
        return abgewickelt;
    }

    private void setAbgewickelt(boolean abgewickelt) {
        this.abgewickelt = abgewickelt;
    }

    boolean isAusstehend() {
        return ausstehend;
    }

    private void setAusstehend(boolean ausstehend) {
        this.ausstehend = ausstehend;
    }

    //Methoden für Rechnung
    void exportToPDF() {
        new ExportPDF(this);
    }

    private int intReturn(boolean b) {
        if (b)
            return 1;
        else
            return 0;
    }

    void updateStatus(boolean inBearbeitung, boolean eingereicht, boolean abgewickelt, boolean ausstehend) {
        Db db = new Db();
        String sql;
        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());

        if (this.isInBearbeitung() != inBearbeitung) {
            this.setInBearbeitung(inBearbeitung);
            this.setStatusZeitstempel_inBearbeitung(timestampNew);
            sql = " UPDATE Rechnung SET inBearbeitung = '" + intReturn(inBearbeitung) + "', statusZeitstempel_inBearbeitung = '" + timestampNew.toString()
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
            sql = " UPDATE Rechnung SET eingereicht = '" + intReturn(eingereicht) + "', statusZeitstempel_eingereicht = '" + timestampNew.toString()
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
            sql = " UPDATE Rechnung SET abgewickelt = '" + intReturn(abgewickelt) + "', statusZeitstempel_abgewickelt = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (this.isAusstehend() != ausstehend) {
            this.setAusstehend(ausstehend);
            this.setStatusZeitstempel_ausstehend(timestampNew);
            sql = " UPDATE Rechnung SET ausstehend = '" + intReturn(ausstehend) + "', statusZeitstempel_ausstehend = '" + timestampNew.toString()
                    + "' WHERE ID = " + this.id + "";
            try {
                db.updateQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void setNewTopfID(int id) {
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
