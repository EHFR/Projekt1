package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kategorie {
    private int id;
    private String name;
    private int produktID;
    private ArrayList<Produkt> produkte;


    public Kategorie(int id, String name) {
        this.id = id;
        this.name = name;
        this.produkte = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Produkt> getProdukte() {
    	this.reloadProdukt();
        return produkte;
    }

    public void setProdukte(ArrayList<Produkt> produkte) {
        this.produkte = produkte;
    }

    public void produkteHinzufuegen(Produkt p) {
        produkte.add(p);
    }

    public int getProduktID() {
        return produktID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }



//	public Map<Integer, Produkt> getNum() {
//		return num;
//	}
//
//	public void setNum(Map<Integer, Produkt> num) {
//		this.num = num;
//	}

    /*
     *
     * Produkt
     *
     *
     */

    private void reloadProdukt() {
        Db db = new Db();
        this.produkte.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Produkt");
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

    public void addProdukt(String name, String link, String einzelpreis, int menge_lagernd, int menge_geplant, int menge_bestellt, String lagerort) throws ElabException{

    	float preis = Float.parseFloat(einzelpreis);

        //todo hier wird preis erzeugt, im SQL Befehl aber der alte Wert "einzelpreis"

        Db db = new Db();
        String sql = "INSERT INTO Produkt (Name,Link,Einzelpreis,MengeLagernd,MengeGeplant,MengeBestellt,LagerOrt) "
                + "VALUES ('" + name + "','" + link + "'," + einzelpreis + "," + menge_lagernd + "," + menge_geplant + ","
                + menge_bestellt + ",'" + lagerort + "')";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProdukt(int id, String name, String link, String einzelpreis, int mengeLagernd, int mengeGeplant, int mengeBestellt, String lagerOrt) throws ElabException{
    	float preis = Float.parseFloat(einzelpreis);

        Db db = new Db();
        String sql = "UPDATE Produkt SET Name = '" + name + "', Link = '" + link
                + "', Einzelpreis = '" + einzelpreis + "', MengeLagernd = '" + mengeLagernd + "', MengeGeplant = '"
                + mengeGeplant + "', MengeBestellt = '" + mengeBestellt + "', LagerOrt =  '" + lagerOrt + " "
                + "WHERE PersonID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProdukt(int id) throws ElabException{
        Db db = new Db();
        String sql = "DELETE FROM Produkt WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
