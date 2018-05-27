package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fertigungsverwaltung {
	private ArrayList<Auftrag> auftraege;

    public Fertigungsverwaltung() {
        auftraege = new ArrayList<>();
        this.reloadAuftraege();
    }
    
    private void reloadAuftraege() {
        Db db = new Db();
        this.auftraege.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Auftrag");
            while (rs.next()) {
                Auftrag a = new Auftrag(rs.getInt("ID"), rs.getString("Titel"),
                        rs.getString("FertigungsArt"), rs.getString("DateiName"),
                        rs.getString("DateiOrt"), rs.getFloat("Kosten"),rs.getBoolean("angenommen"),
                        rs.getBoolean("gefertigt"),rs.getBoolean("kosten_kalkuliert"),rs.getBoolean("abgeholt"),
                        rs.getBoolean("abgerechnet"),rs.getBoolean("wartenAufMaterial"),rs.getBoolean("fertigungFehlgeschlagen"));
                a.setZeitstempel(rs.getTimestamp("timestamp"));
                this.auftraege.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Auftrag> getAuftraege() {
        this.reloadAuftraege();
        return auftraege;
    }
    
    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten,  boolean angenommen,
    				boolean gefertigt, boolean kosten_kalkuliert, boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen) {
        
		Db db = new Db();
	 
		String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen) "
				+ "VALUES ('" + titel + "','" + fertigungsArt + "','" + dateiName 
				+ "','" + dateiOrt + "','" + kosten + "'," + angenommen + "," + gefertigt + "," 
				+ kosten_kalkuliert + "," + abgeholt + "," + abgerechnet 
				+ "," + wartenAufMaterial + "," + fertigungFehlgeschlagen + ")"; 
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    public void removeAuftrag(int id) {
        Db db = new Db();
        String sql = "DELETE FROM Auftrag WHERE ID = " + id + " ";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten,boolean angenommen ,boolean gefertigt, 
    			boolean kosten_kalkuliert, boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen) {
        Db db = new Db();
        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = '" + kosten + "', angenommen = " + angenommen + ", gefertigt = "
                + gefertigt + ", kosten_kalkuliert = " + kosten_kalkuliert + ", abgeholt = " 
                + abgeholt + ", abgerechnet = " + abgerechnet + ", wartenAufMaterial = " + wartenAufMaterial + ", fertigungFehlgeschlagen = " + fertigungFehlgeschlagen
                + "WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
