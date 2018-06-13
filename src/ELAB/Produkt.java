package ELAB;

import java.sql.SQLException;

public class Produkt {
    private int id;
    private String name;
    private String link;
    private double einzelpreis;
    private int menge_lagernd;
    private int menge_geplant;
    private int menge_bestellt;
    private String lagerort;

    public Produkt(int id, String name, String link, double einzelpreis, int menge_lagernd, int menge_geplant, int menge_bestellt, String lagerort) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.einzelpreis = einzelpreis;
        this.menge_lagernd = menge_lagernd;
        this.menge_geplant = menge_geplant;
        this.menge_bestellt = menge_bestellt;
        this.lagerort = lagerort;
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

    String getLink() {
        return link;
    }

    double getEinzelpreis() {
        return einzelpreis;
    }

    int getMenge_lagernd() {
        return menge_lagernd;
    }

    int getMenge_geplant() {
        return menge_geplant;
    }

    int getMenge_bestellt() {
        return menge_bestellt;
    }

    void setMenge_lagernd(int menge_lagernd) {
        Db db = new Db();
        String sql = "UPDATE Produkt SET MengeLagernd = " + menge_lagernd
                + " WHERE ID = " + id;
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getLagerort() {
        return lagerort;
    }

}
