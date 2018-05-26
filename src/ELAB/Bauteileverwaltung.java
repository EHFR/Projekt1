package ELAB;

import java.sql.SQLException;

public class Bauteileverwaltung {
	
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
	
	
}
