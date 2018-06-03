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
		
		float sollBetragFloat;
        try {
            sollBetragFloat = Float.parseFloat(sollBetrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Sollbetrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }
        
		Db db = new Db();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO Topf (Name, SollBestand, Kasse, Zeitstempel) "
                + "VALUES ('" + name + "','"
                + sollBetragFloat + "','"
                + kasse + "','"
                + timestamp + "')";

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
    	
    	float sollBetragFloat2;
        try {
            sollBetragFloat2 = Float.parseFloat(sollBetrag);
        } catch (NumberFormatException e) {
            throw new ElabException("Sollbetrag wurde nicht als korrekte Kommazahl angegeben! (float)");
        }
    	
    	Db db = new Db();
		String sql = "UPDATE Topf SET Name = '" + name +
								"', SollBestand = '" + sollBetragFloat2 +
								"', Kasse = '" + kasse +
								"' WHERE ID = " + id + "";
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

    //Kassenbestände
    // todo Kontostände müssen aus den Töpfen zusammengrechnet werden und returned werden :)
    public String getIstbestandBarkasse() {
    	
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Barkasse"))
    			{
    				betrag += rs.getFloat("IstBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	String result = String.valueOf(betrag);
        return result;
    }


    public String getSollbestandBarkasse() {
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Barkasse"))
    			{
    				betrag += rs.getFloat("SollBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	String result = String.valueOf(betrag);
        return result;
    }


    public String getIstbestandKonto() {
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Konto"))
    			{
    				betrag += rs.getFloat("IstBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	String result = String.valueOf(betrag);
        return result;
    }


    public String getSollbestandKonto() {
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Konto"))
    			{
    				betrag += rs.getFloat("SollBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	String result = String.valueOf(betrag);
        return result;
    }


    public String getIstbestandKostenstelle() {
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Kostenstelle"))
    			{
    				betrag += rs.getFloat("IstBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	String result = String.valueOf(betrag);
        return result;
    }


    public String getSollbestandKostenstelle() {
    	String sql = "SELECT * FROM Topf";
    	Db db = new Db();
    	float betrag = 0;
    	try {
    	ResultSet rs = db.exequteQuery(sql);
    		while(rs.next())
    		{
    			if(rs.getString("Kasse").equals("Kostenstelle"))
    			{
    				betrag += rs.getFloat("SollBestand");
    			}
    		}
    	} 
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	String result = String.valueOf(betrag);
        return result;
    }
}
