package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bauteileverwaltung {

    private ArrayList<Kategorie> kategorie;
    private ArrayList<Produkt> produkt;

    public Bauteileverwaltung() {
        kategorie = new ArrayList<>();
        produkt = new ArrayList<>();
    }

    private void reloadKategorie() {
        Db db = new Db();
        this.kategorie.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Kategorie");
            while (rs.next()) {
                Kategorie p = new Kategorie(rs.getInt("ID"), rs.getString("Name"));
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

    public void updateKategorie(ArrayList<String> produktID) {
        Db db = new Db();
        String sql = "UPDATE Kategorie SET produktID = '" + String.join("','", produktID) + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void removeKategorie(int id) throws ElabException {
        Db db = new Db();
        String sql = "DELETE FROM Kategorie WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Kategorie> getKategorien(){
        this.reloadKategorie();
        return this.kategorie;
    }
}
