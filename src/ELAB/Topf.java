package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Topf {
    private int id;
    private String name;
    private float sollbestand;
    private ArrayList<Rechnung> rechnungen;
    private String kasse;

    public Topf(int id, String name, float sollbestand, String kasse) {
        this.id = id;
        this.name = name;
        this.sollbestand = sollbestand;
        this.kasse = kasse;
        this.rechnungen = new ArrayList<>();
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

    float getSollbestand() {
        return sollbestand;
    }

    float getIstbestand() {
        this.reloadRechnung();
        float ergebnis = 0;
        for (Rechnung rechnung : this.rechnungen) {
            ergebnis += rechnung.getBetrag();
        }
        return ergebnis;
    }

    ArrayList<Rechnung> getRechnungen() {
        this.reloadRechnung();
        return rechnungen;
    }

    String getKasse() {
        return kasse;
    }

    // Methoden Für Rechnungen in dem Topf
    private boolean booleanReturn(int b) {
        return b == 1;
    }

    private void reloadRechnung() {
        Db db = new Db();
        this.rechnungen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Rechnung WHERE TopfID=" + this.getId());
            while (rs.next()) {
                Rechnung r = new Rechnung(rs.getInt("ID"), rs.getString("Name"), rs.getFloat("Betrag"), rs.getString("Bezahlart"),
                        booleanReturn(rs.getInt("inBearbeitung")), rs.getTimestamp("statusZeitstempel_inBearbeitung"),
                        booleanReturn(rs.getInt("eingereicht")), rs.getTimestamp("statusZeitstempel_eingereicht"),
                        booleanReturn(rs.getInt("abgewickelt")), rs.getTimestamp("statusZeitstempel_abgewickelt"),
                        booleanReturn(rs.getInt("ausstehend")), rs.getTimestamp("statusZeitstempel_ausstehend"));
                r.setZeitstempel(rs.getTimestamp("Zeitstempel"));

                try {
                    Personenverwaltung personenVerwaltung = new Personenverwaltung();
                    Person geber = personenVerwaltung.getPersonByName(rs.getString("AuftragGeber"));
                    r.setAuftraggeber(geber);

                    Person ansprechPartner = personenVerwaltung.getPersonByName(rs.getString("AnsprechPartner"));
                    r.setAnsprechpartner(ansprechPartner);

                } catch (ElabException e) {
                    e.printStackTrace();
                }

                r.setTopf(this);
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

    void addRechnung(String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {
        float BetragFloat;
        try {
            BetragFloat = Float.parseFloat(betrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Betrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Personenverwaltung personenverwaltung = new Personenverwaltung();
        if (!personenverwaltung.personAlreadyExists(auftraggeber)) {
            throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht!");
        }
        if (!personenverwaltung.personAlreadyExists(ansprechpartner)) {
            throw new ElabException("Ansprechpartner " + ansprechpartner + " existiert nicht");
        }
        if (!personenverwaltung.getPersonByName(ansprechpartner).getType().equals("Mitglied")) {
            throw new ElabException("Ansprechpartner " + ansprechpartner + " ist kein Mitglied!");
        }

        Db db = new Db();
        Timestamp zeitstempel = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT INTO Rechnung (Name, AuftragGeber, AnsprechPartner, TopfID, Betrag, Bezahlart, Zeitstempel) "
                + "VALUES ('"
                + name + "','"
                + auftraggeber + "','"
                + ansprechpartner + "',"
                + this.id + ","
                + BetragFloat + ",'"
                + bezahlart + "','"
                + zeitstempel + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void removeRechnung(int id) throws ElabException {
        Db db = new Db();
        String sql = "DELETE FROM Rechnung WHERE ID = " + id + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateRechnung(int id, String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException {
        float BetragFloat;
        try {
            BetragFloat = Float.parseFloat(betrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Betrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Personenverwaltung personenverwaltung = new Personenverwaltung();
        if (!personenverwaltung.personAlreadyExists(auftraggeber)) {
            throw new ElabException("Auftraggeber " + auftraggeber + " existiert nicht!");
        }
        if (!personenverwaltung.personAlreadyExists(ansprechpartner)) {
            throw new ElabException("Ansprechpartner " + ansprechpartner + " existiert nicht");
        }
        if (!personenverwaltung.getPersonByName(ansprechpartner).getType().equals("Mitglied")) {
            throw new ElabException("Ansprechpartner " + ansprechpartner + " ist kein Mitglied!");
        }

        Db db = new Db();
        String sql = "UPDATE Rechnung SET Name = '" + name + "', AuftragGeber = '" + auftraggeber
                + "', AnsprechPartner = '" + ansprechpartner + "', Betrag = '" + BetragFloat + "', Bezahlart = '" + bezahlart
                + "' WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
