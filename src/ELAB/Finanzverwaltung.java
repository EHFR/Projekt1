package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Finanzverwaltung {
    private ArrayList<Topf> toepfe;
    

    public Finanzverwaltung() {
        toepfe = new ArrayList<>();
        this.reloadToepfe();
    }

//    public ArrayList<Topf> getToepfe() {
//        return new ArrayList<>(); // nur provisorisch um Fehler zu vermeiden, MUSS BEI IMPLEMENTIERUNG ENTFERNT WERDEN!
//    }

    public void setToepfe(ArrayList<Topf> toepfe) {
		this.toepfe = toepfe;
	}

	public void addTopf(String name, String sollBetrag, String konto) throws ElabException {

    }

    public void updateTopf(int id, String name, String sollBetrag, String konto) throws ElabException {

    }

    // todo Kontostände müssen aus den Töpfen zusammengrechnet werden und returned werden :)
    public String getIstbestandBarkasse() {
        return "hier steht später der Stand";
    }

    public String getSollbestandBarkasse() {
        return "hier steht später der Stand";
    }

    public String getIstbestandKonto() {
        return "hier steht später der Stand";
    }

    public String getSollbestandKonto() {
        return "hier steht später der Stand";
    }

    public String getIstbestandKostenstelle() {
        return "hier steht später der Stand";
    }

    public String getSollbestandKostenstelle() {
        return "hier steht später der Stand";
    }

    //Methoden für Töpfe
    private void reloadToepfe() {
        Db db = new Db();
        this.toepfe.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Topf");
            while (rs.next()) {
                Topf t = new Topf(rs.getInt("ID"), rs.getString("Name"), rs.getFloat("SollBestand"),
                        rs.getFloat("IstBestand"), rs.getString("Kasse"));
                t.setZeitstempel(rs.getTimestamp("Zeitstempel"));
                this.toepfe.add(t); 
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    
    public ArrayList<Topf> getToepfe() {
        this.reloadToepfe();
        return toepfe;
    }
    
    public void addToepfe(String name, float sollBestand, float istBestand, String kasse, String rechnungID) throws ElabException {

        Db db = new Db();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Auftrag (Name, SollBestand, IstBestand, Kasse, RechnungID) "
                + "VALUES ('" + name + "','"
                + sollBestand + "','"
                + istBestand + "','"
                + kasse + "',"
                + rechnungID + "')";
              
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeToepfe(int id) {
        Db db = new Db();
        Topf t = this.getTopfByID(id);
        String sql = "DELETE FROM Topf WHERE ID = " + t.getId() + " ";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Topf getTopfByID(int id) {
        for (Topf topf : toepfe) {
            if (topf.getId() == id) {
                return topf;
            }
        }
        return null;
    }
    
    public void updateToepfe(int id, String name, float sollBestand, float istBestand, 
    						  String kasse, String rechnungID) throws ElabException {

    		Db db = new Db();
    		String sql = "UPDATE Topf SET Name = '" + name + 
    								"', SollBestand = '" + sollBestand + 
    								"', IstBestand = '" + istBestand + 
    								"', Kasse = '" + kasse + 
    								"', RechnungID = '" + rechnungID + 
    								"WHERE ID = " + id + "";
    		try {
    			db.updateQuery(sql);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }

    
}
