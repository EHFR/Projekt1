package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kategorie {
    private int id;
    private String name;
    private ArrayList<Produkt> produkte;

    public Kategorie(int id, String name) {
        this.id = id;
        this.name = name;
        this.produkte = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    ArrayList<Produkt> getProdukte() {
        this.reloadProdukt();
        return produkte;
    }

    private void reloadProdukt() {
        Db db = new Db();
        this.produkte.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Produkt WHERE KategorieID = " + this.id);
            while (rs.next()) {
                Produkt p = new Produkt(rs.getInt("ID"), rs.getString("Name"), rs.getString("Link"),
                        rs.getDouble("Einzelpreis"), rs.getInt("MengeLagernd"),
                        rs.getInt("MengeGeplant"), rs.getInt("MengeBestellt"), rs.getString("LagerOrt"));
                this.produkte.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    void addProdukt(String name, String link, String einzelpreis, int menge_lagernd, int menge_geplant, int menge_bestellt, String lagerort) throws ElabException {
        float preis;
        try {
            preis = Float.parseFloat(einzelpreis);
        } catch (NumberFormatException e) {
            throw new ElabException("Der Einzelpreis wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Db db = new Db();
        String sql = "INSERT INTO Produkt (Name,Link,Einzelpreis,MengeLagernd,MengeGeplant,MengeBestellt,LagerOrt,KategorieID) "
                + "VALUES ('" + name + "','" + link + "'," + preis + "," + menge_lagernd + "," + menge_geplant + ","
                + menge_bestellt + ",'" + lagerort + "'," + this.id + ")";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateProdukt(int id, String name, String link, String einzelpreis, int mengeLagernd, int mengeGeplant, int mengeBestellt, String lagerOrt) throws ElabException {

        float preis;
        try {
            preis = Float.parseFloat(einzelpreis);
        } catch (NumberFormatException e) {
            throw new ElabException("Der Einzelpreis wurde nicht als korrekte Kommazahl angegeben! (float)");
        }

        Db db = new Db();
        String sql = "UPDATE Produkt SET Name = '" + name + "', Link = '" + link
                + "', Einzelpreis = " + preis + ", MengeLagernd = " + mengeLagernd + ", MengeGeplant = "
                + mengeGeplant + ", MengeBestellt = " + mengeBestellt + ", LagerOrt =  '" + lagerOrt + "' "
                + "WHERE ID = " + id;
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void removeProdukt(int id) throws ElabException {
        reloadProdukt();
        for (Produkt produkt : this.produkte) {
            if (produkt.getId() == id) {
                if (produkt.getMenge_lagernd() > 0) {
                    throw new ElabException("Von dem Produkt gibt es noch " + produkt.getMenge_lagernd() + " im Lager");
                }
            }
        }

        Db db = new Db();
        String sql = "DELETE FROM Produkt WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
