package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Finanzverwaltung {
    private ArrayList<Topf> toepfe;


    public Finanzverwaltung() {
        toepfe = new ArrayList<>();
        this.reloadTopf();
    }

//    public ArrayList<Topf> getToepfe() {
//        return new ArrayList<>(); // nur provisorisch um Fehler zu vermeiden, MUSS BEI IMPLEMENTIERUNG ENTFERNT WERDEN!
//    }

    public ArrayList<Topf> getToepfe() {
        this.reloadTopf();
        return toepfe;
    }

    public void setToepfe(ArrayList<Topf> toepfe) {
		this.toepfe = toepfe;
	}

    //Methoden für Töpfe
    private void reloadTopf() {
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


	public void addTopf(String name, String sollBetrag, String kasse) throws ElabException {
		float f = Float.parseFloat(sollBetrag);
		Db db = new Db();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Auftrag (Name, SollBestand, Kasse) "
                + "VALUES ('" + name + "','"
                + f + "','"
                + kasse + "')";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public void removeTopf(int id) throws ElabException {
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


    public void updateTopf(int id, String name, String sollBetrag, String kasse) throws ElabException {
    	float f = Float.parseFloat(sollBetrag);
    	Db db = new Db();
		String sql = "UPDATE Topf SET Name = '" + name +
								"', SollBestand = '" + f +
								"', Kasse = '" + kasse +
								"WHERE ID = " + id + "";
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

    //Kassenbestände
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


//    //Methoden bezüglich der Rechnungen
//
//    public void addRechnung(String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException{
//
//    }
//    public void removeRechnung(int id) throws ElabException{
//
//    }
//    public void updateRechnung(int id, String name, String auftraggeber, String ansprechpartner, String betrag, String bezahlart) throws ElabException{
//
//    }
//    public ArrayList<Rechnung> getRechungenFromTopf(int topfId){
//        // todo Alle rechnung im Topf mit der gegebenen ID werden als ArrayList ausgegeben!
//        return new ArrayList<Rechnung>(); // Vorübergehender Return
//    }
//
//    public void addRechnung(){
//
//    }



}
