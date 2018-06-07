package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bauteileverwaltung {

    private ArrayList<Kategorie> kategorien;
    private ArrayList<Bestellung> bestellungen;

    public Bauteileverwaltung() {
        kategorien = new ArrayList<>();
        bestellungen = new ArrayList<>();
    }

    private void reloadKategorie() {
        Db db = new Db();
        this.kategorien.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Kategorie");
            while (rs.next()) {
                Kategorie k = new Kategorie(rs.getInt("ID"), rs.getString("Name"));
                this.kategorien.add(k);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void addKategorie(String name) throws ElabException {
        Db db = new Db();
        String sql = "INSERT INTO Kategorie (Name)"
                + "VALUES ('" + name + "')";
        try {
            db.updateQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removeKategorie(int id) throws ElabException {
        for (Kategorie kategorie : this.kategorien) {
            if (kategorie.getId() == id) {
                if (kategorie.getProdukte().size() > 0) {
                    throw new ElabException("Es gibt noch Produkte in dieser Kategorie");
                }
            }
        }

        Db db = new Db();
        String sql = "DELETE FROM Kategorie WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Kategorie> getKategorien() {
        this.reloadKategorie();
        return this.kategorien;
    }

    /**
     * Bestellungen
     */

    private void reloadBestellungen() {
        Db db = new Db();
        this.bestellungen.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Bestellung");
            while (rs.next()) {
                Bestellung bestellung = new Bestellung(rs.getInt("ID"), rs.getString("Name"), rs.getString("Produkt"),
                        rs.getString("Kategorie"), rs.getString("Einzelpreis"), rs.getString("Menge"));
                this.bestellungen.add(bestellung);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void addBestellung(String name, String produkt, String kategorie, String einzelpreis, String menge) {
        Db db = new Db();
        String sql = "INSERT INTO Bestellung (Name, Produkt, Kategorie, Einzelpreis, Menge)"
                + "VALUES ('" + name + "','"
                + produkt + "','"
                + kategorie + "','"
                + einzelpreis + "','"
                + menge + "')";
        try {
            db.updateQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bestellung> getBestellungen() {
        this.reloadBestellungen();
        return this.bestellungen;
    }

    public void removeBestellung(int id) throws ElabException {
        Db db = new Db();
        String sql = "DELETE FROM Bestellung WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}