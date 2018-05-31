package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private float istbestand;
    private ArrayList<Rechnung> rechnungen;
    private String kasse;
    private Timestamp zeitstempel;
    
    public Topf() {
        rechnungen = new ArrayList<>();
        this.reloadRechnung();
    }

    public Timestamp getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Timestamp zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Topf(int id, String name, float sollbestand, float istbestand, String kasse) {
        this.id = id;
        this.name = name;
        this.sollbestand = sollbestand;
        this.istbestand = istbestand;
        this.kasse = kasse;
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

    public String getKasse() {
        return kasse;
    }


    // Methoden Für Rechnungen in dem Topf
    
    private void reloadRechnung() {
        Db db = new Db();
        this.rechnungen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Rechnung");
            while (rs.next()) {
                Rechnung r = new Rechnung(rs.getInt("ID"), rs.getString("Name"), rs.getFloat("Betrag"), rs.getString("Bezahlart"), 
                		rs.getBoolean("inBearbeitung"), rs.getTimestamp("statusZeitstempel_inBearbeitung"),
                		rs.getBoolean("eingereicht"), rs.getTimestamp("statusZeitstempel_eingereicht"),
                		rs.getBoolean("abgewickelt"), rs.getTimestamp("statusZeitstempel_abgewickelt"),
                		rs.getBoolean("ausstehend"), rs.getTimestamp("statusZeitstempel_ausstehend"));
                r.setZeitstempel(rs.getTimestamp("Zeitstempel"));
                this.rechnungen.add(r);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void addRechnung(String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {
    	
    	float BetragFloat;
        try {
            BetragFloat = Float.parseFloat(betrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Betrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }
    	
    	Personenverwaltung person = new Personenverwaltung();
    	
    	for (String namen : ansprechpartner.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Auftragbearbeiter " + namen + " existiert nicht!");
            }
        }
    	
    	for (String namen : auftraggeber.split("\n")) {
            if (!person.personAlreadyExists(namen)) {
                throw new ElabException("Auftraggeber " + namen + " existiert nicht!");
            }
        }
    	
    	ArrayList<String> personen = new ArrayList<>();
    	personen.add(auftraggeber);
    	personen.add(ansprechpartner);
    	
    	Db db = new Db();
        zeitstempel = new Timestamp(System.currentTimeMillis());
        
        String sql = "INSERT INTO Rechnung (Datum, Name, AuftragGeber, AnsprechPartner, TopfID, Betrag, Bezahlart, inBearbeitung, eingereicht, abgewickelt, ausstehend, Zeitstempel) "
                + "VALUES ('" + zeitstempel + "','"
                + name + "','"
                + auftraggeber + "','"
                + ansprechpartner + "',"
                + id + ",'"
                + BetragFloat + ","
                + bezahlart + ",'"
                + "FALSE','FALSE','FALSE','FALSE',"
                + zeitstempel + "')";
        
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRechnung(int id) throws ElabException {
    	Db db = new Db();
        Rechnung r = this.getRechnungByID(id);
        String sql = "DELETE FROM Rechnung WHERE ID = " + r.getId() + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Rechnung getRechnungByID(int id) {
        for (Rechnung rechnung : rechnungen) {
            if (rechnung.getId() == id) {
                return rechnung;
            }
        }
        return null;
    }

    public void updateRechnung(int id, String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {

    }

    public ArrayList<Rechnung> getRechungen() {
        // todo Alle rechnung im Topf mit der gegebenen ID werden als ArrayList ausgegeben!
        return new ArrayList<Rechnung>(); // Vor?bergehender Return
    }
}
