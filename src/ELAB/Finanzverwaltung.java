package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Finanzverwaltung {
    private ArrayList<Kasse> kasse;

    public Finanzverwaltung() {
        kasse = new ArrayList<>();
        //this.reloadKasse();
    }
    /*
    private void reloadKasse() {
        Db db = new Db();
        this.kasse.clear();

        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Kasse");
            while (rs.next()) {
                Kasse k = new Kasse(rs.getArray("Töpfe"));
                this.kasse.add(k);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    
    public ArrayList<Kasse> getKasse() {
        this.reloadKasse();
        return kasse;
    }
    
    public void addKasse() {
        
		Db db = new Db();
	 
		String sql = "INSERT INTO Kasse (Töpfe) "
				+ "VALUES ()"; 
		try {
			db.updateQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
    public void removeKasse(int id) {
        Db db = new Db();
        String sql = "DELETE FROM Kasse WHERE ID = " + id + " ";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateKasse() {
        Db db = new Db();
        String sql = "UPDATE Kasse SET Titel = '"  "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  */

}
