package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bauteileverwaltung {
	
	private ArrayList<Kategorie> kategorie;
	private ArrayList<Topf> topf;
	
	public Bauteileverwaltung()
	{
		kategorie = new ArrayList<>();
		topf = new ArrayList<>();
	}
	
	private void reloadProdukt()
	{
		Db db = new Db();
		this.kategorie.clear();
		try {
			ResultSet rs = db.exequteQuery("SELECT * FROM Produkt");
			while(rs.next())
			{
				Produkt p = new Produkt(rs.getInt("ID"),rs.getString("Name"),rs.getString("Link")
						,rs.getDouble("Einzelpreis"),rs.getInt("MengeLagernd")
						,rs.getInt("MengeGeplant"),rs.getInt("MengeBestellt"),rs.getString("LagerOrt"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
//	private void reloadKategorie()
//	{
//		Db db = new Db();
//		this.kategorie.clear();
//		try {
//			ResultSet rs = db.exequteQuery("SELECT * FROM Kategorie");
//			while (rs.next())
//			{
//				Kategorie p = new Kategorie(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Produkte"));
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	public void addProdukt(String name, String link, float einzelpreis, int menge_lagernd, int menge_geplant, int menge_bestellt, String lagerort)
	{
		Db db = new Db();
		String sql = "INSERT INTO Produkt (Name,Link,Einzelpreis,MengeLagernd,MengeGeplant,MengeBestellt,LagerOrt"
				+ "VALUES ('" + name + "','" + link + "'," + einzelpreis + "," + menge_lagernd + "," + menge_geplant + "," 
				+ menge_bestellt + ",'" + lagerort + "')";
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addKategorie(int kategorieID, String name, int produktID)
	{
		Db db = new Db();
		String sql = "INSERT INTO Kategorie (ID, Name, Produkte)"
				+ "VALUES (" + kategorieID + ",'" + name + "'," + produktID + ")";
		try {
			db.updateQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProdukt(int id, String name, String link, float einzelpreis, int mengeLagernd, int mengeGeplant, int mengeBestellt, String lagerOrt)
	{
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
	
	public void updateKategorie(int kategorieID, String name, int produktID)
	{
		/*String sql = "UPDATE Kategorie SET Name = '" + name + "', Link = '" + link 
    			+ "', Einzelpreis = '" + einzelpreis + "', MengeLagernd = '" + mengeLagernd + "', MengeGeplant = '"
    			+ mengeGeplant + "', MengeBestellt = '" + mengeBestellt + "', LagerOrt =  '" + lagerOrt + " "
    			+ "WHERE PersonID = " + id + "";  */
	}
	
	public void removeProdukt(int id)
	{
		Db db = new Db();
		String sql = "DELETE FROM Produkt WHERE ID = " + id + "";
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeKategorie(int id)
	{
		Db db = new Db();
		String sql = "DELETE FROM Kategorie WHERE ID = " + id + "";
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
