package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bauteileverwaltung {

    private ArrayList<Kategorie> kategorien;

    public Bauteileverwaltung() {
        kategorien = new ArrayList<>();
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
}