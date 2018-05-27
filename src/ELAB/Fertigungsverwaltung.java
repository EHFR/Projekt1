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
                        rs.getString("DateiOrt"), rs.getFloat("Kosten"), rs.getString("Status"));
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

    public ArrayList<Auftrag> getAuftrag() {
        this.reloadAuftraege();
        return auftraege;
    }
    
    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten, String status) {
        
		Db db = new Db();
	 
		String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, Status) "
				+ "VALUES ('" + titel + "','" + fertigungsArt + "','" + dateiName + "','" + dateiOrt + "','" + kosten + "','" + status + "')"; 
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
    
    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten, String status) {
        Db db = new Db();
        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = '" + kosten + "', Status = '" + status + "' "
                + "WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
}
