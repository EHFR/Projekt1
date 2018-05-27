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
    private ArrayList<Person> auftragbearbeiter;

    public Auftrag(int id, String titel, String fertigungsart, String dateiname, String dateiort, float kosten, String status) {
        this.id = id;
        this.titel = titel;
        this.fertigungsart = fertigungsart;
        this.dateiname = dateiname;
        this.dateiort = dateiort;
        this.kosten = kosten;
        this.status = status;
        this.zeitstempel = new Timestamp(System.currentTimeMillis());
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
